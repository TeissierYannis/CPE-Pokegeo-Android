package fr.cpe.wolodiayannis.pokemongeo.data;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;

public class DataList {

    private List<Pokemon> pokemons;
    private List<Item> items;

    public DataList(List<Pokemon> pokemons, List<Item> items) {
        this.pokemons = pokemons;
        this.items = items;
    }

    public static DataList CREATE(List<Pokemon> pokemons, List<Item> items) {
        return new DataList(pokemons, items);
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public List<Item> getItems() {
        return items;
    }
}
