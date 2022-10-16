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

    @Bindable
    public String getPlayingTime() {
        // TIme diff in minutes
        long diff = System.currentTimeMillis() - user.getCreatedAt().getTime();

        long diffMinutes = diff / (60 * 1000) % 60;

        return diffMinutes + " minutes";
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
     * Get the user experience.
     *
     * @return int experience.
     */
    public int getUserExperience() {
        return user.getExperience();
    }

    /**
     * Get the user experience string.
     *
     * @return String experience.
     */
    @Bindable
    public String getUserExperienceString() {
        return String.valueOf(user.getExperience());
    }
}
