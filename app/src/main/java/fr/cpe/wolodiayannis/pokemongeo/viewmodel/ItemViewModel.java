package fr.cpe.wolodiayannis.pokemongeo.viewmodel;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.entity.Item;

/**
 * Item View Model.
 */
public class ItemViewModel extends BaseObservable {

    /**
     * Item.
     */
    private Item item;

    /**
     * Set the item.
     * @param item Item.
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Get the item image.
     * @param context Context.
     * @param res Resource.
     * @return Drawable.
     */
    public Drawable getImage(Context context, int res) {
        if (res != -1)
            try {
                return ResourcesCompat.getDrawable(context.getResources(),
                        res, context.getTheme());
            } catch (Exception e) {
                return null;
            }
        else
            return null;
    }

    /**
     * Get the item front res.
     * @return int.
     */
    @Bindable
    public int getFront() {
        // placeholder
        return -1;
    }

    /**
     * Get the item name.
     * @return String.
     */
    @Bindable
    public String getName() {
        return item.getName();
    }

    /**
     * Get the item description.
     * @return String.
     */
    @Bindable
    public String getDescription() {
        return "desc";
    }

    /**
     * Get the item quantity.
     * @return String.
     */
    @Bindable
    public String getQuantity() {
        return Objects.equals(item.getName(), "") ? "" : String.valueOf(1);
    }
}
