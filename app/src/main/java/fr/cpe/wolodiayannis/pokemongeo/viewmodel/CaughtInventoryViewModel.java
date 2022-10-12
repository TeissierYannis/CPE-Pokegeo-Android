package fr.cpe.wolodiayannis.pokemongeo.viewmodel;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BaseObservable;

import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;

public class CaughtInventoryViewModel extends BaseObservable {

    private CaughtInventory caughtInventory = null;

    private Datastore datastore;

    /**
     * set inventory.
     * @param caughtInventory caughtInventory
     */
    public void setCaughtInventory(CaughtInventory caughtInventory) {
        this.datastore = Datastore.getInstance();
        this.caughtInventory = caughtInventory;
        notifyChange();
    }

    /**
     * Get the pokemon image.
     *
     * @param context Context.
     * @param res Resource.
     * @return Drawable.
     */
    public Drawable getImage(Context context, int res) {
        if (res != -1)
            try {
                return ResourcesCompat.getDrawable(context.getResources(), res, null);
            } catch (Exception e) {
                return null;
            }
        else
            return null;
    }

    /**
     * Get the caught pokemon from the inventory.
     * @param id pokemon id into the inventory.
     */
    public void getCaughtPokemon(int id) {
        // TODO
    }

}
