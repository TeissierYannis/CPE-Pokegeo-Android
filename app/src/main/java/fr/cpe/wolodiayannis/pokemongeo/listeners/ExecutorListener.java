package fr.cpe.wolodiayannis.pokemongeo.listeners;

public interface ExecutorListener {
    void onEnd(Integer taskID);

    void onLoadingTextChange(String s);

    void onTaskendSetProgress();
}