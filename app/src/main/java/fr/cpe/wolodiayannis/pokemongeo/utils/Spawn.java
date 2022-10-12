package fr.cpe.wolodiayannis.pokemongeo.utils;

import org.osmdroid.bonuspack.location.NominatimPOIProvider;
import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    public void spawnPharmacies(GeoPoint loc) {
        NominatimPOIProvider poiProvider = new NominatimPOIProvider("OSMBonusPackTutoUserAgent");
        Datastore.getInstance().addPharmacies(
                poiProvider.getPOICloseTo(loc, "pharmacy", 10, 100)
        );
    }

    public void spawnShops(GeoPoint loc) {
        NominatimPOIProvider poiProvider = new NominatimPOIProvider("OSMBonusPackTutoUserAgent");

        ArrayList<POI> l = poiProvider.getPOICloseTo(loc, "shop", 10, 100);


        Datastore.getInstance().addShops(
                poiProvider.getPOICloseTo(loc, "shop", 10, 100)
        );
    }

}
