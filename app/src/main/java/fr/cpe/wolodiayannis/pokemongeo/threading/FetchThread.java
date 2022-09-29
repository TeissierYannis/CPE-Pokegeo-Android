package fr.cpe.wolodiayannis.pokemongeo.threading;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.Ability;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.Item;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonStat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Stat;
import fr.cpe.wolodiayannis.pokemongeo.entity.Type;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.AbilitiesFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.CaughtInventoryFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.ItemsFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.PokemonAbilitiesFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.PokemonStatsFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.PokemonTypesFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.PokemonsFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.StatsFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.TypesFetcher;
import fr.cpe.wolodiayannis.pokemongeo.listeners.ExecutorListener;

public class FetchThread {

    private ExecutorService executor;
    private AtomicReference<List<Pokemon>> pokemonList = new AtomicReference<>(new ArrayList<>());
    private AtomicReference<HashMap<Integer, List<Integer>>> pokemonAbilities = new AtomicReference<>(new HashMap<>());
    private AtomicReference<HashMap<Integer, List<Integer>>> pokemonTypes = new AtomicReference<>(new HashMap<>());
    private AtomicReference<HashMap<Integer, List<PokemonStat>>> pokemonStats = new AtomicReference<>(new HashMap<>());
    private AtomicReference<CaughtInventory> caughtInventory = new AtomicReference<>(new CaughtInventory());
    private List<Stat> statsList = new ArrayList<>();
    private List<Type> typesList = new ArrayList<>();
    private List<Item> itemsList = new ArrayList<>();
    private List<Ability> abilitiesList = new ArrayList<>();
    List<Callable<Void>> tasks = new ArrayList<>();
    private ExecutorListener executorListener;

    public FetchThread() {}

    public FetchThread setupExecutor() {
        this.executor = Executors.newFixedThreadPool(9);
        return this;
    }

    public FetchThread setupTasks(Context context) {
        // Fetching tasks
        tasks.add(() -> {
            changeLoadingText("Fetching Pokémon...");
            pokemonList.set((new PokemonsFetcher(context)).fetchAndCache());
            onEnd(1);
            return null;
        });

        tasks.add(() -> {
            changeLoadingText("Pokémon's abilities training...");
            pokemonAbilities.set((new PokemonAbilitiesFetcher(context)).fetchAndCache());
            onEnd(2);
            return null;
        });

        tasks.add(() -> {
            changeLoadingText("Definition of Pokémon's types...");
            pokemonTypes.set((new PokemonTypesFetcher(context)).fetchAndCache());
            onEnd(3);
            return null;
        });

        tasks.add(() -> {
            changeLoadingText("Definition of Pokémon's stats...");
            pokemonStats.set((new PokemonStatsFetcher(context)).fetchAndCache());
            onEnd(4);
            return null;
        });

        tasks.add(() -> {
            changeLoadingText("Creation of statistics...");
            statsList.addAll((new StatsFetcher(context)).fetchAndCache());
            onEnd(5);
            return null;
        });

        tasks.add(() -> {
            changeLoadingText("Creation of types...");
            typesList.addAll((new TypesFetcher(context)).fetchAndCache());
            onEnd(6);
            return null;
        });

        tasks.add(() -> {
            changeLoadingText("Manufacturing of items...");
            itemsList.addAll((new ItemsFetcher(context)).fetchAndCache());
            onEnd(7);
            return null;
        });
        tasks.add(() -> {
            changeLoadingText("Creation of abilities...");
            abilitiesList.addAll((new AbilitiesFetcher(context)).fetchAndCache());
            onEnd(8);
            return null;
        });
        tasks.add(() -> {
            changeLoadingText("Creation of caught inventory...");
            caughtInventory.set((new CaughtInventoryFetcher(context)).fetchAndCache(Datastore.getInstance().getUser().getId()));
            onEnd(9);
            return null;
        });
        return this;
    }

    private void onEnd(int i) {
        executorListener.onEnd(i);
    }

    private void changeLoadingText(String s) {
        executorListener.onLoadingTextChange(s);
    }

    public FetchThread execute() {
        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public FetchThread shutdown() {
        executor.shutdown();
        return this;
    }

    public FetchThread setExecutorListener(ExecutorListener executorListener) {
        this.executorListener = executorListener;
        return this;
    }

    public AtomicReference<List<Pokemon>> getPokemonList() {
        return pokemonList;
    }

    public AtomicReference<HashMap<Integer, List<Integer>>> getPokemonAbilities() {
        return pokemonAbilities;
    }

    public AtomicReference<HashMap<Integer, List<Integer>>> getPokemonTypes() {
        return pokemonTypes;
    }

    public AtomicReference<HashMap<Integer, List<PokemonStat>>> getPokemonStats() {
        return pokemonStats;
    }

    public AtomicReference<CaughtInventory> getCaughtInventory() {
        return caughtInventory;
    }

    public List<Stat> getStatsList() {
        return statsList;
    }

    public List<Type> getTypesList() {
        return typesList;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public List<Ability> getAbilitiesList() {
        return abilitiesList;
    }
}
