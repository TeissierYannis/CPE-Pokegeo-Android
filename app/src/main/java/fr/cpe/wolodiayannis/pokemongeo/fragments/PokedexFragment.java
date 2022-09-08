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
import fr.cpe.wolodiayannis.pokemongeo.listadapter.PokemonListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokedexListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.databinding.PokedexFragmentBinding;

public class PokedexFragment extends Fragment {

    private PokedexListenerInterface listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        PokedexFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.pokedex_fragment, container, false);
        binding.pokemonList.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

        PokemonListAdapter adapter = new PokemonListAdapter(MainActivity.getPokemonList(), listener);

        binding.pokemonList.setAdapter(adapter);

        return binding.getRoot();
    }

    public void setPokedexListenerInterface(PokedexListenerInterface listener) {
        this.listener = listener;
    }
}