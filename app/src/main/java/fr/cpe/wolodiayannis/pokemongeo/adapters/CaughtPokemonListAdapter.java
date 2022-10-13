package fr.cpe.wolodiayannis.pokemongeo.adapters;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

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
import fr.cpe.wolodiayannis.pokemongeo.listeners.InventoryUseInterface;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokedexListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokemonSwitchInterface;
import fr.cpe.wolodiayannis.pokemongeo.viewmodel.PokemonViewModel;

/**
 * Adapter for the list of Pokemon.
 */
public class CaughtPokemonListAdapter extends RecyclerView.Adapter<CaughtPokemonListAdapter.ViewHolder> {
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
    private final CaughtInventory caughtInventory;


    /**
     * Constructor.
     *
     * @param caughtInventory List of Pokemon.
     * @param listener        Listener for the click on a Pokemon.
     */
    public CaughtPokemonListAdapter(CaughtInventory caughtInventory, PokedexListenerInterface listener) {
        this.listener = listener;
        this.caughtInventory = caughtInventory;
        this.switchListener = null;
    }

    public CaughtPokemonListAdapter(CaughtInventory caughtInventory, PokemonSwitchInterface listener) {
        this.listener = null;
        this.caughtInventory = caughtInventory;
        this.switchListener = listener;
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
        holder.binding.pokemonBg.getBackground().

                setTint(
                        pokemon.getBackgroundColor()
                );

        // Set the PokemonViewModel to the layout.
        holder.binding.getPokemonViewModel().

                setPokemon(pokemon);
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
        private final PokemonViewModel viewModel = new PokemonViewModel();

        ViewHolder(PokemonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setPokemonViewModel(viewModel);
        }
    }
}



