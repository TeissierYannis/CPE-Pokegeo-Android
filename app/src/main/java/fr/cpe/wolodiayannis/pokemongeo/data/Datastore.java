package fr.cpe.wolodiayannis.pokemongeo.data;

import android.location.Location;

import java.io.Serializable;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;
import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;

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
     * The datastore constructor.
     */
    private Datastore() {
    }

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
     * The list of pokemon types.
     */
    private List<Type> types;

    /**
     * The list of items
     */
    private List<Item> items;

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
    public List<Item> getItems() {
        return items;
    }

    /**
     * Sets the list of items.
     *
     * @param items the list of items.
     */
    public Datastore setItems(List<Item> items) {
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
