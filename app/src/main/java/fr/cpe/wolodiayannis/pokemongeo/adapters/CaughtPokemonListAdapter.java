package fr.cpe.wolodiayannis.pokemongeo.adapters;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.databinding.PokemonItemBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemPotion;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemRevive;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokedexListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokemonSwitchInterface;
import fr.cpe.wolodiayannis.pokemongeo.viewmodel.PokemonViewModel;

/**
 * Adapter for the list of Pokemon.
 */
public class CaughtPokemonListAdapter extends RecyclerView.Adapter<CaughtPokemonListAdapter.ViewHolder> implements Filterable {
    /**
     * Listener for the click on a Pokemon.
     */
    private final PokedexListenerInterface listener;

    /**
     * Listener for the switch of a Pokemon.
     */
    private final PokemonSwitchInterface switchListener;

    /**
     * List of Pokemon.
     */
    private CaughtInventory caughtInventory;

    /**
     * List of Pokemon received at init.
     */
    private final CaughtInventory initCaughtInventory;

    /**
     * Constructor.
     *
     * @param caughtInventory List of Pokemon.
     * @param listener        Listener for the click on a Pokemon.
     */
    public CaughtPokemonListAdapter(CaughtInventory caughtInventory, PokedexListenerInterface listener) {
        this.listener = listener;
        this.caughtInventory = caughtInventory;
        this.initCaughtInventory = caughtInventory;
        this.switchListener = null;
    }

    /**
     * Constructor.
     *
     * @param caughtInventory List of Pokemon.
     * @param listener        Listener for the click on a Pokemon.
     */
    public CaughtPokemonListAdapter(CaughtInventory caughtInventory, PokemonSwitchInterface listener) {
        this.listener = null;
        this.switchListener = listener;

        // change the caught inventory list depending on the context
        if (Datastore.getInstance().getActualItem() instanceof ItemRevive) {
            this.caughtInventory = deadCaughtInventory(caughtInventory);
        } else if (Datastore.getInstance().getActualItem() instanceof ItemPotion) {
            this.caughtInventory = aliveCaughtInventory(caughtInventory);
        } else {
            this.caughtInventory = caughtInventory;
        }

        Datastore.getInstance().setActualItem(null);

        this.initCaughtInventory = this.caughtInventory;
    }

    /**
     * Create a new ViewHolder.
     *
     * @param parent   Parent ViewGroup.
     * @param viewType ViewType.
     * @return ViewHolder.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.pokemon_item, parent, false);

        return new ViewHolder(binding);
    }

    /**
     * Bind the ViewHolder with the Pokemon.
     *
     * @param holder   ViewHolder.
     * @param position Position of the Pokemon in the list.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HashMap<Pokemon, CaughtPokemon> caughtPokemon = caughtInventory.getCaughtInventoryList();
        Pokemon pokemon = (Pokemon) caughtPokemon.keySet().toArray()[position];

        // if currentLifestate === 0, then the pokemon is dead display gray filter on the pokemon
        if (Objects.requireNonNull(caughtPokemon.get(pokemon)).getCurrentLifeState() <= 0) {
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            holder.binding.pokemonImage.setColorFilter(new ColorMatrixColorFilter(matrix));
        }

        if (listener != null) {
            // Set the listener for the click on the Pokemon.
            holder.binding.getRoot().setOnClickListener(v -> listener.onPokemonSelected(pokemon));

        } else if (switchListener != null) {

            // Set the listener for the click on the Pokemon.
            holder.binding.getRoot().setOnClickListener(v -> {

                ArrayList<?> ar = Datastore.getInstance()
                        .getCaughtInventory()
                        .getPokemonAndCaughtPokemon(pokemon);

                switchListener.onPokemonSwitch((Pokemon) ar.get(0), (CaughtPokemon) ar.get(1));
            });

        }

        // Set the color of the pokemon bg.
        holder.binding.pokemonBg.getBackground().setTint(pokemon.getBackgroundColor());

        // Set the PokemonViewModel to the layout.
        holder.binding.getPokemonViewModel().setPokemon(pokemon);
    }

    /**
     * Get the number of Pokemon in the list.
     *
     * @return Number of Pokemon.
     */
    @Override
    public int getItemCount() {
        return caughtInventory.getCaughtInventoryList().size();
    }

