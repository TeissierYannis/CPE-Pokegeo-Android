package fr.cpe.wolodiayannis.pokemongeo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.databinding.InventoryItemBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.Inventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.viewmodel.ItemViewModel;

public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.ViewHolder> {

    private final Inventory inventory;
    private final Context context;

    public InventoryListAdapter(Inventory inventory, Context context) {
        this.inventory = inventory;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InventoryItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.inventory_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Item itemToAdd = inventory.getItem(position) == null ?
                Item.CREATE(
                        "",
                        "",
                        context.getApplicationContext().getResources().getIdentifier(
                                "rounded_rect_shape",
                                "drawable",
                                context.getApplicationContext().getPackageName()
                        )

                ) :
                inventory.getItem(position);

        holder.viewModel.setItem(
                itemToAdd
        );
    }

    @Override
    public int getItemCount() {
        return inventory.getMaxItems();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private final InventoryItemBinding binding;
        private final ItemViewModel viewModel = new ItemViewModel();

        ViewHolder(InventoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setItemViewModel(viewModel);
        }
    }
}



