package fr.cpe.wolodiayannis.pokemongeo.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonDescription;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonExperienceLevel;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonGeneration;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;
import fr.cpe.wolodiayannis.pokemongeo.entity.User;
import fr.cpe.wolodiayannis.pokemongeo.entity.UserExperienceLevel;
import fr.cpe.wolodiayannis.pokemongeo.entity.UserPokedex;

public class APIFetcher {
    public static final String BASE_URL = "https://pokeapi.co/api/v2/";
    public static final String POKEMON_URL = BASE_URL + "pokemon/";
    public static final String TYPE_URL = BASE_URL + "type/";
    public static final String ITEM_URL = BASE_URL + "item/";
    public static final String ABILITY_URL = BASE_URL + "ability/";
    public static final String STAT_URL = BASE_URL + "stat/";
    public static final String POKEMON_GENERATION_URL = BASE_URL + "generation/";
    public static final String USER_URL = BASE_URL + "user/";
    public static final String USER_POKEDEX_URL = BASE_URL + "user-pokedex/";
    public static final String USER_CAUGHT_INVENTORY_URL = BASE_URL + "user-caught-inventory/";
    public static final String POKEMON_DESCRIPTION_URL = BASE_URL + "pokemon-description/";
    public static final String USER_EXPERIENCE_LEVEL_URL = BASE_URL + "user-experience-level/";
    public static final String POKEMON_EXPERIENCE_LEVEL_URL = BASE_URL + "pokemon-experience-level/";


