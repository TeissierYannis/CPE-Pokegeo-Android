package fr.cpe.wolodiayannis.pokemongeo.entity;

public class Stat {
    private final int id;
    private final String name;

    public Stat(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void fetchStat() {
        // TODO : fetch stat from database
    }
}
