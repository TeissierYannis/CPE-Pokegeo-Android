package fr.cpe.wolodiayannis.pokemongeo.entity;

public class Pokemon {

    private final int id;
    private final String name;
    private final int weight;
    private final int height;

    public Pokemon(int id, String name, int weight, int height) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public void fetchPokemon() {
        // TODO : fetch pokemon from database
    }
}