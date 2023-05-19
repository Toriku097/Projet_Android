package ca.qc.colval.projet1.api;

import android.app.Activity;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ca.qc.colval.projet1.adapter.CheckAdapter;
import ca.qc.colval.projet1.entities.Check;
import ca.qc.colval.projet1.entities.Expense;

public class CheckGetAPI implements Runnable {
    String urlPath = "https://androidprojectdbcock-056f.restdb.io/rest/checks";
    String key = "7faca29019492d112ff0d122f39b7cdd7b304";
    Activity activity;
    RecyclerView recyclerView;
    CheckAdapter adapter;
    List<Check> checks;

    public CheckGetAPI(Activity activity, RecyclerView recyclerView) {
        this.activity = activity;
        this.recyclerView = recyclerView;
    }

    @Override
    public void run() {
        try {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urlPath);
                //open a URL connection
                urlConnection = (HttpURLConnection) url.openConnection();
                //add headers to request
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("x-apikey",key);

                InputStream in = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                // use a string builder to bufferize the response body
                // read from the input strea.
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append('\n');
                }
                // use the string builder directly,
                // or convert it into a String
                String body = sb.toString();
                Log.d("HTTP-GET", body);
                //convert jsonBody to List<Categorie> in memory
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Check>>(){}.getType();

                checks = gson.fromJson(body, listType);
                //when list of categories is ready
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //update data source of MainActivity
                        adapter = new CheckAdapter(checks, activity);
                        recyclerView.setAdapter(adapter);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Exception: ", e.getMessage());
        }
    }
}
