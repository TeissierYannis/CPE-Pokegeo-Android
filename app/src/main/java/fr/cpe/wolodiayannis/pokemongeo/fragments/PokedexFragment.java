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
import fr.cpe.wolodiayannis.pokemongeo.adapters.PokemonListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokedexListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.databinding.PokedexFragmentBinding;

/**
 * Pokedex fragment.
 */
public class PokedexFragment extends Fragment {

    /**
     * On click on pokemon listener.
     */
    private PokedexListenerInterface listener;

    /**
     * Datastore instance.
     */
    private Datastore datastore;

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
        PokedexFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.pokedex_fragment, container, false);
        // Set the layout
        binding.pokemonList.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

        // Get the datastore instance
        this.datastore = Datastore.getInstance();

        // new adapter
        PokemonListAdapter adapter = new PokemonListAdapter(this.datastore.getPokemons(), listener);
        // set the adapter
        binding.pokemonList.setAdapter(adapter);

        // search bar : pokedex_search
        binding.pokedexSearch.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return binding.getRoot();
    }

    /**
     * Set the listener.
     * @param listener listener
     */
    public void setPokedexListenerInterface(PokedexListenerInterface listener) {
        this.listener = listener;
    }
}