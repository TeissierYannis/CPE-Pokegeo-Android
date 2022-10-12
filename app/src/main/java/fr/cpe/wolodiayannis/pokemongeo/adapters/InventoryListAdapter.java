package fr.cpe.wolodiayannis.pokemongeo.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.databinding.InventoryItemBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemPotion;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemRevive;
import fr.cpe.wolodiayannis.pokemongeo.listeners.InventoryListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.listeners.InventoryUseInterface;
import fr.cpe.wolodiayannis.pokemongeo.viewmodel.ItemViewModel;

/**
 * Adapter for the inventory list.
 */
public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.ViewHolder> {

    /**
     * Listener for the click on an item.
     */
    private final InventoryListenerInterface listener;

    /**
     * Listener for to use an item.
     */
    private final InventoryUseInterface useListener;

    /**
     * List of item inventory.
     */
    private ItemInventory itemInventory;

    /**
     * Constructor.
     *
     * @param itemInventory List of item inventory.
     * @param listener      Listener for the click on an item.
     */
    public InventoryListAdapter(ItemInventory itemInventory, InventoryListenerInterface listener) {
        this.itemInventory = itemInventory;
        this.listener = listener;
        this.useListener = null;
    }

    /**
     * Constructor.
     *
     * @param itemInventory List of item inventory.
     * @param listener      Listener for the click on an item.
     */
    public InventoryListAdapter(ItemInventory itemInventory, InventoryUseInterface listener) {
        this.itemInventory = itemInventory;
        this.listener = null;
        this.useListener = listener;
    }

    /**
     * Create a new ViewHolder.
     *
     * @param parent   Parent ViewGroup.
     * @param viewType ViewType.
     * @return ViewHolder.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InventoryItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.inventory_item, parent, false);

        return new ViewHolder(binding);
    }

    /**
     * Bind the ViewHolder.
     *
     * @param holder   ViewHolder.
     * @param position Position.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Get the item if item do not exist create empty one
        Item item = itemInventory.getItem(position);
        holder.viewModel.setItem(item);


        if (useListener != null) {
            // set the listener for the click on the item
            holder.binding.getRoot().setOnClickListener(v -> {
                Item itm = Datastore.getInstance().getItemInventory().getItem(position);
                CaughtPokemon cp = null;

                if (itm instanceof ItemPotion) {
                    // TODO show caught pokemon frag and get a targ
                } else if (itm instanceof ItemRevive) {
                    // TODO show caught pokemon frag and get a targ
                }

                useListener.onItemInventorySwitch((Item) itm, (CaughtPokemon) cp);
            });
        }

        // Set the color of the item bg.
        holder.binding.inventoryBg.getBackground().setTint(
                ContextCompat.getColor(
                        holder.binding.getRoot().getContext(),
                        item.getBackgroundColor()
                )
        );

        // Set the ItemViewModel to the layout.
        holder.binding.getItemViewModel().setItem(item);
    }

    /**
     * Get the number of items in the inventory.
     *
     * @return the number of items in the inventory.
     */
    @Override
    public int getItemCount() {
        return itemInventory.size();
    }

    /**
     * ViewHolder for the inventory list.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final InventoryItemBinding binding;
        // create
        private final ItemViewModel viewModel = new ItemViewModel();

        ViewHolder(InventoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            // set the view model
            this.binding.setItemViewModel(viewModel);
        }
    }
}



