package fr.cpe.wolodiayannis.pokemongeo.viewmodel;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import fr.cpe.wolodiayannis.pokemongeo.entity.Item;

public class ItemViewModel extends BaseObservable {

    private Item item;

    public void setItem(Item item) {
        this.item = item;
    }

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

    @Bindable
    public int getFront() {
        return item.getFrontResource();
    }

    @Bindable
    public String getName() {
        return item.getName();
    }

    @Bindable
    public String getDescription() {
        return item.getDescription();
    }

    @Bindable
    public String getQuantity() {
        return item.getQuantity() == 0 ? "" : String.valueOf(item.getQuantity());
    }
}
