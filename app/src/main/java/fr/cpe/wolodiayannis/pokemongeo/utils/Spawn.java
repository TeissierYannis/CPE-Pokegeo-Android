package fr.cpe.wolodiayannis.pokemongeo.utils;

import android.graphics.drawable.Drawable;

import org.osmdroid.bonuspack.location.NominatimPOIProvider;
import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;

public class Spawn {

    public boolean isSpawned() {
        return Datastore.getInstance().getSpawnedPokemonExpiration() != null;
    }

    public boolean isSpawnedExpired() {
        Date now = new Date();
        Date spawnDate = Datastore.getInstance().getSpawnedPokemonExpiration();
        return now.after(spawnDate);
    }

    public void spawnPokemons() {
        // Random between 2 and 6 - Corresponds to the number of pokemon to display on the map
        int random = (int) (Math.random() * 5) + 2;

        // Get the list of pokemon to display
        HashMap<Pokemon, GeoPoint> pokemonToDisplay = new HashMap<>();

        GeoPoint[] points = Coordinates.generateRandomPoints(
                new GeoPoint(
                        Datastore.getInstance().getLastLocation().getLatitude(),
                        Datastore.getInstance().getLastLocation().getLongitude()
                ),
                1000,
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

    private void spawnPharmacies(GeoPoint loc) {
        NominatimPOIProvider poiProvider = new NominatimPOIProvider("OSMBonusPackTutoUserAgent");

        new Thread(() -> {
            ArrayList<POI> pois = poiProvider.getPOICloseTo(loc, "pharmacy", 10, 0.1);
            Datastore.getInstance().addPharmacies(pois);
        }).start();
    }

    private void spawnShops(GeoPoint loc) {
        NominatimPOIProvider poiProvider = new NominatimPOIProvider("OSMBonusPackTutoUserAgent");

        new Thread(() -> {
            ArrayList<POI> pois = poiProvider.getPOICloseTo(loc, "shop", 10, 0.1);
            Datastore.getInstance().addShops(pois);
        }).start();
    }

    public boolean isPokemonNearby() {
        // 1. Sort the list of spawned pokemons by distance (nearest first)
        // 2. Check if the first one is near enough
        // 3. If yes, return true
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
        return distance < 100;
    }

    public boolean isShopNearby() {
        // 1. Sort the list of spawned shops by distance (nearest first)
        // 2. Check if the first one is near enough
        // 3. If yes, return true
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

        // if distance < 100m, return true
        return distance < 100;
    }

    public boolean isPharmacyNearby() {
        // 1. Sort the list of spawned pharmacies by distance (nearest first)
        // 2. Check if the first one is near enough
        // 3. If yes, return true
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

        // if distance < 100m, return true
        return distance < 100;
    }

    public boolean isPokemonSpawnNeeded(GeoPoint loc) {
        if (!this.isSpawned()) {
            this.spawnPokemons();
            return true;
        } else if (this.isSpawnedExpired()) {
            this.spawnPokemons();
            return true;
        } else if (!this.isPokemonNearby()) {
            this.spawnPokemons();
            return true;
        }

        return false;
    }

    public boolean isShopSpawnNeeded(GeoPoint loc) {
        if (Datastore.getInstance().getPharmacies() == null) {
            this.spawnShops(loc);
            return true;
        } else if (!this.isShopNearby()) {
            this.spawnShops(loc);
            return true;
        }

        return false;
    }

    public boolean isPharmacySpawnNeeded(GeoPoint loc) {
        if (Datastore.getInstance().getPharmacies() == null) {
            this.spawnPharmacies(loc);
            return true;
        } else if (!this.isPharmacyNearby()) {
            this.spawnPharmacies(loc);
            return true;
        }

        return false;
    }
}
