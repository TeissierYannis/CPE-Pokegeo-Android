package fr.cpe.wolodiayannis.pokemongeo.utils;

import org.osmdroid.util.GeoPoint;

public class Position {

    /**
     * Is position in range.
     *
     * @param point1       Point 1.
     * @param point2       Point 2.
     * @param rangeInMeter Range in meter.
     * @return True if in range, false otherwise.
     */
    public static boolean isInRange(GeoPoint point1, GeoPoint point2, int rangeInMeter) {
        return point1.distanceToAsDouble(point2) <= rangeInMeter;
    }
}
