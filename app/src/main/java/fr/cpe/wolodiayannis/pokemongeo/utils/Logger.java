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


    /**
     * Log error in main thread.
     * @param message The error message.
     * @param args The error arguments.
     */
    public static void logOnUiThread(final String message, final Object... args) {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                System.out.println("[LOG]" + String.format(message, args));
            }
        });
    }

    /**
     * Log error in main thread.
     * @param message error message.
     */
    public static void logOnUiThreadError(final String message) {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                System.err.println("[LOG]" + message);
            }
        });
    }

    /**
     * Log error in main thread.
     * @param message error message.
     * @param args error message arguments.
     */
    public static void logOnUiThreadError(final String message, final Object... args) {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                System.err.println("[LOG]" + String.format(message, args));
            }
        });
    }
}
