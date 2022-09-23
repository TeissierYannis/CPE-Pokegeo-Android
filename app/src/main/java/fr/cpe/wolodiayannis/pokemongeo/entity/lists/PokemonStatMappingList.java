package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import com.google.gson.annotations.JsonAdapter;

import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.lists.PokemonStatMappingListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonStat;

@JsonAdapter(PokemonStatMappingListAdapter.class)
public class PokemonStatMappingList {

    private final HashMap<Integer, List<PokemonStat>> pokemonStatMappingList;

    public PokemonStatMappingList(HashMap<Integer, List<PokemonStat>> pokemonStatMappingList) {
        this.pokemonStatMappingList = pokemonStatMappingList;
    }

    public HashMap<Integer, List<PokemonStat>> getPokemonStatMappingList() {
        return pokemonStatMappingList;
    }
}