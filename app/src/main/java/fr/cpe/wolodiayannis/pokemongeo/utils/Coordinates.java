package fr.cpe.wolodiayannis.pokemongeo.utils;

import org.osmdroid.util.GeoPoint;

/**
 * This class contains some useful methods to work with coordinates.
 */
public class Coordinates {

    private static final double EARTH_RADIUS = 6371;

    /**
     * This method calculates the distance between two points.
     *
     * @param lat1 Latitude of the first point.
     * @param lon1 Longitude of the first point.
     * @param lat2 Latitude of the second point.
     * @param lon2 Longitude of the second point.
     * @return The distance between the two points.
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return EARTH_RADIUS * c;
    }

    /**
     * This method calculates the distance between two points.
     *
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The distance between the two points.
     */
    public static double distance(GeoPoint p1, GeoPoint p2) {
        return distance(p1.getLatitude(), p1.getLongitude(), p2.getLatitude(), p2.getLongitude());
    }

    /**
     * This method get a matrix from current location + radius of X meters.
     *
     * @param currentLocation The current location.
     * @param radiusInMeters          The radius in meters.
     */
    public static double[][] getMatrix(GeoPoint currentLocation, double radiusInMeters) {
        double[][] matrix = new double[2][2];
        double lat = currentLocation.getLatitude();
        double lon = currentLocation.getLongitude();
        double computedLat = (radiusInMeters / 1000) / EARTH_RADIUS * (180 / Math.PI);
        double computedLon = computedLat / Math.cos(lat * Math.PI / 180);
        double latMin = lat - computedLat;
        double latMax = lat + computedLat;
        double lonMin = lon - computedLon;
        double lonMax = lon + computedLon;
        matrix[0][0] = latMin;
        matrix[0][1] = lonMin;
        matrix[1][0] = latMax;
        matrix[1][1] = lonMax;
        return matrix;
    }

    /**
     * This method generate X random points in a matrix of X meters around a location.
     *
     * @param currentLocation The current location.
     * @param radius          The radius of the matrix.
     * @param nbPoints        The number of points to generate.
     */
    public static GeoPoint[] generateRandomPoints(GeoPoint currentLocation, double radius, int nbPoints) {
        double[][] matrix = getMatrix(currentLocation, radius);

        return generateRandomPointsWithCovariance(matrix, nbPoints);
    }

    /**
     * This method generate X random points in a matrix, that can't be too close to each other.
     *
     * @param matrix   The matrix where the points will be generated.
     * @param nbPoints The number of points to generate.
     * @return An array of GeoPoint.
     */
    public static GeoPoint[] generateRandomPointsWithCovariance(double[][] matrix, int nbPoints) {
        GeoPoint[] points = new GeoPoint[nbPoints];

        for (int i = 0; i < nbPoints; i++) {
            GeoPoint point = generateRandomPoint(matrix);
            while (isTooClose(point, points)) {
                point = generateRandomPoint(matrix);
            }
            points[i] = point;
        }

        return points;
    }

    /**
     * Avoid to generate points too close to each other.
     *
     * @param point  The point to check.
     * @param points The array of points.
     * @return True if the point is too close to another point.
     */
    private static boolean isTooClose(GeoPoint point, GeoPoint[] points) {
        for (GeoPoint p : points) {
            if (p != null && distance(point, p) < 0.0001) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method generate a random point in a matrix.
     *
     * @param matrix The matrix where the point will be generated.
     * @return The generated point.
     */
    private static GeoPoint generateRandomPoint(double[][] matrix) {
        double lat = matrix[0][0] + (matrix[1][0] - matrix[0][0]) * Math.random();
        double lon = matrix[0][1] + (matrix[1][1] - matrix[0][1]) * Math.random();
        return new GeoPoint(lat, lon);
    }


}
