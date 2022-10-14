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
            return getStatList(statList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get stat List.
     * @param statList StatList.
     * @return StatList.
     */
    private static StatList getStatList(StatList statList) {
        return statList;
    }
}
