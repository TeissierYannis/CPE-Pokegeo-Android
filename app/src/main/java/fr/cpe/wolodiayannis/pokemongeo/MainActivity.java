package fr.cpe.wolodiayannis.pokemongeo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_ABILITIES;
import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_TYPE;
import fr.cpe.wolodiayannis.pokemongeo.databinding.ActivityMainBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stats;
import fr.cpe.wolodiayannis.pokemongeo.fragments.PokedexFragment;
import fr.cpe.wolodiayannis.pokemongeo.fragments.PokemonDetailsFragment;
import fr.cpe.wolodiayannis.pokemongeo.listeners.BackArrowListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokedexListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.utils.JsonFormatter;

public class MainActivity extends AppCompatActivity {

    static List<Pokemon> pokemons;

    public static List<Pokemon> getPokemonList() {
        return pokemons;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        pokemons = fetchPokemons();

        showStartup();

        BottomNavigationView navView = findViewById(R.id.bottomNav);

        navView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.map:
                    // TODO
                    break;
                case R.id.inventory:
                    //
                    break;
                case R.id.pokedex:
                    showStartup();
                    break;
                case R.id.setting:
                    //
                    break;
            }
            return true;
        });
    }

    public void showStartup() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokedexFragment fragment = new PokedexFragment();

        PokedexListenerInterface listener = this::showPokemonDetails;
        fragment.setPokedexListenerInterface(listener);

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void showPokemonDetails(Pokemon pokemon) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        PokemonDetailsFragment fragment = new PokemonDetailsFragment(pokemon);

        BackArrowListenerInterface backArrowListener = this::showStartup;
        fragment.setBackArrowListenerInterface(backArrowListener);

        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private List<Pokemon> fetchPokemons() {

        List<Pokemon> pokemonList = new ArrayList<>();

        InputStreamReader isr = new InputStreamReader(getResources().openRawResource(R.raw.pokemon_stats));
        JsonFormatter jsonFormatter = new JsonFormatter(isr);

        // File handling
        try {
            for (int i = 0; i < jsonFormatter.getSize(); i++) {
                JSONObject object = jsonFormatter.getResultIndex(i);

                int id = object.getInt("id");
                String name = object.getString("name");
                String species = object.getString("species");

                float height = 0;
                String sHeight = object.getString("height");
                if (!sHeight.contains("None")) {
                    height = Float.parseFloat(sHeight.substring(sHeight.indexOf('(') + 1, sHeight.indexOf('m')));
                }

                float weight = 0;
                String sWeight = object.getString("weight");
                if (!sWeight.contains("None")) {
                    weight = Float.parseFloat(sWeight.substring(sWeight.indexOf('(') + 1, sWeight.indexOf("kg")));
                }

                List<POKEMON_TYPE> types = new ArrayList<>();
                for (int j = 0; j < object.getJSONArray("type").length(); j++) {
                    types.add(POKEMON_TYPE.valueOf(object.getJSONArray("type").getString(j)));
                }

                Stats stats = new Stats(
                        object.getJSONObject("stats").getInt("hp"),
                        object.getJSONObject("stats").getInt("attack"),
                        object.getJSONObject("stats").getInt("defense"),
                        object.getJSONObject("stats").getInt("sp.atk"),
                        object.getJSONObject("stats").getInt("sp.def"),
                        object.getJSONObject("stats").getInt("speed")
                );

                String description = object.getString("description");
                int gen = object.getInt("gen");

                List<POKEMON_ABILITIES> abilities = new ArrayList<>();
                for (int j = 0; j < object.getJSONArray("abilities").length(); j++) {
                    abilities.add(POKEMON_ABILITIES.valueOf(object.getJSONArray("abilities").getString(j).replaceAll(" ", "_")));
                }
                List<Pokemon> evolutions = new ArrayList<>();

                pokemonList.add(
                        Pokemon.CREATE(
                                id,
                                name,
                                species,
                                types,
                                height,
                                weight,
                                abilities,
                                stats,
                                evolutions,
                                description,
                                gen,
                                getResources().getIdentifier(
                                        "p" + object.getString("id") + "_100",
                                        "drawable",
                                        getPackageName()
                                )
                        )
                );

            }
        } catch (JSONException e) {
            System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
        }

        return pokemonList;
    }
}