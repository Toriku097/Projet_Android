package ca.qc.colval.projet1.api;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import ca.qc.colval.projet1.entities.Supplier;


public class SupplierRestAPI implements Runnable {

    public interface CommunicationChannel {
        void sendResponseCode(int responseCode);
    }

    CommunicationChannel channel;
    Activity activity;
    Supplier supplier;

    String urlPath = "https://androidprojectdbcock-056f.restdb.io/rest/suppliers";
    String key = "7faca29019492d112ff0d122f39b7cdd7b304";
    HttpURLConnection urlConnection = null;

    public SupplierRestAPI(Activity activity, Supplier supplier) {
        this.activity = activity;
        this.supplier = supplier;
        channel = (CommunicationChannel) activity;
    }

    @Override
    public void run() {
        try {
            URL url;
            try {
                url = new URL(urlPath);
                // Open a URL connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("x-apikey", key);
                urlConnection.setDoOutput(true);

                // Use Gson library to convert the Supplier object to JSON
                Gson gson = new Gson();
                String supplierJson = gson.toJson(supplier);

                // Send the request
                OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                wr.write(supplierJson);
                wr.flush();
                if (urlConnection.getResponseCode() >= 200 && urlConnection.getResponseCode() < 300)
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                channel.sendResponseCode(urlConnection.getResponseCode());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
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
