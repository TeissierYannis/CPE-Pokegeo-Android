package fr.cpe.wolodiayannis.pokemongeo.utils;

public class Logger {
    public static void log(String message) {
        System.out.println("[LOG]" + message);
    }

    public static void log(String message, Object... args) {
        System.out.println("[LOG]" + String.format(message, args));
    }

    // log in main thread
    public static void logOnUiThread(final String message) {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                System.out.println("[LOG]" + message);
            }
        });
    }

    // log in main thread
    public static void logOnUiThread(final String message, final Object... args) {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                System.out.println("[LOG]" + String.format(message, args));
            }
        });
    }

    // log in main thread error
    public static void logOnUiThreadError(final String message) {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                System.err.println("[LOG]" + message);
            }
        });
    }

    // log in main thread error
    public static void logOnUiThreadError(final String message, final Object... args) {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                System.err.println("[LOG]" + String.format(message, args));
            }
        });
    }
}
