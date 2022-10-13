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

import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.adapters.InventoryListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.databinding.InventoryFragmentBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemBall;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemPotion;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemRevive;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.CaughtInventoryFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.ItemInventoryFetcher;
import fr.cpe.wolodiayannis.pokemongeo.listeners.InventoryUseInterface;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokemonSwitchInterface;

/**
 * Inventory Fragment.
 */
public class InventoryFragment extends Fragment {

    /**
     * Listener on item Inventory switch (for fight fragment)
     */
    private InventoryUseInterface itemUseListener;

    private Item itemToUse;

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
        InventoryFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.inventory_fragment, container, false);
        // set grid layout
        binding.inventoryList.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        // new adapter

        ItemInventory itemInventoryPossedItem = (new ItemInventory()).getPossedItems();

        itemUseListener = this::useItem;
        InventoryListAdapter adapter = new InventoryListAdapter(itemInventoryPossedItem, itemUseListener);

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

    private void useItem(Item item) {
        System.out.println("Use item " + item.getName());

        if (!(item instanceof ItemBall)) {

            this.itemToUse = item;
            PokemonSwitchInterface pokemonSwitchInterface = this::useHealItem;

            CaughtFragment caughtFragment = new CaughtFragment();
            caughtFragment.setSwitchListener(pokemonSwitchInterface);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, caughtFragment)
                    .addToBackStack("fight")
                    .setReorderingAllowed(true)
                    .commit();
        }
    }

    private void useHealItem(Pokemon pokemon, CaughtPokemon cp) {

        if (itemToUse instanceof ItemPotion) {
            Objects.requireNonNull(Datastore.getInstance().getCaughtInventory().getCaughtInventoryList().get(cp.getCorrespondingPokemon()))
                    .setCurrentLifeState(cp.getCurrentLifeState() + ((ItemPotion) itemToUse).getBonus());

        } else if (itemToUse instanceof ItemRevive) {
            Objects.requireNonNull(Datastore.getInstance().getCaughtInventory().getCaughtInventoryList().get(cp.getCorrespondingPokemon())).
                    setCurrentLifeState(((ItemRevive) itemToUse).getExactHpToHeal(cp.getCorrespondingPokemon()));
        }
        // go back to this fragment and re-update bar
        requireActivity().getSupportFragmentManager().popBackStack();
        Datastore.getInstance().getItemInventory().removeItem(itemToUse, 1);
        new Thread(() -> {
            (new ItemInventoryFetcher(getContext())).updateAndCache(Datastore.getInstance().getItemInventory());
        }).start();
        new Thread(() -> {
            (new CaughtInventoryFetcher(getContext())).updatePokemonAndCache(cp);
        }).start();
    }
}
