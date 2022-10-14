package fr.cpe.wolodiayannis.pokemongeo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import fr.cpe.wolodiayannis.pokemongeo.listeners.InventoryListenerInterfaceInventory;
import fr.cpe.wolodiayannis.pokemongeo.listeners.InventoryListenerInterfaceFight;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokemonSwitchInterface;

/**
 * Inventory Fragment.
 */
public class InventoryFragment extends Fragment {

    /**
     * Listener on item Inventory switch (for fight fragment)
     */
    private InventoryListenerInterfaceFight itemListenerFight;

    /**
     * Listener on item inventory for inventory fragment
     */
    private InventoryListenerInterfaceInventory inventoryListenerInv;

    private Item itemToUse;
    private CaughtPokemon caughtPokemon;

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

        InventoryListAdapter adapter = null;
        if (itemListenerFight != null) {
            adapter = new InventoryListAdapter(itemInventoryPossedItem, itemListenerFight);
        } else {
            inventoryListenerInv = this::useItem;
            adapter = new InventoryListAdapter(itemInventoryPossedItem, inventoryListenerInv);
        }

        // set user money
        binding.inventoryTextviewPokemoney.setText(String.valueOf(Datastore.getInstance().getUser().getMoney()));

        // bind adapter to recycler view
        binding.inventoryList.setAdapter(adapter);

        return binding.getRoot();
    }

    /**
     * Set listener.
     *
     * @param itemListenerFight listener
     */
    public void setItemListenerFight(InventoryListenerInterfaceFight itemListenerFight) {
        this.itemListenerFight = itemListenerFight;
    }

    /**
     * Use item.
     *
     * @param item item
     */
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

    /**
     * Handle how to use heal item.
     *
     * @param cp      caught pokemon
     * @param pokemon pokemon
     */
    private void useHealItem(Pokemon pokemon, CaughtPokemon cp) {
        this.caughtPokemon = cp;
        // if the user try to use a potion on a dead pokemon
        if (caughtPokemon != null && caughtPokemon.getCurrentLifeState() <= 0 && itemToUse instanceof ItemPotion) {
            Toast.makeText(requireContext(), "You can't use a potion on a dead pokemon", Toast.LENGTH_SHORT).show();
        }
        // if the user try to use a revive on a alive pokemon
        else if (caughtPokemon != null && caughtPokemon.getCurrentLifeState() > 0 && itemToUse instanceof ItemRevive) {
            Toast.makeText(requireContext(), "You can't use a revive on a alive pokemon", Toast.LENGTH_SHORT).show();
        } else {
            this.useItem();
        }
    }

    /**
     * Use item.
     */
    private void useItem() {
        if (itemToUse instanceof ItemPotion) {

            int maxPokemonLife = this.caughtPokemon.getCorrespondingPokemon().getHp();
            int currentLife = this.caughtPokemon.getCurrentLifeState();

            // if the pokemon is already at max life
            if (currentLife == maxPokemonLife) {
                Toast.makeText(requireContext(), "This pokemon is already at max life", Toast.LENGTH_SHORT).show();
            } else {
                // if the pokemon is dead
                if (currentLife <= 0) {
                    Toast.makeText(requireContext(), "This pokemon is dead", Toast.LENGTH_SHORT).show();
                } else {

                    int newLife = Math.min(currentLife + ((ItemPotion) itemToUse).getBonus(), maxPokemonLife);

                    // if the pokemon is not dead and not at max life
                    Objects.requireNonNull(Datastore.getInstance().getCaughtInventory().getCaughtInventoryList().get(caughtPokemon.getCorrespondingPokemon()))
                            .setCurrentLifeState(
                                    newLife
                            );
                }

                Toast.makeText(requireContext(), "The potion worked", Toast.LENGTH_SHORT).show();
            }

        } else if (itemToUse instanceof ItemRevive) {
            Objects.requireNonNull(Datastore.getInstance().getCaughtInventory().getCaughtInventoryList().get(caughtPokemon.getCorrespondingPokemon())).
                    setCurrentLifeState(((ItemRevive) itemToUse).getExactHpToHeal(caughtPokemon.getCorrespondingPokemon()));
        }

        // go back to this fragment and re-update bar
        requireActivity().getSupportFragmentManager().popBackStack();
        Datastore.getInstance().getItemInventory().removeItem(itemToUse, 1);
        new Thread(() ->
                (new ItemInventoryFetcher(getContext())).updateAndCache(Datastore.getInstance().getItemInventory())).start();
        new Thread(() ->
                (new CaughtInventoryFetcher(getContext())).updatePokemonAndCache(caughtPokemon)).start();
    }
}
