package fr.cpe.wolodiayannis.pokemongeo.entity;

public class PokemonAbilities {
    private final int pokemon_id;
    private final int ability_id;

    public PokemonAbilities(int pokemon_id, int ability_id) {
        this.pokemon_id = pokemon_id;
        this.ability_id = ability_id;
    }

    public int getPokemon_id() {
        return pokemon_id;
    }

    public int getAbility_id() {
        return ability_id;
    }
}
