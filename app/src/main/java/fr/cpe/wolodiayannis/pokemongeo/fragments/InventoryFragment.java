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
import fr.cpe.wolodiayannis.pokemongeo.adapters.InventoryListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.databinding.InventoryFragmentBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemInventory;
import fr.cpe.wolodiayannis.pokemongeo.listeners.InventoryUseInterface;

/**
 * Inventory Fragment.
 */
public class InventoryFragment extends Fragment {

    /**
     * Listener on item Inventory switch (for fight fragment)
     */
    private InventoryUseInterface itemUseListener;

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

        // Get datastore instance
        /**
         * Datastore instance.
         */
        // Bind layout
        InventoryFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.inventory_fragment, container, false);
        // set grid layout
        binding.inventoryList.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        // new adapter

        ItemInventory itemInventoryPossedItem = (new ItemInventory()).getPossedItems();

        InventoryListAdapter adapter = null;
        adapter = new InventoryListAdapter(itemInventoryPossedItem, itemUseListener);

        // set user money
        binding.inventoryTextviewPokemoney.setText(String.valueOf(Datastore.getInstance().getUser().getMoney()));

        // bind adapter to recycler view
        binding.inventoryList.setAdapter(adapter);

        return binding.getRoot();
    }

    /**
     * Set listener.
     *
     * @param itemUseListener listener
     */
    public void setItemUseListener(InventoryUseInterface itemUseListener) {
        this.itemUseListener = itemUseListener;
    }
}
