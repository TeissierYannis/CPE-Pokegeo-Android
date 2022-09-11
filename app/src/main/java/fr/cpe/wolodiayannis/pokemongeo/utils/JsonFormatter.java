package fr.cpe.wolodiayannis.pokemongeo.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * JsonFormatter
 */
public class JsonFormatter {
    /**
     * Input Stream Reader.
     */
    private final InputStreamReader isr;
    /**
     * Json Array.
     */
    private JSONArray array;

    /**
     * Constructor.
     *
     * @param isr Input Stream Reader
     */
    public JsonFormatter(InputStreamReader isr) {
        this.isr = isr;
        this.format();
    }

    /**
     * Format the Json.
     */
    private void format() {
        BufferedReader reader = new BufferedReader(this.isr);
        StringBuilder builder = new StringBuilder();
        String data = "";

        while (data != null) {
            try {
                data = reader.readLine();
                builder.append(data);
            } catch (Exception e) {
                System.err.println("[JSON Formatter] Error while reading the file\n" + e.getMessage());
            }
        }

        this.array = null;

        try {
            this.array = new JSONArray(builder.toString());
        } catch (JSONException e) {
            System.err.println("[JSON Formatter] Error while building JSON Array\n" + e.getMessage());
        }
    }

    /**
     * Get the Json Array.
     *
     * @return Json Array
     */
    public JSONArray getResult() {
        return this.array;
    }

    /**
     * Get size of the Json Array.
     *
     * @return Size of the Json Array
     */
    public int getSize() {
        return this.array.length();
    }

    /**
     * Get the Json Object at the given index.
     *
     * @param i Index
     * @return Json Object
     */
    public JSONObject getResultIndex(int i) {
        JSONObject object = null;
        try {
            object = this.array.getJSONObject(i);
        } catch (JSONException e) {
            System.err.println("[JSON Formatter] Error while fetching specific index\n" + e.getMessage());
        }
        return object;
    }
}
