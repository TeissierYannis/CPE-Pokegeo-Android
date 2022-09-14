package fr.cpe.wolodiayannis.pokemongeo.entity;

public class PokemonDescription {
    private final int pokemon_id;
    private final String description;

    public PokemonDescription(int pokemon_id, String description) {
        this.pokemon_id = pokemon_id;
        this.description = description;
    }

    public int getPokemon_id() {
        return pokemon_id;
    }

    public String getDescription() {
        return description;
    }

    public void fetchPokemonDescription() {
        // TODO : fetch pokemon description from database
    }
}
