package fr.cpe.wolodiayannis.pokemongeo.entity;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_ABILITIES;
import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_TYPE;

public class PokemonUnitTest {

    @Test
    public void testPokemonWithoutData() {
        Pokemon pokemon = new Pokemon();

        assertEquals(0, pokemon.getID());
        assertEquals("", pokemon.getName());
        assertEquals("", pokemon.getSpecies());
        assertNull(pokemon.getTypes());
        assertEquals(0, pokemon.getHeight(), 0);
        assertEquals(0, pokemon.getWeight(), 0);
        assertNull(pokemon.getAbilities());
        assertNull(pokemon.getStats());
        assertNull(pokemon.getEvolutions());
        assertEquals("", pokemon.getDescription());
        assertEquals(0, pokemon.getGen());
        assertEquals(0, pokemon.getFrontResource());
    }

    @Test
    public void testPokemonWithData() {
        List<POKEMON_TYPE> types = new ArrayList<POKEMON_TYPE>();
        List<POKEMON_ABILITIES> abilities = new ArrayList<POKEMON_ABILITIES>();
        List<Pokemon> evolutions = new ArrayList<Pokemon>();

        Stats stats = Stats.CREATE(45, 49, 49, 65, 65, 45);

        types.add(POKEMON_TYPE.Fire);
        abilities.add(POKEMON_ABILITIES.Blaze);
        evolutions.add(new Pokemon());

        Pokemon pokemon = Pokemon.CREATE(
                1,
                "Bulbasaur",
                "Seed",
                types,
                0.7f,
                6.9f,
                abilities,
                stats,
                evolutions,
                "Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun's rays, the seed grows progressively larger.",
                1,
                1
        );

        assertEquals(1, pokemon.getID());
        assertEquals("Bulbasaur", pokemon.getName());
        assertEquals("Seed", pokemon.getSpecies());
        assertEquals(types, pokemon.getTypes());
        assertEquals(types.get(0), pokemon.getTypes().get(0));
        assertEquals(0.7f, pokemon.getHeight(), 0);
        assertEquals(6.9f, pokemon.getWeight(), 0);
        assertEquals(abilities, pokemon.getAbilities());
        assertEquals(abilities.get(0), pokemon.getAbilities().get(0));
        assertEquals(stats, pokemon.getStats());
        assertEquals(stats.getHp(), pokemon.getStats().getHp());
        assertEquals(stats.getAttack(), pokemon.getStats().getAttack());
        assertEquals(stats.getDefense(), pokemon.getStats().getDefense());
        assertEquals(stats.getSpecialAttack(), pokemon.getStats().getSpecialAttack());
        assertEquals(stats.getSpecialDefense(), pokemon.getStats().getSpecialDefense());
        assertEquals(stats.getSpeed(), pokemon.getStats().getSpeed());
        assertEquals(stats.getTotal(), pokemon.getStats().getTotal());
        assertEquals(evolutions, pokemon.getEvolutions());
        assertEquals("Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun's rays, the seed grows progressively larger.", pokemon.getDescription());
        assertEquals(1, pokemon.getGen());
        assertEquals(1, pokemon.getFrontResource());
    }

    @Test
    public void testPokemonColors() {
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(0xFFA8A878);
        colors.add(0xFFF08030);
        colors.add(0xFF6890F0);
        colors.add(0xFFF8D030);
        colors.add(0xFF78C850);
        colors.add(0xFF98D8D8);
        colors.add(0xFFC03028);
        colors.add(0xFFA040A0);
        colors.add(0xFFE0C068);
        colors.add(0xFFA890F0);
        colors.add(0xFFF85888);
        colors.add(0xFFA8B820);
        colors.add(0xFFB8A038);
        colors.add(0xFF705898);
        colors.add(0xFF7038F8);
        colors.add(0xFF705848);
        colors.add(0xFFB8B8D0);
        colors.add(0xFFEE99AC);
        colors.add(0xFF000000);

        List<POKEMON_TYPE> types = new ArrayList<POKEMON_TYPE>();
        List<POKEMON_ABILITIES> abilities = new ArrayList<POKEMON_ABILITIES>();
        List<Pokemon> evolutions = new ArrayList<Pokemon>();

        Stats stats = new Stats(45, 49, 49, 65, 65, 45);
        abilities.add(POKEMON_ABILITIES.Blaze);
        evolutions.add(new Pokemon());

        POKEMON_TYPE[] pokemonTypes = POKEMON_TYPE.values();

        for (int i = 0; i < pokemonTypes.length; i++) {
            types.clear();
            types.add(pokemonTypes[i]);
            Pokemon pokemon = Pokemon.CREATE(
                    1,
                    "Bulbasaur",
                    "Seed",
                    types,
                    0.7f,
                    6.9f,
                    abilities,
                    stats,
                    evolutions,
                    "Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun's rays, the seed grows progressively larger.",
                    1,
                    1
            );

            assertEquals((int) colors.get(i), (int) pokemon.getColor());
        }
    }

    @Test
    public void testPokemonColorWhenNoType() {
        Pokemon pokemon = new Pokemon();
        assertEquals(0, pokemon.getColor());
    }
}
