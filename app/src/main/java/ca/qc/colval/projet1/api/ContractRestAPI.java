package ca.qc.colval.projet1.api;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ContractRestAPI {
    private static final String URL_PATH = "https://androidprojectdbcock-056f.restdb.io/rest/expenses";
    String key = "7faca29019492d112ff0d122f39b7cdd7b304";

    public void deleteContract(String contractId) {
        try {
            URL url = new URL(URL_PATH + "/" + contractId);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("x-apikey",key);
            urlConnection.setRequestMethod("DELETE");

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
                Log.d("HTTP-DELETE", "DELETE Success - Contract ID: " + contractId);
            } else {
                Log.e("HTTP-DELETE", "DELETE Request Failed. Response Code: " + responseCode);
            }

            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
