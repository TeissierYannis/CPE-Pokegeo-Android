package fr.cpe.wolodiayannis.pokemongeo.viewmodel;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.User;

/**
 * Item View Model.
 */
public class UserViewModel extends BaseObservable {

    private User user;
    private String pokedexCount;

    /**
     * Set the item.
     *
     * @param user the item
     */
    public void setUser(User user) {
        this.user = user;
        notifyChange();
    }

    public void setPokedexCount(String count) {
        this.pokedexCount = count;
        notifyChange();
    }

    @Bindable
    public String getUserPseudo() {
        return user.getPseudo().toUpperCase();
    }
    @Bindable
    public String getUserEmail() {
        return user.getEmail();
    }

    @Bindable
    public String getUserMoney() {
        return String.valueOf(user.getMoney());
    }

    @Bindable
    public String getPokedexCount() {
        return pokedexCount;
    }

    @Bindable
    public int getUserExperience() {
        return user.getExperience();
    }
}
