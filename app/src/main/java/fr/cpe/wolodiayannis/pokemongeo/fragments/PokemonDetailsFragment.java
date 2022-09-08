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
import fr.cpe.wolodiayannis.pokemongeo.viewmodel.PokemonViewModel;

public class PokemonDetailsFragment extends Fragment {

    private Pokemon pokemon;

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
        viewModel.setPokemon(pokemon);

        // Set ViewModel to the binding
        binding.setPokemonViewModel(viewModel);



        return binding.getRoot();
    }
}