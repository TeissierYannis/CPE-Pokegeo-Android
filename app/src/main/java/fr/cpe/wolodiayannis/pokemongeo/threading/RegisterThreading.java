package fr.cpe.wolodiayannis.pokemongeo.threading;

import android.content.Context;

import fr.cpe.wolodiayannis.pokemongeo.entity.user.User;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.UserRegisterFetcher;

public class RegisterThreading extends Threading {

    /**
     * The pseudo of the user;
     */
    private final User user;
    /**
     * The password of the user.
     */
    private final String password;

    /**
     * Constructor.
     *
     * @param user     The user.
     * @param password The password of the user.
     */
    public RegisterThreading(User user, String password) {
        this.user = user;
        this.password = password;
    }

    /**
     * Run the threading.
     * @param context The context.
     */
    @Override
    public Threading setupTasks(Context context) {
        tasks.add(() -> {
            (new UserRegisterFetcher(context)).fetchAndCache(user, password);
            this.executorListener.onEnd(1);
            return null;
        });
        return this;
    }
}
