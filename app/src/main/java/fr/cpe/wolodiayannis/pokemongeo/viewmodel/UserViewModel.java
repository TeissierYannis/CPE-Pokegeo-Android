package fr.cpe.wolodiayannis.pokemongeo.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import fr.cpe.wolodiayannis.pokemongeo.entity.user.User;

/**
 * Item View Model.
 */
public class UserViewModel extends BaseObservable {

    /**
     * User.
     */
    private User user;

    /**
     * Pokedex Count.
     */
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

    /**
     * Set pokedex count.
     *
     * @param count count
     */
    public void setPokedexCount(String count) {
        this.pokedexCount = count;
        notifyChange();
    }

    /**
     * Get the item name.
     *
     * @return String.
     */
    @Bindable
    public String getUserPseudo() {
        return user.getPseudo().toUpperCase();
    }

    /**
     * Get the item name.
     *
     * @return String.
     */
    @Bindable
    public String getUserEmail() {
        return user.getEmail();
    }

    /**
     * Get the item name.
     *
     * @return String.
     */
    @Bindable
    public String getUserMoney() {
        return String.valueOf(user.getMoney());
    }

    /**
     * Get the item name.
     *
     * @return String.
     */
    @Bindable
    public String getPokedexCount() {
        return pokedexCount;
    }

    /**
     * Get the item name.
     *
     * @return String.
     */
    @Bindable
    public int getUserExperience() {
        return user.getExperience();
    }
}
