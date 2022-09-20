package fr.cpe.wolodiayannis.pokemongeo.entity.lists;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.adapters.gson.StatAdapter;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;

@JsonAdapter(StatAdapter.class)
public class StatList {

    @SerializedName("data")
    private List<Stat> statsList;

    public List<Stat> getStatsList() {
        return statsList;
    }
}
