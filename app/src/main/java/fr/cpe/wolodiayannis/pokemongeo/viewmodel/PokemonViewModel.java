package fr.cpe.wolodiayannis.pokemongeo.viewmodel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;

/**
 * Pokemon View Model.
 */
public class PokemonViewModel extends BaseObservable {
    private Pokemon pokemon = new Pokemon();

    /**
     * set pokemon.
     * @param pokemon pokemon
     */
    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        notifyChange();
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
     * Get the pokemon front res.
     * @return int.
     */
    @Bindable
    public int getFront() {
        return pokemon.getFrontResource();
    }

    /**
     * Get the pokemon id.
     * @return String.
     */
    @Bindable
    public String getID() {
        return ("#" + pokemon.getId());
    }

    /**
     * Get the pokemon name.
     * @return String.
     */
    @Bindable
    public String getName() {
        return pokemon.getName();
    }

    /**
     * Get the pokemon types.
     * @return List of POKEMON_TYPE.
     */
    @Bindable
    public List<Type> getTypes() {
        return pokemon.getTypes();
    }

    /**
     * Get the pokemon height.
     * @return String.
     */
    @Bindable
    public String getHeight() {
        return (pokemon.getHeight() + " m");
    }

    /**
     * Get the pokemon weight.
     * @return String.
     */
    @Bindable
    public String getWeight() {
        return (pokemon.getWeight() + " kg");
    }

    /**
     * Get the pokemon abilities.
     * @return String.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Bindable
    public String getAbilities() {
        // convert abilities to string
        StringBuilder abilities = new StringBuilder();
        pokemon.getAbilities().forEach(ability -> abilities.append(ability.toString()).append(", "));
        // remove last ", "
        abilities.delete(abilities.length() - 2, abilities.length());
        return abilities.toString().replaceAll("_", " ");
    }

    /**
     * Get the pokemon stats.
     * @return List of Stats.
     */
    @Bindable
    public List<Stat> getStats() {
        return pokemon.getStats();
    }


    /**
     * Get the pokemon description.
     * @return String.
     */
    @Bindable
    public String getDescription() {
        return pokemon.getDescription();
    }

    /**
     * Get the pokemon gen.
     * @return int.
     */
    @Bindable
    public int getGeneration() {
        return pokemon.getGeneration();
    }

    /**
     * Get the pokemon color.
     * @return int
     */
    @Bindable
    public int getColor() {
        return pokemon.getBackgroundColor();
    }

}