    /**
     * ViewHolder for the Pokemon.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final PokemonItemBinding binding;

        ViewHolder(PokemonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            PokemonViewModel viewModel = new PokemonViewModel();
            this.binding.setPokemonViewModel(viewModel);
        }
    }

    /**
     * Filter the caught inventory to return only alive pokemon.
     *
     * @param caughtInventory List of all caught Pokemon.
     * @return a caught inventory with only alive pokemon.
     */
    private CaughtInventory aliveCaughtInventory(CaughtInventory caughtInventory) {
        CaughtInventory aliveCaughtInventory = new CaughtInventory();

        for (Pokemon pokemon : caughtInventory.getCaughtInventoryList().keySet()) {
            if (Objects.requireNonNull(caughtInventory.getCaughtInventoryList().get(pokemon)).getCurrentLifeState() > 0 && Objects.requireNonNull(caughtInventory.getCaughtInventoryList().get(pokemon)).getCurrentLifeState() < pokemon.getHp()) {
                aliveCaughtInventory.addPokemon(pokemon);
            }
        }
        return aliveCaughtInventory;
    }

    /**
     * Filter the caught inventory to return only dead pokemon.
     *
     * @param caughtInventory List of all caught Pokemon.
     * @return a caught inventory with only dead pokemon.
     */
    private CaughtInventory deadCaughtInventory(CaughtInventory caughtInventory) {
        CaughtInventory deadCaughtInventory = new CaughtInventory();

        for (Pokemon pokemon : caughtInventory.getCaughtInventoryList().keySet()) {
            // if the pokemon is dead
            if (Objects.requireNonNull(caughtInventory.getCaughtInventoryList().get(pokemon)).getCurrentLifeState() <= 0) {
                deadCaughtInventory.addPokemon(pokemon, Objects.requireNonNull(caughtInventory.getCaughtInventoryList().get(pokemon)));
            }
        }
        return deadCaughtInventory;
    }

    /**
     * Get the filter for the search.
     *
     * @return Filter.
     */
    public Filter getFilter() {
        return SearchedFilter;
    }

    /**
     * Filter for the search.
     */
    private final Filter SearchedFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            CaughtInventory filteredCaughtInventory = new CaughtInventory();

            // If the search is empty, return the full list else return the list filtered.
            if (constraint == null || constraint.length() == 0) {
                filteredCaughtInventory = initCaughtInventory;
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                // If filterPattern contains number filter by id or else filter by name.
                if (filterPattern.matches("[0-9]+")) {
                    int id = Integer.parseInt(filterPattern);
                    for (Pokemon pokemon : caughtInventory.getCaughtInventoryList().keySet()) {
                        if (pokemon.getId() == id) {
                            filteredCaughtInventory.addPokemon(pokemon, Objects.requireNonNull(initCaughtInventory.getCaughtInventoryList().get(pokemon)));
                        }
                    }
                } else {
                    for (Pokemon pokemon : caughtInventory.getCaughtInventoryList().keySet()) {
                        if (pokemon.getName().toLowerCase().contains(filterPattern)) {
                            filteredCaughtInventory.addPokemon(pokemon, Objects.requireNonNull(initCaughtInventory.getCaughtInventoryList().get(pokemon)));
                        }
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredCaughtInventory;

            return results;
        }

        /**
         * Update the list with the filtered list.
         * @param constraint Search.
         * @param results Filtered list.
         */
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            caughtInventory = (CaughtInventory) results.values;
            notifyDataSetChanged();
        }
    };
}



