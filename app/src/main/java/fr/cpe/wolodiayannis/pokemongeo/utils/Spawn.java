package fr.cpe.wolodiayannis.pokemongeo.utils;

import org.osmdroid.bonuspack.location.NominatimPOIProvider;
import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.observers.PharmaciesObserver;
import fr.cpe.wolodiayannis.pokemongeo.observers.ShopsObserver;

public class Spawn {

    private final PharmaciesObserver pharmaciesObserver;
    private final ShopsObserver shopsObserver;

    public Spawn(ShopsObserver shopsObserver, PharmaciesObserver pharmaciesObserver) {
        this.shopsObserver = shopsObserver;
        this.pharmaciesObserver = pharmaciesObserver;
    }

    public boolean isSpawned() {
        return Datastore.getInstance().getSpawnedPokemonExpiration() != null;
    }

    public boolean isSpawnedExpired() {
        Date now = new Date();
        Date spawnDate = Datastore.getInstance().getSpawnedPokemonExpiration();
        return now.after(spawnDate);
    }

    private void spawnPokemons(GeoPoint location) {
        // Random between 4 and 6 - Corresponds to the number of pokemon to display on the map
        int random = (int) (Math.random() * 9) + 4;

        // Get the list of pokemon to display
        HashMap<Pokemon, GeoPoint> pokemonToDisplay = new HashMap<>();

        GeoPoint[] points = Coordinates.generateRandomPoints(
                location,
                100,
                random
        );

        // Fill the list of pokemon to display
        for (int i = 0; i < random; i++) {
            pokemonToDisplay.put(getRandomPokemon(), points[i]);
        }

        Datastore.getInstance().setSpawnedPokemons(pokemonToDisplay);

        this.generateTimeout();
    }

    private void generateTimeout() {
        // Timeout 5 minutes
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 5 * 60 * 1000);
        Datastore.getInstance().setSpawnedPokemonExpiration(expiration);
    }

    private Pokemon getRandomPokemon() {
        int random = (int) (Math.random() * Datastore.getInstance().getPokemons().size());
        return Datastore.getInstance().getPokemons().get(random);
    }

    boolean isRequestForPharmacyRunning = false;

    public void spawnPharmacies(GeoPoint loc) {

        if (isRequestForPharmacyRunning) {
            return;
        }

        NominatimPOIProvider poiProvider = new NominatimPOIProvider("OSMBonusPackTutoUserAgent");

        new Thread(() -> {
            isRequestForPharmacyRunning = true;
            BoundingBox boundingBox = new BoundingBox(loc.getLatitude() + 0.005, loc.getLongitude() + 0.005, loc.getLatitude() - 0.005, loc.getLongitude() - 0.005);
            ArrayList<POI> pois = new ArrayList<>();
            try {
                pois = poiProvider.getPOIInside(boundingBox, "pharmacy", 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Datastore.getInstance().addPharmacies(pois);
            pharmaciesObserver.set(pois);
        }).start();
    }

    boolean isRequestForShopRunning = false;

    public void spawnShops(GeoPoint loc) {
        if (isRequestForShopRunning) {
            return;
        }
        NominatimPOIProvider poiProvider = new NominatimPOIProvider("OSMBonusPackTutoUserAgent");

        new Thread(() -> {
            isRequestForShopRunning = true;
            BoundingBox boundingBox = new BoundingBox(loc.getLatitude() + 0.005, loc.getLongitude() + 0.005, loc.getLatitude() - 0.005, loc.getLongitude() - 0.005);
            ArrayList<POI> pois = new ArrayList<>();
            try {
                pois = poiProvider.getPOIInside(boundingBox, "cafe", 20);
            } catch (Exception e) {
                try {
                    pois = poiProvider.getPOIInside(boundingBox.increaseByScale(100), "supermarket", 20);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            Datastore.getInstance().addShops(pois);
            shopsObserver.set(pois);
        }).start();
    }

    public boolean isPokemonNearby() {
        // 1. Sort the list of spawned pokemons by distance (nearest first)
        // 2. Check if the first one is near enough
        // 3. If yes, return true
        try {

            HashMap<Pokemon, GeoPoint> sorted = Sorter.sortByPokemonDistance(
                    Datastore.getInstance().getSpawnedPokemons(),
                    new GeoPoint(
                            Datastore.getInstance().getLastLocation().getLatitude(),
                            Datastore.getInstance().getLastLocation().getLongitude()
                    )
            );

            // get first element (geopoint)
            GeoPoint first = sorted.values().iterator().next();

            // get distance between first and current location
            double distance = Coordinates.distance(
                    new GeoPoint(
                            Datastore.getInstance().getLastLocation().getLatitude(),
                            Datastore.getInstance().getLastLocation().getLongitude()
                    ),
                    first
            );

            // if distance < 100m, return true
            return distance < 10000.0 / 111111.0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isShopNearby() {
        // 1. Sort the list of spawned shops by distance (nearest first)
        // 2. Check if the first one is near enough
        // 3. If yes, return true
        try {

            List<POI> sorted = Sorter.sortPOIByDistance(
                    Datastore.getInstance().getShops(),
                    new GeoPoint(
                            Datastore.getInstance().getLastLocation().getLatitude(),
                            Datastore.getInstance().getLastLocation().getLongitude()
                    )
            );

            // get first element (geopoint)
            GeoPoint first = sorted.get(0).mLocation;

            // get distance between first and current location
            double distance = Coordinates.distance(
                    new GeoPoint(
                            Datastore.getInstance().getLastLocation().getLatitude(),
                            Datastore.getInstance().getLastLocation().getLongitude()
                    ),
                    first
            );

            return distance > 10000.0 / 111111.0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPharmacyNearby() {
        // 1. Sort the list of spawned pharmacies by distance (nearest first)
        // 2. Check if the first one is near enough
        // 3. If yes, return true
        try {

            List<POI> sorted = Sorter.sortPOIByDistance(
                    Datastore.getInstance().getPharmacies(),
                    new GeoPoint(
                            Datastore.getInstance().getLastLocation().getLatitude(),
                            Datastore.getInstance().getLastLocation().getLongitude()
                    )
            );

            // get first element (geopoint)
            GeoPoint first = sorted.get(0).mLocation;

            // get distance between first and current location
            double distance = Coordinates.distance(
                    new GeoPoint(
                            Datastore.getInstance().getLastLocation().getLatitude(),
                            Datastore.getInstance().getLastLocation().getLongitude()
                    ),
                    first
            );

            return distance > 10000.0 / 111111.0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPokemonSpawnNeeded(GeoPoint loc) {
        if (!this.isSpawned()) {
            this.spawnPokemons(loc);
            return true;
        } else if (this.isSpawnedExpired()) {
            this.spawnPokemons(loc);
            return true;
        } else if (!this.isPokemonNearby()) {
            this.spawnPokemons(loc);
            return true;
        }
        return false;
    }

    public boolean isShopSpawned() {
        return Datastore.getInstance().getShops() != null && Datastore.getInstance().getShops().size() > 0;
    }

    public boolean isPharmacySpawned() {
        return Datastore.getInstance().getPharmacies() != null && Datastore.getInstance().getPharmacies().size() > 0;
    }

    public void isShopSpawnNeeded(GeoPoint loc) {
        if (!this.isShopSpawned() || !this.isShopNearby()) {
            this.shopsObserver.needUpdate(loc);
        }
    }

    public void isPharmacySpawnNeeded(GeoPoint loc) {
        if (!this.isPharmacySpawned() || !this.isPharmacyNearby()) {
            this.pharmaciesObserver.needUpdate(loc);
        }
    }
}