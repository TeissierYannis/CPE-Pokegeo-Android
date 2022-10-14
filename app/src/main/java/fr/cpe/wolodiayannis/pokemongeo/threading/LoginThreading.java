package fr.cpe.wolodiayannis.pokemongeo.threading;

import android.content.Context;

import fr.cpe.wolodiayannis.pokemongeo.fetcher.UserLoginFetcher;

public class LoginThreading extends Threading {

    /**
     * The pseudo of the user;
     */
    private final String pseudo;
    /**
     * The password of the user.
     */
    private final String password;

    /**
     * Constructor.
     *
     * @param pseudo   The pseudo of the user.
     * @param password The password of the user.
     */
    public LoginThreading(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    /**
     * Run the threading.
     * @param context The context.
     */
    @Override
    public Threading setupTasks(Context context) {
        tasks.add(() -> {
            (new UserLoginFetcher(context)).fetchAndCache(this.pseudo, this.password);
            this.executorListener.onEnd(1);
            return null;
        });
        return this;
    }
}
