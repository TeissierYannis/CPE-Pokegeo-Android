package fr.cpe.wolodiayannis.pokemongeo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.databinding.PokemonDetailsBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.listeners.BackArrowListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokedexListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.viewmodel.PokemonViewModel;

public class PokemonDetailsFragment extends Fragment {

    private final Pokemon pokemon;
    private BackArrowListenerInterface listener;

    public PokemonDetailsFragment(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        PokemonDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.pokemon_details, container, false);

        // Get ViewModel data from the bundle
        PokemonViewModel viewModel = new PokemonViewModel();
        // Set ViewModel to the binding
        binding.setPokemonViewModel(viewModel);
        binding.backArrow.setOnClickListener(v -> listener.onBackArrowClicked());
        // Set the pokemon to the ViewModel
        binding.getPokemonViewModel().setPokemon(pokemon);

        binding.pokemonBg.getBackground().setTint(
                pokemon.getColor()
        );

        binding.pokemonHpValue.getProgressDrawable().setTint(
                pokemon.getColor()
        );
        binding.pokemonAttackValue.getProgressDrawable().setTint(
                pokemon.getColor()
        );
        binding.pokemonDefenseValue.getProgressDrawable().setTint(
                pokemon.getColor()
        );
        binding.pokemonSpattackValue.getProgressDrawable().setTint(
                pokemon.getColor()
        );
        binding.pokemonSpdefenseValue.getProgressDrawable().setTint(
                pokemon.getColor()
        );
        binding.pokemonSpeedValue.getProgressDrawable().setTint(
                pokemon.getColor()
        );
        binding.pokemonTotalValue.getProgressDrawable().setTint(
                pokemon.getColor()
        );

        return binding.getRoot();
    }

    public void setBackArrowListenerInterface(BackArrowListenerInterface backArrowListener) {
        this.listener = backArrowListener;
    }
}