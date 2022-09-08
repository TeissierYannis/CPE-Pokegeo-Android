package fr.cpe.wolodiayannis.pokemongeo.ViewModel;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.Entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.Entity.Stats;
import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_ABILITIES;
import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_TYPE;

public class PokemonViewModel extends BaseObservable {
    private Pokemon pokemon = new Pokemon();

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        notifyChange();
    }

    public Drawable getImage(Context context, int res) {
        if(res != -1)
            return ResourcesCompat.getDrawable(context.getResources(),
                    res, context.getTheme());
        else
            return null;
    }

    @Bindable
    public int getFront() { return pokemon.getFrontResource(); }
    @Bindable
    public int getID() {
        return pokemon.getID();
    }
    @Bindable
    public String getName() {
        return pokemon.getName();
    }
    @Bindable
    public String getSpecies() {
        return pokemon.getSpecies();
    }
    @Bindable
    public List<POKEMON_TYPE> getTypes() {
        return pokemon.getTypes();
    }
    @Bindable
    public float getHeight() {
        return pokemon.getHeight();
    }
    @Bindable
    public float getWeight() {
        return pokemon.getWeight();
    }
    @Bindable
    public List<POKEMON_ABILITIES> getAbilities() {
        return pokemon.getAbilities();
    }
    @Bindable
    public Stats getStats() {
        return pokemon.getStats();
    }
    @Bindable
    public List<Pokemon> getEvolutions() {
        return pokemon.getEvolutions();
    }
    @Bindable
    public String getDescription() {
        return pokemon.getDescription();
    }
    @Bindable
    public int getGen() {
        return pokemon.getGen();
    }
    @Bindable
    public int getColor() {
        return pokemon.getColor();
    }
}
