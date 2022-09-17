package fr.cpe.wolodiayannis.pokemongeo.entity;

public class Ability {

    private final int id;
    private final String name;

    public Ability(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
