package fr.cpe.wolodiayannis.pokemongeo.data;

import android.location.Location;

import org.osmdroid.util.GeoPoint;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;
import fr.cpe.wolodiayannis.pokemongeo.entity.user.User;

/**
 * Datastore is a singleton class that provides access to the data layer.
 * This is a persistent storage that can be used to store data between sessions.
 * It is also used to store data that is not supposed to be modified by the user.
 * For example, the list of pokemons is stored in the datastore.
 * The datastore is initialized by the application at startup.
 * It is then available to all the activities.
 */
public class Datastore implements Comparable<Object>, Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1000L;

    /**
     * The datastore instance.
     */
    private static Datastore instance = null;
    /**
     * The last Date pokemon spawned on the map.
     */
    private Date spawnedPokemonExpirationDate = null;

    /**
     * The list of spawned Pokemon's and their locations.
     */
    private HashMap<Pokemon, GeoPoint> spawnedPokemons = new HashMap<>();

    /**
     * The datastore constructor.
     */
    private Datastore() {}

    /**
     * Returns the datastore instance.
     *
     * @return the datastore instance.
     */
    public static Datastore getInstance() {
        if (instance == null) {
            instance = new Datastore();
        }
        return instance;
    }

    /**
     * The user.
     */
    private User user;

    /**
     * The list of pokemon types.
     */
    private List<Type> types;

    /**
     * The list of items
     */
    private HashMap<String, List<Object>> items;

    /**
     * The list of stats
     */
    private List<Stat> stats;

    /**
     * The list of pokemons
     */
    private List<Pokemon> pokemons;

    /**
     * The list of abilities
     */
    private List<Ability> abilities;

    /**
     * Last location.
     */
    private Location lastLocation;

    /**
     * User caught inventory
     */
    private CaughtInventory caughtInventory;


    /**
     * Get the user.
     *
     * @return User.
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user.
     *
     * @param user User.
     */
    public void setUser(User user) {
        this.user = user;
    }


    /**
     * Returns the list of pokemon types.
     *
     * @return the list of pokemon types.
     */
    public List<Type> getTypes() {
        return types;
    }

    /**
     * Sets the list of pokemon types.
     *
     * @param types the list of pokemon types.
     */
    public Datastore setTypes(List<Type> types) {
        this.types = types;
        return this;
    }

    /**
     * Returns the list of items.
     *
     * @return the list of items.
     */
    public HashMap<String, List<Object>> getItems() {
        return items;
    }

    /**
     * Sets the list of items.
     *
     * @param items the list of items.
     */
    public Datastore setItems(HashMap<String, List<Object>> items) {
        this.items = items;
        return this;
    }

    /**
     * Returns the list of stats.
     *
     * @return the list of stats.
     */
    public List<Stat> getStats() {
        return stats;
    }

    /**
     * Sets the list of stats.
     *
     * @param stats the list of stats.
     */
    public Datastore setStats(List<Stat> stats) {
        this.stats = stats;
        return this;
    }

    /**
     * Returns the list of pokemons.
     *
     * @return the list of pokemons.
     */
    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    /**
     * Sets the list of pokemons.
     *
     * @param pokemons the list of pokemons.
     */
    public Datastore setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
        return this;
    }

    /**
     * Returns the list of abilities.
     *
     * @return the list of abilities.
     */
    public List<Ability> getAbilities() {
        return abilities;
    }

    /**
     * Sets the list of abilities.
     *
     * @param abilities the list of abilities.
     */
    public Datastore setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
        return this;
    }

    /**
     * Returns the last location.
     *
     * @return the last location.
     */
    public Location getLastLocation() {
        return lastLocation;
    }

    /**
     * Sets the last location.
     *
     * @param lastLocation the last location.
     */
    public Datastore setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
        return this;
    }

    /**
     * Returns the caught inventory.
     *
     * @return the caught inventory.
     */
    public CaughtInventory getCaughtInventory() {
        return caughtInventory;
    }

    /**
     * Sets the caught inventory.
     *
     * @param caughtInventory the caught inventory.
     */
    public Datastore setCaughtInventory(CaughtInventory caughtInventory) {
        this.caughtInventory = caughtInventory;
        return this;
    }

    /**
     * Returns the last Date pokemon spawned on the map.
     * @return the last Date pokemon spawned on the map.
     */
    public Date getSpawnedPokemonExpiration() {
        return spawnedPokemonExpirationDate;
    }

    /**
     * Returns the last Date pokemon spawned on the map.
     * @param lastPokemonSpawned the last Date pokemon spawned on the map.
     */
    public Datastore setSpawnedPokemonExpiration(Date lastPokemonSpawned) {
        this.spawnedPokemonExpirationDate = lastPokemonSpawned;
        return this;
    }

    /**
     * Returns the list of spawned Pokemon's and their locations.
     * @return the list of spawned Pokemon's and their locations.
     */
    public HashMap<Pokemon, GeoPoint> getSpawnedPokemons() {
        return spawnedPokemons;
    }

    /**
     * Sets the list of spawned Pokemon's and their locations.
     * @param spawnedPokemons the list of spawned Pokemon's and their locations.
     */
    public Datastore setSpawnedPokemons(HashMap<Pokemon, GeoPoint> spawnedPokemons) {
        this.spawnedPokemons = spawnedPokemons;
        return this;
    }

    /**
     * ===============================================
     *  COMPARABLE INTERFACE IMPLEMENTATION TODO
     * ===============================================
     */

    /**
     * @param types
     * @return
     */
    public int compareTo(List<Type> types) {
        // TODO
        return 0;
    }

    /**
     * Compares this object with the specified object for order.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Object o) {

        // Check if the object is null
        if (o == null) {
            return 1;
        }

        // Look at the object type and use the appropriate comparison method
        if (o instanceof Datastore) {
            return compareTo((Datastore) o);
        } else if (o instanceof List) {
            if (((List<?>) o).get(0) instanceof Type) {
                return compareTo((List<Type>) o);
            }
        } else {
            return 1;
        }


        return 0;
    }
}
