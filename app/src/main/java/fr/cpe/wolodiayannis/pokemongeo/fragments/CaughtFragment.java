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

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.adapters.CaughtPokemonListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.databinding.CaughtFragmentBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokedexListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokemonSwitchInterface;
import fr.cpe.wolodiayannis.pokemongeo.utils.Sorter;

/**
 * CaughtFragment
 */
public class CaughtFragment extends Fragment {

    /**
     * Listener on click on pokemon
     */
    private PokedexListenerInterface listener;

    /**
     * Listener on pokemon switch (for fight fragment)
     */
    private PokemonSwitchInterface switchListener;

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


        // Bind layout
        CaughtFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.caught_fragment, container, false);
        // set grid layout
        binding.caughtList.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

        CaughtPokemonListAdapter adapter = null;

        CaughtInventory caughtInventorySORT = Datastore.getInstance().getCaughtInventory();
        caughtInventorySORT = Sorter.sortByValue(caughtInventorySORT);

        if (listener == null) {
            adapter = new CaughtPokemonListAdapter(caughtInventorySORT, switchListener);
        }
        if (switchListener == null) {
            adapter = new CaughtPokemonListAdapter(caughtInventorySORT, listener);
        }
        // bind adapter to recycler view
        binding.caughtList.setAdapter(adapter);

        // search bar : pokedex_search
        if (adapter != null) {
            CaughtPokemonListAdapter finalAdapter = adapter;
            binding.caughtinventorySearch.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    finalAdapter.getFilter().filter(newText);
                    return false;
                }
            });
        }

        return binding.getRoot();
    }

    /**
     * set listener
     *
     * @param listener listener
     */
    public void setPokedexListenerInterface(PokedexListenerInterface listener) {
        this.listener = listener;
        this.switchListener = null;
    }

    /**
     * set listener (switch pokemon in fight)
     *
     * @param switchListener switchListener
     */
    public void setSwitchListener(PokemonSwitchInterface switchListener) {
        this.listener = null;
        this.switchListener = switchListener;
    }
}
