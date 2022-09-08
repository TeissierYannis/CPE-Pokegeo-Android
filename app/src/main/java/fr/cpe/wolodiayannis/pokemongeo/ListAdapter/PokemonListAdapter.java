package fr.cpe.wolodiayannis.pokemongeo.ListAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.Entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.databinding.PokemonItemBinding;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {
    List<Pokemon> pokemonList;

    public PokemonListAdapter(List<Pokemon> pokemonList) {
        assert pokemonList != null;
        this.pokemonList = pokemonList;
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
        Pokemon pokemon = pokemonList.get(position);

        holder.binding.pokemonImage.setImageResource(pokemon.getFrontResource());
        holder.binding.pokemonName.setText(pokemon.getName());
        String number = "#" + pokemon.getID();
        holder.binding.pokemonId.setText(number);

        holder.binding.pokemonBg.getBackground().setTint(
                pokemon.getColor()
        );
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final PokemonItemBinding binding;

        ViewHolder(PokemonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
