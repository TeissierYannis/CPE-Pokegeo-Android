package fr.cpe.wolodiayannis.pokemongeo.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JsonFormatter {
    private final InputStreamReader isr;
    private JSONArray array;

    public JsonFormatter(InputStreamReader isr) {
        this.isr = isr;
        this.format();
    }

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

    public JSONArray getResult() {
        return this.array;
    }

        public int getSize() {
            return this.array.length();
        }


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
