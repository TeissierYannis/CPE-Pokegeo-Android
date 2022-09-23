package fr.cpe.wolodiayannis.pokemongeo.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.List;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.MainActivity;
import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonStat;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.StatList;

/**
 * Pokemon View Model.
 */
public class PokemonViewModel extends BaseObservable {
    private Pokemon pokemon = null;

    /**
     * set pokemon.
     *
     * @param pokemon pokemon
     */
    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
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
                // return drawable ID
                @SuppressLint("DefaultLocale")
                int drawableID =
                        context
                                .getResources()
                                .getIdentifier(
                                        "p" + String.format("%03d", this.pokemon.getId()),
                                        "drawable",
                                        context.getPackageName()
                                );

                return ResourcesCompat.getDrawable(context.getResources(), drawableID, null);
            } catch (Exception e) {
                return null;
            }
        else
            return null;
    }

    /**
     * Get the pokemon id.
     *
     * @return String.
     */
    @Bindable
    public String getID() {
        return ("#" + pokemon.getId());
    }

    /**
     * Get the pokemon name.
     *
     * @return String.
     */
    @Bindable
    public String getName() {
        return pokemon.getName();
    }

    /**
     * Get the pokemon height.
     *
     * @return String.
     */
    @Bindable
    public String getHeight() {
        return (pokemon.getHeight() + " m");
    }

    /**
     * Get the pokemon weight.
     *
     * @return String.
     */
    @Bindable
    public String getWeight() {
        return (pokemon.getWeight() + " kg");
    }

    /**
     * Get the pokemon abilities.
     *
     * @return String.
     */
    @Bindable
    public String getAbilities() {
        // convert abilities to string
        StringBuilder abilities = new StringBuilder();
        // TODO Rework that

        if (pokemon.getAbilities() != null) {
            List<Ability> ability = MainActivity.getDataList().getAbilities();
            for (Integer abilityID : pokemon.getAbilities()) {
                abilities.append(ability.get(abilityID).getName()).append(", ");
            }
        }
        // remove last ", "
        if (abilities.length() > 2)
            abilities.delete(abilities.length() - 2, abilities.length());
        return abilities.toString().replaceAll("_", " ");
    }

    /**
     * Get the pokemon stats.
     *
     * @return List of Stats.
     */
    @Bindable
    public List<PokemonStat> getStats() {
        return pokemon.getStats();
    }

    /**
     * Get the pokemon evolution.
     *
     * @return List of Pokemon.
     */
    @Bindable
    public int getEvolutionChainId() {
        return pokemon.getEvolutionChainId();
    }

    /**
     * Get the pokemon description.
     *
     * @return String.
     */
    @Bindable
    public String getDescription() {
        return pokemon.getDescription();
    }

    /**
     * Get the pokemon gen.
     *
     * @return int.
     */
    @Bindable
    public int getGenerationId() {
        return pokemon.getGenerationId();
    }

    /**
     * Get the pokemon color.
     *
     * @return int
     */
    @Bindable
    public int getColor() {
        return pokemon.getBackgroundColor();
    }
}
