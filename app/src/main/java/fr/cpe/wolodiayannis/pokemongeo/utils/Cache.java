package fr.cpe.wolodiayannis.pokemongeo.utils;

import android.content.Context;

import fr.cpe.wolodiayannis.pokemongeo.exception.CacheException;

/**
 * Cache class.
 */
public class Cache {

    /**
     * Read cache.
     *
     * @param ctx Context.
     * @param key Cache key.
     * @return Cache value.
     * @throws CacheException Cache exception.
     */
    public static Object readCache(Context ctx, String key) throws CacheException {
        try {
            return InternalStorage.readObject(ctx, key);
        } catch (Exception e) {
            throw new CacheException("Error while reading cache");
        }
    }

    /**
     * Write cache.
     *
     * @param ctx   Context.
     * @param key   Cache key.
     * @param value Cache value.
     * @throws CacheException Cache exception.
     */
    public static void writeCache(Context ctx, String key, Object value) throws CacheException {
        try {
            InternalStorage.writeObject(ctx, key, value);
        } catch (Exception e) {
            throw new CacheException("Error while writing cache");
        }
    }

    public static void clearCache(Context ctx) {
        InternalStorage.clearCache(ctx);
    }
}
