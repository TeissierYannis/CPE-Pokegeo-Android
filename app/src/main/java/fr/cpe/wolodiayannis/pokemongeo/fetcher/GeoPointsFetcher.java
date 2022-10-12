package fr.cpe.wolodiayannis.pokemongeo.fetcher;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class GeoPointsFetcher {

    String baseUrl = "https://overpass-api.de/api/interpreter?data=";

    Context ctx;

    public GeoPointsFetcher(Context ctx) {
        this.ctx = ctx;
    }

    // (latitude sud, longitude ouest, latitude nord, longitude est);
    public void fetchSupermarket(double distance, double southLat, double westLng, double northLat, double eastLng, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String query = "[out:json];node[shop=supermarket](around:1000," + southLat + "," + westLng + "," + northLat + "," + eastLng + ");out;";
        String url = baseUrl + query;

        // Prepare request with Volley
        RequestQueue queue = Volley.newRequestQueue(this.ctx);

        System.out.println("URL: " + url);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, listener, errorListener);

        // add it to the RequestQueue
        queue.add(request);
        // Launch request
        queue.start();
    }

    public void fetchPharmacy(double distance, double southLat, double westLng, double northLat, double eastLng, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String query = "[out:json];node[amenity=pharmacy](around:2000," + southLat + "," + westLng + "," + northLat + "," + eastLng + ");out;";
        String url = baseUrl + query;

        // Prepare request with Volley
        RequestQueue queue = Volley.newRequestQueue(this.ctx);

        System.out.println("URL: " + url);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, listener, errorListener);

        // add it to the RequestQueue
        queue.add(request);
        // Launch request
        queue.start();
    }
}
