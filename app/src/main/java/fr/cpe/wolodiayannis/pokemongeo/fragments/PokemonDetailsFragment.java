package fr.cpe.wolodiayannis.pokemongeo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.databinding.PokemonDetailsFragmentBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.listeners.BackArrowListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.viewmodel.PokemonViewModel;

/**
 * Pokemon Details Fragment.
 */
public class PokemonDetailsFragment extends Fragment {

    /**
     * Pokemon.
     */
    private Pokemon pokemon;
    /**
     * Back Arrow Listener.
     */
    private BackArrowListenerInterface listener;

    /**
     * onCreateView.
     *
     * @param inflater           inflater
     * @param container          container
     * @param savedInstanceState savedInstanceState
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        PokemonDetailsFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.pokemon_details_fragment, container, false);
        // Get ViewModel data from the bundle
        PokemonViewModel viewModel = new PokemonViewModel();

        // Set ViewModel to the binding
        binding.setPokemonViewModel(viewModel);
        // Set the pokemon to the ViewModel
        binding.getPokemonViewModel().setPokemon(pokemon);
        // Set event listener on the back arrow
        binding.backArrow.setOnClickListener(v -> listener.onBackArrowClicked());

        // Define the bg color's
        binding.pokemonBg.getBackground().setTint(
                pokemon.getBackgroundColor()
        );
        return binding.getRoot();
    }

    /**
     * Set the back arrow listener.
     *
     * @param backArrowListener BackArrowListenerInterface
     */
    public void setBackArrowListenerInterface(BackArrowListenerInterface backArrowListener) {
        this.listener = backArrowListener;
    }

    /**
     * Set pokemon.
     *
     * @param pokemon Pokemon
     */
    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}