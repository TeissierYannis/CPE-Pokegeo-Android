package fr.cpe.wolodiayannis.pokemongeo.viewmodel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stats;
import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_ABILITIES;
import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_TYPE;

public class PokemonViewModel extends BaseObservable {
    private Pokemon pokemon = new Pokemon();

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        notifyChange();
    }

    public Drawable getImage(Context context, int res) {
        if (res != -1)
            return ResourcesCompat.getDrawable(context.getResources(),
                    res, context.getTheme());
        else
            return null;
    }

    @Bindable
    public int getFront() {
        return pokemon.getFrontResource();
    }

    @Bindable
    public String getID() {
        return ("#" + pokemon.getID());
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
    public String getHeight() {
        return (pokemon.getHeight() + " m");
    }

    @Bindable
    public String getWeight() {
        return (pokemon.getWeight() + " kg");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Bindable
    public String getAbilities() {
        // convert abilities to string
        System.out.println("[PokemonViewModel] abilities: " + pokemon.getAbilities().toString());
        StringBuilder abilities = new StringBuilder();
        pokemon.getAbilities().forEach(ability -> abilities.append(ability.toString()).append(", "));
        // remove last ", "
        abilities.delete(abilities.length() - 2, abilities.length());
        return abilities.toString().replaceAll("_", " ");
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
