package fr.cpe.wolodiayannis.pokemongeo.entity;

public class Item {

    private final int id;
    private final String name;

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFrontResource() {
        return this.id;
    }
}
