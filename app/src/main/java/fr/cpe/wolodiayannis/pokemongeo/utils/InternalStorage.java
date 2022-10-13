package fr.cpe.wolodiayannis.pokemongeo.utils;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class InternalStorage {
    /**
     * Constructor.
     */
    private InternalStorage() {}

    /**
     * Write object in cache.
     * @param context Context.
     * @param key Key.
     * @param object Object.
     * @throws IOException
     * @throws IOException
     */
    public static void writeObject(Context context, String key, Object object) throws IOException, IOException {
        FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    /**
     * Read object from cache.
     * @param context Context.
     * @param key Key.
     * @return Object.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object readObject(Context context, String key) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = context.openFileInput(key);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }
}
