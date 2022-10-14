package fr.cpe.wolodiayannis.pokemongeo.listeners;

public interface ExecutorListener {
    /**
     * On task end.
     *
     * @param taskID task id
     */
    void onEnd(Integer taskID);

    /**
     * On loading text change.
     *
     * @param s text
     */
    void onLoadingTextChange(String s);

    /**
     * On task progress.
     */
    void onTaskendSetProgress();
}
