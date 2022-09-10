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

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> implements Filterable {
    private final PokedexListenerInterface listener;
    private final List<Pokemon> pokemonList;
    private final ArrayList<Pokemon> dataset;

    public PokemonListAdapter(List<Pokemon> pokemonList, PokedexListenerInterface listener) {
        assert pokemonList != null;
        this.pokemonList = pokemonList;
        this.listener = listener;
        this.dataset = new ArrayList<>();
        this.dataset.addAll(pokemonList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.pokemon_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = dataset.get(position);
        holder.viewModel.setPokemon(pokemon);

        holder.binding.getRoot().setOnClickListener(v -> listener.onPokemonSelected(pokemon));

        holder.binding.pokemonBg.getBackground().setTint(
                pokemon.getColor()
        );
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public Filter getFilter() {
        return SearchedFilter;
    }

    private final Filter SearchedFilter = new Filter() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Pokemon> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(pokemonList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                // If filterPattern contains number filter by id
                if (filterPattern.matches("[0-9]+")) {
                    int id = Integer.parseInt(filterPattern);
                    pokemonList.stream()
                            .filter(pokemon -> pokemon.getID() == id)
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

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataset.clear();
            dataset.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

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



