package fr.cpe.wolodiayannis.pokemongeo.entity;

public class Type {
    private final int id;
    private final String name;

    public Type(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void fetchType() {
        // TODO : fetch type from database
    }
}
