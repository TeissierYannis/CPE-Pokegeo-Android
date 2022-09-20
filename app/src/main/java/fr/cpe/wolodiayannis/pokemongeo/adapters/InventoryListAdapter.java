package fr.cpe.wolodiayannis.pokemongeo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.databinding.InventoryItemBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.ItemInventory;
import fr.cpe.wolodiayannis.pokemongeo.viewmodel.ItemViewModel;

/**
 * Adapter for the inventory list.
 */
public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.ViewHolder> {

    /**
     * The inventory.
     */
    private final ItemInventory itemsInventory;
    /**
     * The context.
     */
    private final Context context;

    /**
     * Constructor.
     * @param itemsInventory the inventory
     * @param context the context
     */
    public InventoryListAdapter(ItemInventory itemsInventory, Context context) {
        this.itemsInventory = itemsInventory;
        this.context = context;
    }

    /**
     * @{inheritDoc}
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
     * @{inheritDoc}
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Get the item if item do not exist create empty one

        // TODO
        Item itemToAdd = new Item(0, "default");

        // Set the item to the view model
        holder.viewModel.setItem(
                itemToAdd
        );
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public int getItemCount() {
        return 1;
    }


    /**
     * The view holder.
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



