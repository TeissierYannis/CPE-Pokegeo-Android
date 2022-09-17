package fr.cpe.wolodiayannis.pokemongeo.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;

import fr.cpe.wolodiayannis.pokemongeo.datas.EnumList;
import fr.cpe.wolodiayannis.pokemongeo.datas.UserDatas;
import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.ItemsInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonAbilities;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonDescription;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonExperienceLevel;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonGeneration;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonStats;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonType;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;
import fr.cpe.wolodiayannis.pokemongeo.entity.User;
import fr.cpe.wolodiayannis.pokemongeo.entity.UserExperienceLevel;
import fr.cpe.wolodiayannis.pokemongeo.entity.UserPokedex;

public class APIFetcher {
    private static final String BASE_URL = "http://vps-af8ec19b.vps.ovh.net:6868/";
    private static final String POKEMON_URL = BASE_URL + "pokemon/";
    private static final String TYPE_URL = BASE_URL + "type/";
    private static final String ITEM_URL = BASE_URL + "item/";
    private static final String STAT_URL = BASE_URL + "stat/";
    private static final String USER_URL = BASE_URL + "user/";
    private static final String ABILITY_URL = BASE_URL + "ability/";
    private static final String POKEMON_TYPE_URL = BASE_URL + "pokemon-type/";
    private static final String USER_POKEDEX_URL = BASE_URL + "user-pokedex/";
    private static final String POKEMON_STATS_URL = BASE_URL + "pokemon-stats/";
    private static final String POKEMON_GENERATION_URL = BASE_URL + "generation/";
    private static final String POKEMON_ABILITIES_URL = BASE_URL + "pokemon-abilities/";
    private static final String USER_CAUGHT_INVENTORY_URL = BASE_URL + "user-caught-inventory/";
    private static final String USER_ITEMS_INVENTORY_URL = BASE_URL + "user-items-inventory/";
    private static final String POKEMON_DESCRIPTION_URL = BASE_URL + "pokemon-description/";
    private static final String USER_EXPERIENCE_LEVEL_URL = BASE_URL + "user-experience-level/";
    private static final String POKEMON_EXPERIENCE_LEVEL_URL = BASE_URL + "pokemon-experience-level/";


    /**
     * Fetch all the data from the API
     */
    public static void fetchAllData() {
        fetchPokemon();
        fetchTypes();
        fetchItems();
        fetchStats();
        fetchUserData();
        fetchAbilities();
        fetchPokemonTypes();
        fetchUserPokedex();
        fetchPokemonStats();
        fetchPokemonGenerations();
        fetchPokemonAbilities();
        fetchCaughtInventory();
        fetchUserItemsInventory();
        fetchPokemonDescription();
        fetchUserExperienceLevels();
        fetchPokemonExperienceLevels();
    }

    /**
     * Fetch items from the API
     */
    private static void fetchItems() {
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
                        EnumList.addItem(new Item(id, name));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
    }

    /**
     * Fetch pokemon from the API.
     */
    private static void fetchPokemon() {
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
                        EnumList.addPokemon(new Pokemon(id, name, weight, height));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
    }

    /**
     * Fetch Types from the API.
     */
    private static void fetchTypes() {
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
                        EnumList.addType(new Type(id, name));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
    }


    /**
     * Fetch Stats from the API.
     */
    private static void fetchStats() {
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
                        EnumList.addStat(new Stat(id, name));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }
        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
    }


    /**
     * Fetch Abilities from the API.
     */
    private static void fetchAbilities() {
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
                        EnumList.addAbility(new Ability(id, name));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
    }


    /**
     * Fetch User Experience Levels from the API.
     */
    private static void fetchUserExperienceLevels() {
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
                        EnumList.addUserExperienceLevel(new UserExperienceLevel(level, experience));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
    }

    /**
     * Fetch Pokemon experience level from the API.
     */
    private static void fetchPokemonExperienceLevels() {
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
                        EnumList.addPokemonExperienceLevel(new PokemonExperienceLevel(level, experience));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
    }

    /**
     * Fetch Pokemon Generation from the API.
     */
    private static void fetchPokemonGenerations() {
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
                        EnumList.addPokemonGeneration(new PokemonGeneration(pokemon_id, generation_id));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
    }

    /**
     * Fetch the pokemon description from the API.
     */
    private static void fetchPokemonDescription() {
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
                        EnumList.addPokemonDescription(new PokemonDescription(pokemon_id, description));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
    }

    /**
     * Fetch the pokemon types from the API.
     */
    private static void fetchPokemonTypes() {
        try {
            URL url = new URL(POKEMON_TYPE_URL);
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
                        int type_id = object.getInt("type_id");
                        EnumList.addPokemonType(new PokemonType(pokemon_id, type_id));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }
        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
    }

    /**
     * Fetch the pokemon abilities from the API.
     */
    private static void fetchPokemonAbilities() {
        try {
            URL url = new URL(POKEMON_ABILITIES_URL);
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
                        int ability_id = object.getInt("ability_id");
                        EnumList.addPokemonAbility(new PokemonAbilities(pokemon_id, ability_id));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }
        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
    }

    /**
     * Fetch the pokemons stats from the API.
     */
    private static void fetchPokemonStats() {
        try {
            URL url = new URL(POKEMON_STATS_URL);
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
                        int stat_id = object.getInt("stat_id");
                        int base_stat = object.getInt("base_stat");
                        EnumList.addPokemonStats(new PokemonStats(pokemon_id, stat_id, base_stat));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }
        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
    }

    /**
     * Fetch the user data from the API.
     */
    private static void fetchUserData() {
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
                        UserDatas.setUser(new User(id, pseudo, password, email, experience, is_init, created_at));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }
        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
    }

    /**
     * Fetch the user pokedex from the API.
     */
    private static void fetchUserPokedex() {
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
                        UserDatas.addUserPokedex(new UserPokedex(user_id, pokemon_id, quantity_caught, quantity_seen));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
    }

    /**
     * Fetch the caught inventory from the API.
     */
    private static void fetchCaughtInventory() {
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
                        UserDatas.addCaughtInventory(new CaughtInventory(user_id, pokemon_id, pokemon_experience, current_life_state, caught_time));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }
        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
    }

    /**
     * Fetch the user items inventory from the API.
     */
    private static void fetchUserItemsInventory() {
        try {
            URL url = new URL(USER_ITEMS_INVENTORY_URL);
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
                        int item_id = object.getInt("item_id");
                        int quantity = object.getInt("quantity");
                        UserDatas.addItemInventory(new ItemsInventory(user_id, item_id, quantity));
                    }
                } catch (JSONException e) {
                    System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
                }
            }

        } catch (Exception e) {
            Log.e("PokedexFragment", "Error while establish the connection with the API" + e.getMessage());
        }
    }
}

