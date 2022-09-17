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
    private final Pokemon pokemon;
    /**
     * Back Arrow Listener.
     */
    private BackArrowListenerInterface listener;

    /**
     * Constructor.
     * @param pokemon Pokemon
     */
    public PokemonDetailsFragment(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    /**
     * onCreateView.
     * @param inflater inflater
     * @param container container
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
        // Define progress bar color
        binding.pokemonHpValue.getProgressDrawable().setTint(
                pokemon.getBackgroundColor()
        );
        binding.pokemonAttackValue.getProgressDrawable().setTint(
                pokemon.getBackgroundColor()
        );
        binding.pokemonDefenseValue.getProgressDrawable().setTint(
                pokemon.getBackgroundColor()
        );
        binding.pokemonSpattackValue.getProgressDrawable().setTint(
                pokemon.getBackgroundColor()
        );
        binding.pokemonSpdefenseValue.getProgressDrawable().setTint(
                pokemon.getBackgroundColor()
        );
        binding.pokemonSpeedValue.getProgressDrawable().setTint(
                pokemon.getBackgroundColor()
        );
        binding.pokemonTotalValue.getProgressDrawable().setTint(
                pokemon.getBackgroundColor()
        );

        return binding.getRoot();
    }

    /**
     * Set the back arrow listener.
     * @param backArrowListener BackArrowListenerInterface
     */
    public void setBackArrowListenerInterface(BackArrowListenerInterface backArrowListener) {
        this.listener = backArrowListener;
    }
}