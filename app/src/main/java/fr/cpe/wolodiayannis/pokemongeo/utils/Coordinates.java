package fr.cpe.wolodiayannis.pokemongeo.utils;

import org.osmdroid.util.GeoPoint;

/**
 * This class contains some useful methods to work with coordinates.
 */
public class Coordinates {

    private static final double EARTH_RADIUS = 6371;

    /**
     * This method calculates the distance between two points.
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
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The distance between the two points.
     */
    public static double distance(GeoPoint p1, GeoPoint p2) {
        return distance(p1.getLatitude(), p1.getLongitude(), p2.getLatitude(), p2.getLongitude());
    }

    /**
     * This method get a matrix from current location + radius of X meters.
     * @param currentLocation The current location.
     *                        The matrix will be centered on this location.
     *                        The matrix will be a square.
     *
     */
    public static double[][] getMatrix(GeoPoint currentLocation, double radius) {
        double[][] matrix = new double[2][2];
        double lat = currentLocation.getLatitude();
        double lon = currentLocation.getLongitude();
        double lat1 = lat + (radius / 111111);
        double lon1 = lon + (radius / (111111 * Math.cos(Math.toRadians(lat))));
        double lat2 = lat - (radius / 111111);
        double lon2 = lon - (radius / (111111 * Math.cos(Math.toRadians(lat))));
        matrix[0][0] = lat1;
        matrix[0][1] = lon1;
        matrix[1][0] = lat2;
        matrix[1][1] = lon2;
        return matrix;
    }

    /**
     * Find covariance matrix of a set of points.
     */
    public static double[][] covarianceMatrix(GeoPoint[] points) {
        double[][] matrix = new double[2][2];
        double sumLat = 0;
        double sumLon = 0;
        for (GeoPoint point : points) {
            sumLat += point.getLatitude();
            sumLon += point.getLongitude();
        }
        double meanLat = sumLat / points.length;
        double meanLon = sumLon / points.length;
        double sumLatLat = 0;
        double sumLonLon = 0;
        double sumLatLon = 0;
        for (GeoPoint point : points) {
            sumLatLat += Math.pow(point.getLatitude() - meanLat, 2);
            sumLonLon += Math.pow(point.getLongitude() - meanLon, 2);
            sumLatLon += (point.getLatitude() - meanLat) * (point.getLongitude() - meanLon);
        }
        matrix[0][0] = sumLatLat / points.length;
        matrix[0][1] = sumLatLon / points.length;
        matrix[1][0] = sumLatLon / points.length;
        matrix[1][1] = sumLonLon / points.length;
        return matrix;
    }

    /**
     * This method generate X random points in a matrix of X meters around a location.
     * Implement a method of dispersion.
     * @param currentLocation The current location.
     *                        The matrix will be centered on this location.
     *                        The matrix will be a square.
     *                        The points will be generated in this matrix.
     *
     */
    public static GeoPoint[] generateRandomPointsWithCovariance(GeoPoint currentLocation, double radius, int nbPoints) {
        GeoPoint[] points = new GeoPoint[nbPoints];
        double[][] matrix = getMatrix(currentLocation, radius);
        double[][] covarianceMatrix = covarianceMatrix(points);
        double meanLat = (matrix[0][0] + matrix[1][0]) / 2;
        double meanLon = (matrix[0][1] + matrix[1][1]) / 2;
        for (int i = 0; i < nbPoints; i++) {
            double lat = meanLat + Math.random() * (matrix[0][0] - matrix[1][0]) + covarianceMatrix[0][0];
            double lon = meanLon + Math.random() * (matrix[0][1] - matrix[1][1]) + covarianceMatrix[0][1];
            points[i] = new GeoPoint(lat, lon);
        }
        return points;
    }

}
