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
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.databinding.PokemonItemBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokedexListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.viewmodel.PokemonViewModel;

/**
 * Adapter for the list of Pokemon.
 */
public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> implements Filterable {
    /**
     * Listener for the click on a Pokemon.
     */
    private final PokedexListenerInterface listener;
    /**
     * List of Pokemon.
     */
    private final List<Pokemon> pokemonList;
    /**
     * List of Pokemon use for the search.
     */
    private final ArrayList<Pokemon> dataset;

    /**
     * Constructor.
     * @param pokemonList List of Pokemon.
     * @param listener Listener for the click on a Pokemon.
     */
    public PokemonListAdapter(List<Pokemon> pokemonList, PokedexListenerInterface listener) {
        this.pokemonList = pokemonList;
        this.listener = listener;
        this.dataset = new ArrayList<>();
        this.dataset.addAll(pokemonList);
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
        Pokemon pokemon = dataset.get(position);
        holder.viewModel.setPokemon(pokemon);

        // Set the listener for the click on the Pokemon.
        holder.binding.getRoot().setOnClickListener(v -> listener.onPokemonSelected(pokemon));

        // Set the color of the pokemon bg.
        holder.binding.pokemonBg.getBackground().setTint(
                pokemon.getColor()
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
     * Get the filter for the search.
     * @return Filter.
     */
    @Override
    public Filter getFilter() {
        return SearchedFilter;
    }

    /**
     * Filter for the search.
     */
    private final Filter SearchedFilter = new Filter() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Pokemon> filteredList = new ArrayList<>();

            // If the search is empty, return the full list else return the list filtered.
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(pokemonList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                // If filterPattern contains number filter by id or else filter by name.
                if (filterPattern.matches("[0-9]+")) {
                    int id = Integer.parseInt(filterPattern);
                    pokemonList.stream()
                            .filter(pokemon -> pokemon.getId() == id)
                            .forEach(filteredList::add);
                } else {
                    pokemonList.stream()
                            .filter(pokemon -> pokemon.getName().toLowerCase().contains(filterPattern))
                            .forEach(filteredList::add);
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        /**
         * Update the list with the filtered list.
         * @param constraint Search.
         * @param results Filtered list.
         */
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataset.clear();
            dataset.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
    
    

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



