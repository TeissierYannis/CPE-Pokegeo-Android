package fr.cpe.wolodiayannis.pokemongeo.api.request;

import java.io.IOException;

import fr.cpe.wolodiayannis.pokemongeo.api.StatAPI;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.entity.lists.StatList;
import retrofit2.Call;

public class StatRequest extends BaseRequest {

    /**
     * Get StatAPI.
     * @return StatAPI.
     */
    protected static StatAPI getAPI() {
        return getRetrofit().create(StatAPI.class);
    }

    /**
     * Get all stats.
     * @return List of stats.
     */
    public static StatList getAllStats() {
        Call<StatList> call = getAPI().getStats();
        try {
            StatList statList = call.execute().body();
            LogAPI("Stats");
            return statList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get one stat.
     * @param id Stat id.
     * @return Stat.
     */
    public static Stat getStatFromID(int id) {
        Call<Stat> call = getAPI().getStat(id);
        try {
            Stat stat = call.execute().body();
            LogAPI("Stat");
            return stat;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
