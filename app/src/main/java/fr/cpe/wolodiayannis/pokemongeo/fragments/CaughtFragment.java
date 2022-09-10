package fr.cpe.wolodiayannis.pokemongeo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import fr.cpe.wolodiayannis.pokemongeo.MainActivity;
import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.adapters.PokemonListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.databinding.CaughtFragmentBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokedexListenerInterface;


public class CaughtFragment extends Fragment {

    private PokedexListenerInterface listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        CaughtFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.caught_fragment, container, false);
        binding.caughtList.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

        CaughtInventory caughtInventory = new CaughtInventory();

        caughtInventory.addCaughtPokemon(MainActivity.getPokemonList().get(133), 1);
        caughtInventory.addCaughtPokemon(MainActivity.getPokemonList().get(138), 1);
        caughtInventory.addCaughtPokemon(MainActivity.getPokemonList().get(662), 1);

        PokemonListAdapter adapter = new PokemonListAdapter(caughtInventory.getCaughtPokemon(), listener);

        binding.caughtList.setAdapter(adapter);

        return binding.getRoot();
    }

    public void setPokedexListenerInterface(PokedexListenerInterface listener) {
        this.listener = listener;
    }
}
