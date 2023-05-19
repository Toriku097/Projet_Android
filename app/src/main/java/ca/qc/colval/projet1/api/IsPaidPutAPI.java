package ca.qc.colval.projet1.api;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import ca.qc.colval.projet1.entities.Contract;
import ca.qc.colval.projet1.entities.Expense;
import ca.qc.colval.projet1.utility.UtilityClass;

public class IsPaidPutAPI implements Runnable{
    String urlPath;
    String key = "7faca29019492d112ff0d122f39b7cdd7b304";

    Expense expense;
    Activity activity;
    Context context;

    public IsPaidPutAPI(Expense expense, Activity activity, Context context){
        this.expense = expense;
        this.activity = activity;
        this.context = context;
        this.urlPath = "https://androidprojectdbcock-056f.restdb.io/rest/expenses/" + expense.getExpenseId();
    }


    @Override
    public void run() {
        try {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urlPath);
                // Open a URL connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("PUT");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("x-apikey", key);
                urlConnection.setDoOutput(true);

                // Create a new Expense object with the updated fields
                Expense updatedExpense = new Expense();
                updatedExpense.setPaid();

                Gson gson = new Gson();
                String updatedExpenseJson = gson.toJson(updatedExpense);

                // Send the request
                OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                wr.write(updatedExpenseJson);
                wr.flush();

                // Check the response code
                if (urlConnection.getResponseCode() >= 200 && urlConnection.getResponseCode() < 300) {
                    Log.d("HTTP-PUT", "PUT Success - " + updatedExpenseJson);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            UtilityClass.Toast(context, "Dépense mise à jour - " + expense.getExpenseType());
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
