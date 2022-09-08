package fr.cpe.wolodiayannis.pokemongeo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stats;
import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_ABILITIES;
import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_TYPE;
import fr.cpe.wolodiayannis.pokemongeo.listadapter.PokemonListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokedexListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.utils.JsonFormatter;
import fr.cpe.wolodiayannis.pokemongeo.databinding.PokedexFragmentBinding;

public class PokedexFragment extends Fragment {

    private PokedexListenerInterface listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        PokedexFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.pokedex_fragment, container, false);
        binding.pokemonList.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

        List<Pokemon> pokemonList = new ArrayList<>();

        PokemonListAdapter adapter = new PokemonListAdapter(pokemonList, listener);

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
                                        binding.getRoot().getContext().getPackageName()
                                )
                        )
                );

            }
        } catch (JSONException e) {
            System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
        }

        binding.pokemonList.setAdapter(adapter);

        return binding.getRoot();
    }

    public void setPokedexListenerInterface(PokedexListenerInterface listener) {
        this.listener = listener;
    }
}