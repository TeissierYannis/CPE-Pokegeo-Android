package fr.cpe.wolodiayannis.pokemongeo.entity;

public class PokemonGeneration {

    private final int pokemon_id;
    private final int generation_id;

    public PokemonGeneration(int pokemon_id, int generation_id) {
        this.pokemon_id = pokemon_id;
        this.generation_id = generation_id;
    }

    public int getPokemon_id() {
        return pokemon_id;
    }

    public int getGeneration_id() {
        return generation_id;
    }

    public void fetchPokemonGeneration() {
        // TODO : fetch pokemon generation from database
    }
}
