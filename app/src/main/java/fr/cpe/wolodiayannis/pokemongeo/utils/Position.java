package fr.cpe.wolodiayannis.pokemongeo.utils;

import org.osmdroid.util.GeoPoint;

public class Position {

    public static boolean isInRange(GeoPoint point1, GeoPoint point2, int rangeInMeter) {
        return point1.distanceToAsDouble(point2) <= rangeInMeter;
    }
}
