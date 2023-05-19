package ca.qc.colval.projet1.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import ca.qc.colval.projet1.entities.Expense;

public class ExpenseRestAPI implements Runnable{
    String urlPath = "https://androidprojectdbcock-056f.restdb.io/rest/expenses";
    String key= "7faca29019492d112ff0d122f39b7cdd7b304";


    Expense expense;
    Context context;
    public ExpenseRestAPI(Context context,Expense expense){
        this.expense = expense;
        this.context = context;
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
                String newPost = gson.toJson(expense);

                //send request
                OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                wr.write(newPost);
                wr.flush();
                //check response code
                if (urlConnection.getResponseCode()>=200 && urlConnection.getResponseCode()<300)
                    Log.d("HTTP-POST", "POST Success - " + newPost);
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
