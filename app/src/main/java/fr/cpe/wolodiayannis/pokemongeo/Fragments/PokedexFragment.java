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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.Entity.POKEMON_TYPE;
import fr.cpe.wolodiayannis.pokemongeo.Entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.ListAdapter.PokemonListAdapter;
import fr.cpe.wolodiayannis.pokemongeo.databinding.PokedexFragmentBinding;

public class PokedexFragment extends Fragment {
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        PokedexFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.pokedex_fragment, container, false);

        // Set the pokémon list from JSON
        binding.pokemonList.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        List<Pokemon> pokemonList = new ArrayList<>();
        PokemonListAdapter adapter = new PokemonListAdapter(pokemonList);
        InputStreamReader isr = new InputStreamReader(getResources().openRawResource(R.raw.pokemons));
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder builder = new StringBuilder();
        String data = "";
        //lecture du fichier. data == null => EOF
        while (data != null) {
            try {
                data = reader.readLine();
                builder.append(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Traitement du fichier
        try {
            for (int i = 0; i < jsonFormatter.getSize(); i++) {
                JSONObject object = jsonFormatter.getResultIndex(i);
                String name = object.getJSONObject("name").getString("french");

                // gets types
                String type1 = object.getJSONArray("type").getString(0);
                String type2 = null;
                if (object.getJSONArray("type").length() > 1) {
                    type2 = object.getJSONArray("type").getString(1);
                }
                // get the ID of the image
                String image = object.getString("i
                int imgID = getResources().getIdentifier(image, "pokemons", binding.getRoot().getContext().getPackageName());
                // get the type of the pokemon
                POKEMON_TYPE type1Enum = POKEMON_TYPE.valueOf(type1.substring(0, 1).toUpperCase() + type1.substring(1));
                POKEMON_TYPE type2Enum = null;
                if (type2 != null)
                    type2Enum = POKEMON_TYPE.valueOf(type2.substring(0, 1).toUpperCase() + type2.substring(1));

                pokemonList.add(new Pokemon(i, name, imgID, type1Enum, type2Enum));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        binding.pokemonList.setAdapter(adapter);
        return binding.getRoot();
    }
}