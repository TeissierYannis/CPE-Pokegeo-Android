package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThread;
import static fr.cpe.wolodiayannis.pokemongeo.utils.Logger.logOnUiThreadError;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fr.cpe.wolodiayannis.pokemongeo.data.DataFetcher;
import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;
import fr.cpe.wolodiayannis.pokemongeo.utils.Cache;

public class AbilitiesFetcher {

    private Context ctx;

    public AbilitiesFetcher(Context ctx) {
        this.ctx = ctx;
    }

    public List<Ability> fetchAndCache() {
        List<Ability> abilityList = new ArrayList<>();
        try {
            abilityList = (List<Ability>) Cache.readCache(this.ctx, "data_abilities");
        } catch (Exception e) {
            try {
                abilityList = DataFetcher.fetchAbilityList().getAbilityList();
                Cache.writeCache(this.ctx, "data_abilities", abilityList);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return abilityList;
    }
}
