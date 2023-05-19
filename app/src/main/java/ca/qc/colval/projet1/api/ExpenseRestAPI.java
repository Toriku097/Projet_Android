package ca.qc.colval.projet1.api;

import android.content.Context;
import android.util.Log;

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
                url = new URL("https://jsonplaceholder.typicode.com/posts");
                //open a URL connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setDoOutput(true);
                String newPost = "{" +
                        "\"expense_type\": \""+expense.getExpenseType()+"\"," +
                        "\"amount\": \""+expense.getAmount()+"\"," +
                        "\"payment_method\": \""+expense.getPaymentMethod()+"\"," +
                        "\"supplier\": \""+expense.getSupplier()+"\"," +
                        "\"project\": \""+expense.getBankAccount()+"\"," +
                        "\"bank_account\": \""+expense.getBankAccount()+"\"," +
                        "\"date\": \""+expense.getDate()+"\"," +
                        "}";
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
