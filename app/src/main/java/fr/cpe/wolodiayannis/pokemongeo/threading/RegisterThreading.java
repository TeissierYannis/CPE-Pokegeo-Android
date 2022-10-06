package fr.cpe.wolodiayannis.pokemongeo.threading;

import android.content.Context;

import fr.cpe.wolodiayannis.pokemongeo.entity.User;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.UserRegisterFetcher;

public class RegisterThreading extends Threading {

    /**
     * The pseudo of the user;
     */
    private User user;
    /**
     * The password of the user.
     */
    private String password;

    /**
     * Constructor.
     * @param pseudo The pseudo of the user.
     * @param password The password of the user.
     */
    public RegisterThreading(User user, String password) {
        this.user = user;
        this.password = password;
    }

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
