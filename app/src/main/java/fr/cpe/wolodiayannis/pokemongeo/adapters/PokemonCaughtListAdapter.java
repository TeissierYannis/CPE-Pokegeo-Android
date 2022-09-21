package fr.cpe.wolodiayannis.pokemongeo.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.databinding.PokemonItemBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokedexListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.viewmodel.PokemonViewModel;

/**
 * Adapter for the list of Pokemon.
 */
public class PokemonCaughtListAdapter extends RecyclerView.Adapter<PokemonCaughtListAdapter.ViewHolder> {
    /**
     * Listener for the click on a Pokemon.
     */
    private final PokedexListenerInterface listener;
    /**
     * List of Pokemon.
     */
    private final CaughtInventory caughtInventory;
    /**
     * List of Pokemon use for the search.
     */
    private final HashMap<Pokemon, Integer> dataset;

    /**
     * Constructor.
     * @param caughtInventory List of Pokemon.
     * @param listener Listener for the click on a Pokemon.
     */
    public PokemonCaughtListAdapter(CaughtInventory caughtInventory, PokedexListenerInterface listener) {
        this.caughtInventory = caughtInventory;
        this.listener = listener;
        this.dataset = new HashMap<Pokemon, Integer>();
        this.dataset.putAll(caughtInventory.getCaughtInventoryList());
    }

    /**
     * Create a new ViewHolder.
     * @param parent Parent ViewGroup.
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
     * @param holder ViewHolder.
     * @param position Position of the Pokemon in the list.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pokemonID = caughtInventory.getCaughtInventoryList().get(position);

        if (pokemonID == 0) {
            return;
        }

        Pokemon pokemon = caughtInventory.getPokemonById(pokemonID);
        holder.viewModel.setPokemon(pokemon);

        // Set the listener for the click on the Pokemon.
        holder.binding.getRoot().setOnClickListener(v -> listener.onPokemonSelected(pokemon));

        // Set the color of the pokemon bg.
        holder.binding.pokemonBg.getBackground().setTint(
                pokemon.getBackgroundColor()
        );
    }

    /**
     * Get the number of Pokemon in the list.
     * @return Number of Pokemon.
     */
    @Override
    public int getItemCount() {
        return dataset.size();
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