    /**
     * Fetch items from the API
     *
     * @return List of items
     */
    public static List<Item> fetchItems() {
        List<Item> items = new ArrayList<>();
        try {
            URL url = new URL(ITEM_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                JsonFormatter jsonFormatter = new JsonFormatter(isr);

                // File handling
                try {
                    for (int i = 0; i < jsonFormatter.getSize(); i++) {
                        JSONObject object = jsonFormatter.getResultIndex(i);
                        int id = object.getInt("id");
                        String name = object.getString("name");
                        items.add(new Item(id, name));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }

        return items;
    }

    /**
     * Fetch pokemon from the API.
     *
     * @return list of pokemon
     */
    public static List<Pokemon> fetchPokemon() {
        List<Pokemon> pokemons = new ArrayList<>();
        try {
            URL url = new URL(POKEMON_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                JsonFormatter jsonFormatter = new JsonFormatter(isr);

                // File handling
                try {
                    for (int i = 0; i < jsonFormatter.getSize(); i++) {
                        JSONObject object = jsonFormatter.getResultIndex(i);
                        int id = object.getInt("id");
                        String name = object.getString("name");
                        int weight = object.getInt("weight");
                        int height = object.getInt("height");
                        pokemons.add(new Pokemon(id, name, weight, height));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }

        return pokemons;
    }


    /**
     * Fetch Types from the API.
     *
     * @return list of types
     */
    public static List<Type> fetchTypes() {
        List<Type> types = new ArrayList<>();
        try {
            URL url = new URL(TYPE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                JsonFormatter jsonFormatter = new JsonFormatter(isr);

                // File handling
                try {
                    for (int i = 0; i < jsonFormatter.getSize(); i++) {
                        JSONObject object = jsonFormatter.getResultIndex(i);
                        int id = object.getInt("id");
                        String name = object.getString("name");
                        types.add(new Type(id, name));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }

        return types;
    }


    /**
     * Fetch Stats from the API.
     *
     * @return list of stats
     */
    public static List<Stat> fetchStats() {
        List<Stat> stats = new ArrayList<>();
        try {
            URL url = new URL(STAT_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                JsonFormatter jsonFormatter = new JsonFormatter(isr);

                // File handling
                try {
                    for (int i = 0; i < jsonFormatter.getSize(); i++) {
                        JSONObject object = jsonFormatter.getResultIndex(i);
                        int id = object.getInt("id");
                        String name = object.getString("name");
                        stats.add(new Stat(id, name));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }

        return stats;
    }


    /**
     * Fetch Abilities from the API.
     *
     * @return list of abilities
     */
    public static List<Ability> fetchAbilities() {
        List<Ability> abilities = new ArrayList<>();
        try {
            URL url = new URL(ABILITY_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                JsonFormatter jsonFormatter = new JsonFormatter(isr);

                // File handling
                try {
                    for (int i = 0; i < jsonFormatter.getSize(); i++) {
                        JSONObject object = jsonFormatter.getResultIndex(i);
                        int id = object.getInt("id");
                        String name = object.getString("name");
                        abilities.add(new Ability(id, name));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }

        return abilities;
    }


    /**
     * Fetch User Experience Levels from the API.
     *
     * @return list of user experience levels
     */
    private List<UserExperienceLevel> fetchUserExperienceLevels() {

        List<UserExperienceLevel> userExperienceLevels = new ArrayList<>();
        try {
            URL url = new URL(USER_EXPERIENCE_LEVEL_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                JsonFormatter jsonFormatter = new JsonFormatter(isr);

                // File handling
                try {
                    for (int i = 0; i < jsonFormatter.getSize(); i++) {
                        JSONObject object = jsonFormatter.getResultIndex(i);
                        int level = object.getInt("level");
                        int experience = object.getInt("experience");
                        userExperienceLevels.add(new UserExperienceLevel(level, experience));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }

        return userExperienceLevels;
    }

    /**
     * Fetch Pokemon experience level from the API.
     *
     * @return list of pokemon experience levels
     */
    private List<PokemonExperienceLevel> fetchPokemonExperienceLevels() {

        List<PokemonExperienceLevel> pokemonExperienceLevels = new ArrayList<>();
        try {
            URL url = new URL(POKEMON_EXPERIENCE_LEVEL_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                JsonFormatter jsonFormatter = new JsonFormatter(isr);

                // File handling
                try {
                    for (int i = 0; i < jsonFormatter.getSize(); i++) {
                        JSONObject object = jsonFormatter.getResultIndex(i);
                        int level = object.getInt("level");
                        int experience = object.getInt("experience");
                        pokemonExperienceLevels.add(new PokemonExperienceLevel(level, experience));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }

        return pokemonExperienceLevels;
    }

    /**
     * Fetch Pokemon Generation from the API.
     *
     * @return list of pokemon with his generation
     */
    private List<PokemonGeneration> fetchPokemonGenerations() {

        List<PokemonGeneration> pokemonGenerations = new ArrayList<>();
        try {
            URL url = new URL(POKEMON_GENERATION_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                JsonFormatter jsonFormatter = new JsonFormatter(isr);

                // File handling
                try {
                    for (int i = 0; i < jsonFormatter.getSize(); i++) {
                        JSONObject object = jsonFormatter.getResultIndex(i);
                        int pokemon_id = object.getInt("pokemon_id");
                        int generation_id = object.getInt("generation_id");
                        pokemonGenerations.add(new PokemonGeneration(pokemon_id, generation_id));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }

        return pokemonGenerations;

    }

    /**
     * Fetch the user data from the API.
     *
     * @return the user data
     */
    private User fetchUserData() {

        User user = null;
        try {
            URL url = new URL(USER_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                JsonFormatter jsonFormatter = new JsonFormatter(isr);

                // File handling
                try {
                    for (int i = 0; i < jsonFormatter.getSize(); i++) {
                        JSONObject object = jsonFormatter.getResultIndex(i);
                        int id = object.getInt("id");
                        String pseudo = object.getString("pseudo");
                        String password = object.getString("password");
                        String email = object.getString("email");
                        int experience = object.getInt("experience");
                        boolean is_init = object.getBoolean("is_init");
                        Timestamp created_at = Timestamp.valueOf(object.getString("created_at"));

                        user = new User(id, pseudo, password, email, experience, is_init, created_at);

                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
        return user;
    }

    /**
     * Fetch the user pokedex from the API.
     *
     * @return the user pokedex
     */
    private UserPokedex fetchUserPokedex() {

        UserPokedex userPokedex = null;
        try {
            URL url = new URL(USER_POKEDEX_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                JsonFormatter jsonFormatter = new JsonFormatter(isr);

                // File handling
                try {
                    for (int i = 0; i < jsonFormatter.getSize(); i++) {
                        JSONObject object = jsonFormatter.getResultIndex(i);
                        int user_id = object.getInt("user_id");
                        int pokemon_id = object.getInt("pokemon_id");
                        int quantity_caught = object.getInt("quantity_caught");
                        int quantity_seen = object.getInt("quantity_seen");

                        userPokedex = new UserPokedex(user_id, pokemon_id, quantity_caught, quantity_seen);
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
        return userPokedex;
    }

    /**
     * Fetch the caught inventory from the API.
     *
     * @return the caught inventory
     */
    private CaughtInventory fetchCaughtInventory() {

        CaughtInventory caughtInventory = null;
        try {
            URL url = new URL(USER_CAUGHT_INVENTORY_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                JsonFormatter jsonFormatter = new JsonFormatter(isr);

                // File handling
                try {
                    for (int i = 0; i < jsonFormatter.getSize(); i++) {
                        JSONObject object = jsonFormatter.getResultIndex(i);
                        int user_id = object.getInt("user_id");
                        int pokemon_id = object.getInt("pokemon_id");
                        int pokemon_experience = object.getInt("pokemon_experience");
                        int current_life_state = object.getInt("current_life_state");
                        Timestamp caught_time = Timestamp.valueOf(object.getString("caught_time"));

                        caughtInventory = new CaughtInventory(user_id, pokemon_id, pokemon_experience, current_life_state, caught_time);
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
        return caughtInventory;
    }

    /**
     * Fetch the pokemon description from the API.
     *
     * @return the pokemon description
     */
    private PokemonDescription fetchPokemonDescription() {

        PokemonDescription pokemonDescription = null;
        try {
            URL url = new URL(POKEMON_DESCRIPTION_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                JsonFormatter jsonFormatter = new JsonFormatter(isr);

                // File handling
                try {
                    for (int i = 0; i < jsonFormatter.getSize(); i++) {
                        JSONObject object = jsonFormatter.getResultIndex(i);
                        int pokemon_id = object.getInt("pokemon_id");
                        String description = object.getString("description");

                        pokemonDescription = new PokemonDescription(pokemon_id, description);
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
        return pokemonDescription;
    }

}

