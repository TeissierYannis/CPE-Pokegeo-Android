package fr.cpe.wolodiayannis.pokemongeo.Enum;

import java.lang.reflect.Field;

public class BACKGROUND_COLOR {
    public static final int Normal = 0xFFA8A878;
    public static final int Fire = 0xFFF08030;
    public static final int Water = 0xFF6890F0;
    public static final int Electric = 0xFFF8D030;
    public static final int Grass = 0xFF78C850;
    public static final int Ice = 0xFF98D8D8;
    public static final int Fighting = 0xFFC03028;
    public static final int Poison = 0xFFA040A0;
    public static final int Ground = 0xFFE0C068;
    public static final int Flying = 0xFFA890F0;
    public static final int Psychic = 0xFFF85888;
    public static final int Bug = 0xFFA8B820;
    public static final int Rock = 0xFFB8A038;
    public static final int Ghost = 0xFF705898;
    public static final int Dragon = 0xFF7038F8;
    public static final int Dark = 0xFF705848;
    public static final int Steel = 0xFFB8B8D0;
    public static final int Fairy = 0xFFEE99AC;
    public static final int Shadow = 0x00000000;
    public static final int Unknown = 0xFF000000;

    public static int valueOf(String name) {
        Field field;
        try {
            field = BACKGROUND_COLOR.class.getField(name);
            return field.getInt(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return Unknown;
    }
}