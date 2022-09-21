package fr.cpe.wolodiayannis.pokemongeo.api.request;

public abstract class Request {

    /**
     * Print API Tag.
     * @param tag Tag.
     */
    protected static void LogAPI(String tag) {
        System.out.println(
                "[API] " + tag + " fetched !"
        );
    }


}
