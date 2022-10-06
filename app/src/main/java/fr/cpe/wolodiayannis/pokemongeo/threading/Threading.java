package fr.cpe.wolodiayannis.pokemongeo.threading;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.cpe.wolodiayannis.pokemongeo.listeners.ExecutorListener;

public abstract class Threading {

    /**
     * The executor service
     */
    protected ExecutorService executor;

    /**
     * The tasks to execute.
     */
    protected List<Callable<Void>> tasks = new ArrayList<>();

    /**
     * The listener to call when the executor is done.
     */
    protected ExecutorListener executorListener;

    /**
     * Setup all the tasks.
     * @param context The context to use.
     * @return The current instance.
     */
    public abstract Threading setupTasks(Context context);

    /**
     * Setup the listener.
     * @param threadCount The number of threads to use.
     * @return The current instance.
     */
    public Threading setupExecutor(int threadCount) {
        this.executor = Executors.newFixedThreadPool(threadCount);
        return this;
    }

    /**
     * Execute the tasks.
     */
    public Threading execute() {
        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Shutdown the executor.
     * @return The current instance.
     */
    public Threading shutdown() {
        executor.shutdown();
        return this;
    }

    public Threading setExecutorListener(ExecutorListener executorListener) {
        this.executorListener = executorListener;
        return this;
    }


}
