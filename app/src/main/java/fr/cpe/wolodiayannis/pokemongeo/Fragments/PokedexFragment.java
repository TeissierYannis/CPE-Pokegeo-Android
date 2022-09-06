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
import fr.cpe.wolodiayannis.pokemongeo.databinding.PokedexFragmentBinding;
import fr.cpe.wolodiayannis.pokemongeo.ListAdapter.PokemonListAdapter;

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
            JSONArray array = new JSONArray(builder.toString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String name = object.getString("name");
                String image = object.getString("image");
                String type1 = object.getString("type1");
                String type2 = null;
                if (object.has("type2"))
                    type2 = object.getString("type2");
                // get the ID of the image
                int imgID = Integer.parseInt(image.replaceAll("[a-zA-Z]", ""));
                // get the type of the pokemon
                POKEMON_TYPE type1Enum = POKEMON_TYPE.valueOf(type1.toUpperCase());
                POKEMON_TYPE type2Enum = null;
                if (type2 != null)
                    type2Enum = POKEMON_TYPE.valueOf(type2.toUpperCase());
                pokemonList.add(new Pokemon(i, name, imgID, type2Enum, type1Enum));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        binding.pokemonList.setAdapter(adapter);
        return binding.getRoot();
    }
}