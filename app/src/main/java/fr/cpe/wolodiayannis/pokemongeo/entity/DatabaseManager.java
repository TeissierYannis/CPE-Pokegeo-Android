package fr.cpe.wolodiayannis.pokemongeo.entity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseManager extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "pokegeo.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Pokemon.class);
            TableUtils.createTableIfNotExists(connectionSource, PokemonTypes.class);
            TableUtils.createTableIfNotExists(connectionSource, Stats.class);
            TableUtils.createTableIfNotExists(connectionSource, Items.class);
            Log.i("DatabaseManager", "Database successfully created");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DatabaseManager", "Can't create database", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {

            TableUtils.dropTable(connectionSource, Pokemon.class, true);
            TableUtils.dropTable(connectionSource, PokemonTypes.class, true);
            TableUtils.dropTable(connectionSource, Stats.class, true);
            TableUtils.dropTable(connectionSource, Items.class, true);
            Log.i("DatabaseManager", "Database successfully Dropped");
            onCreate(database, connectionSource);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DatabaseManager", "Can't update database", e);
        }
    }

    public void insertPokemon(Pokemon pokemon) {
        try {
            getDao(Pokemon.class).create(pokemon);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DatabaseManager", "Can't insert pokemon", e);
        }
    }

    public void insertPokemonTypes(PokemonTypes pokemonTypes) {
        try {
            getDao(PokemonTypes.class).create(pokemonTypes);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DatabaseManager", "Can't insert pokemonTypes", e);
        }
    }

    public void insertStats(Stats stats) {
        try {
            getDao(Stats.class).create(stats);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DatabaseManager", "Can't insert stats", e);
        }
    }

    public void insertItem(Items item) {
        try {
            getDao(Items.class).create(item);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DatabaseManager", "Can't insert item", e);
        }
    }
}

