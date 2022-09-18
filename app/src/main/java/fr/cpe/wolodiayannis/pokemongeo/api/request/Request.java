package fr.cpe.wolodiayannis.pokemongeo.api.request;

import fr.cpe.wolodiayannis.pokemongeo.api.BaseAPI;

public abstract class Request {

    protected static void LogAPI(String tag) {
        System.out.println(
                "[API] " + tag + " fetched !"
        );
    }
}
