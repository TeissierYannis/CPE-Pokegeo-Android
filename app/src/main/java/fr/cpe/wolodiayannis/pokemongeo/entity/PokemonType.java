package fr.cpe.wolodiayannis.pokemongeo.entity;

public class PokemonType {
    private final int pokemon_id;
    private final int type_id;

    public PokemonType(int pokemon_id, int type_id) {
        this.pokemon_id = pokemon_id;
        this.type_id = type_id;
    }

    public int getPokemon_id() {
        return pokemon_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void fetchPokemonType() {
        // TODO : fetch pokemon type from database
    }
}
