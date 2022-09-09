package fr.cpe.wolodiayannis.pokemongeo.listadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
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
        return pokemonList.size();
    }

    @Override
    public Filter getFilter() {
        return SearchedFilter;
    }

    private final Filter SearchedFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Pokemon> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(pokemonList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Pokemon pokemon : pokemonList) {
                    if (pokemon.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(pokemon);
                    }
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



