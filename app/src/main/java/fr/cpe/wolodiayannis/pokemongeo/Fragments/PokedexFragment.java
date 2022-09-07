package fr.cpe.wolodiayannis.pokemongeo.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_ABILITIES;
import fr.cpe.wolodiayannis.pokemongeo.Enum.POKEMON_TYPE;
import fr.cpe.wolodiayannis.pokemongeo.Entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.ListAdapter.PokemonListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.Utils.JsonFormatter;
import fr.cpe.wolodiayannis.pokemongeo.databinding.PokedexFragmentBinding;

public class PokedexFragment extends Fragment {

    private List<Pokemon> pokemonList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        PokedexFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.pokedex_fragment, container, false);
        binding.pokemonList.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        pokemonList = new ArrayList<>();

        PokemonListAdapter adapter = new PokemonListAdapter(pokemonList);

        InputStreamReader isr = new InputStreamReader(getResources().openRawResource(R.raw.pokemon_stats));
        JsonFormatter jsonFormatter = new JsonFormatter(isr);

        // File handling
        try {
            for (int i = 0; i < jsonFormatter.getSize(); i++) {
                JSONObject object = jsonFormatter.getResultIndex(i);

                String name = object.getJSONObject("name").getString("french");
                String type1 = object.getJSONArray("type").getString(0);
                String type2 = null;
                if (object.getJSONArray("type").length() > 1) {
                    type2 = object.getJSONArray("type").getString(1);
                }
                // get the ID of the image
                String image = object.getString("image");
                int imgID = getResources().getIdentifier(image, "pokemons", binding.getRoot().getContext().getPackageName());
                // get the type of the pokemon

                List<POKEMON_TYPE> types = new ArrayList<>();

                types.add(POKEMON_TYPE.valueOf(type1.substring(0, 1).toUpperCase() + type1.substring(1)));
                if (type2 != null) {
                    types.add(POKEMON_TYPE.valueOf(type2.substring(0, 1).toUpperCase() + type2.substring(1)));
                }

                List<POKEMON_ABILITIES> abilities = new ArrayList<>();
                List<Pokemon> evolutions = new ArrayList<>();

                pokemonList.add(
                        Pokemon.CREATE(
                                0,
                                "Name",
                                "Species",
                                types,
                                0f,
                                0f,
                                abilities,
                                0,
                                0,
                                0,
                                0,
                                0,
                                0,
                                evolutions,
                                "Description",
                                1,
                                imgID
                        )
                );
            }
        } catch (JSONException e) {
            System.err.println("[PokedexFragment] Error while parsing JSON file\n" + e.getMessage());
        }

        binding.pokemonList.setAdapter(adapter);

        return binding.getRoot();
    }
}