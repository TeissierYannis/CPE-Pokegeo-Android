package fr.cpe.wolodiayannis.pokemongeo.viewmodel;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;

/**
 * Item View Model.
 */
public class ItemViewModel extends BaseObservable {

    /**
     * Item.
     */
    private Item item;

    /**
     * Datastore instance.
     */
    private Datastore datastore;

    /**
     * Set the item.
     *
     * @param item the item
     */
    public void setItem(Item item) {
        this.datastore = Datastore.getInstance();
        this.item = item;
        notifyChange();
    }

    /**
     * Get the item image.
     *
     * @param context Context.
     * @param res     Resource.
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
     *
     * @return int.
     */
    @Bindable
    public int getFront() {
        return item.getImageID();
    }

    /**
     * Get the item name.
     *
     * @return String.
     */
    @Bindable
    public String getName() {
        return item.getName();
    }

    /**
     * Get the item quantity.
     *
     * @return String.
     */
    @Bindable
    public String getQuantity() {
        return String.valueOf(datastore.getItemInventory().getQuantity(item));
    }
}
