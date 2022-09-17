package fr.cpe.wolodiayannis.pokemongeo.entity;

public class UserExperienceLevel {

    private final int level;
    private final int experience;

    public UserExperienceLevel(int level, int experience) {
        this.level = level;
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }
}
