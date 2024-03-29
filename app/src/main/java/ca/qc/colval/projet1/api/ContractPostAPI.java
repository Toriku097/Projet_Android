package ca.qc.colval.projet1.api;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import ca.qc.colval.projet1.entities.Contract;
import ca.qc.colval.projet1.utility.UtilityClass;

public class ContractPostAPI implements Runnable{
    String urlPath = "https://androidprojectdbcock-056f.restdb.io/rest/contracts";
    String key= "7faca29019492d112ff0d122f39b7cdd7b304";
    Contract contract;
    Activity activity;
    Context context;
    public ContractPostAPI(Context context,Contract contract, Activity activity){
        this.contract = contract;
        this.context = context;
        this.activity = activity;
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
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("x-apikey",key);
                urlConnection.setDoOutput(true);

                Gson gson = new Gson();
                String newPost = gson.toJson(contract);

                //send request
                OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                wr.write(newPost);
                wr.flush();
                //check response code
                if (urlConnection.getResponseCode()>=200 && urlConnection.getResponseCode()<300) {
                    Log.d("HTTP-POST", "POST Success - " + newPost);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            UtilityClass.Toast(context,"Convention ajoutée");
                        }
                    });
                }
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

