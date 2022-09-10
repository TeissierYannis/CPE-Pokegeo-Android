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
import fr.cpe.wolodiayannis.pokemongeo.databinding.InventoryFragmentBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.Inventory;

public class InventoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        InventoryFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.inventory_fragment, container, false);
        binding.inventoryList.setLayoutManager(new StaggeredGridLayoutManager(5, LinearLayoutManager.VERTICAL));

        InventoryListAdapter adapter = new InventoryListAdapter(
                new Inventory(),
                getContext()
        );
        binding.inventoryList.setAdapter(adapter);

        return binding.getRoot();
    }
}
