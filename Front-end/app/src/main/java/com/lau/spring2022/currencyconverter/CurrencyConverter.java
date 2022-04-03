package com.lau.spring2022.currencyconverter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConverter extends AppCompatActivity {

    TextView rate;
    EditText amount;
    Spinner sp1;
    Spinner sp2;
    Button convert;
    TextView result;

    // any function that will run in parallel with the application, as the app wont be loading or waiting for the execution of that function
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) { // pre execute
            String result = "";
            URL url; // manages all the information present in the URL string
            HttpURLConnection http; // used to make a single request

            // to catch any error that may be executed when we run the code
            try{
                url = new URL(urls[0]); // sending the url
                http = (HttpURLConnection) url.openConnection(); // opening connection between the app and url (api)

                InputStream in = http.getInputStream();
                InputStreamReader reader = new InputStreamReader(in); // to read the output of the api
                int data = reader.read(); // cursor to read the output of the api

                while( data != -1){ // didn't reach the end of the file
                    char current = (char) data; // take a character every time
                    result += current; // append the character read to the string result
                    data = reader.read(); // not to run to an infinite loop (move the cursor one character)

                }
            } catch(Exception e){
                e.printStackTrace();
                return null;
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s){ // when the api is executed
            super.onPostExecute(s);

            try{
                JSONObject json = new JSONObject(s); // object of JSONObject and assigned to json
                String rate_value = json.getString("rate_value"); // convert the string to a json object
                rate.setText(rate_value);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_currency_converter);

        rate = (TextView) findViewById(R.id.rate);
        amount = (EditText) findViewById(R.id.amount);
        sp1 = (Spinner) findViewById(R.id.spinner_bar1);
        sp2 = (Spinner) findViewById(R.id.spinner_bar2);
        convert = (Button) findViewById(R.id.convert);
        result = (TextView) findViewById(R.id.result);

        // spinner to choose the currency
        String[] from = {"USD", "LBP"};
        ArrayAdapter<String> adp1 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, from);
        sp1.setAdapter(adp1);

        // spinner to choose the currency
        String[] to = {"LBP", "USD"};
        ArrayAdapter<String> adp2 = new ArrayAdapter<>(this,  androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, to);
        sp2.setAdapter(adp2);

        String url = "http://192.168.0.108/Currency_Converter_Server/get_rate.php"; // url of the local host api getting the rate

        DownloadTask task = new DownloadTask(); // create a DownloadTask object and assign it to task
        task.execute(url); // executing the url
    }

    /* any function that will run in parallel with the application, as the app wont be loading or waiting for the execution of that function
       and to send the data to the database, and to retrieve the conversion calculation from the api and display it on the screen */
    public class DownloadTask2 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) { // pre execute
            String result = "";
            URL url; // manages all the information present in the URL string
            HttpURLConnection http; // used to make a single request

            // to catch any error that may be executed when we run the code
            try{
                url = new URL(urls[0]); // sending the url
                http = (HttpURLConnection) url.openConnection(); // opening connection between the app and url (api)

                InputStream in = http.getInputStream();
                InputStreamReader reader = new InputStreamReader(in); // to read the output of the api
                int data = reader.read(); // cursor to read the output of the api

                while( data != -1){ // didn't reach the end of the file
                    char current = (char) data; // take a character every time
                    result += current; // append the character read to the string result
                    data = reader.read(); // not to run to an infinite loop (move the cursor one character)

                }
            } catch(Exception e){
                e.printStackTrace();
                return null;
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) { // when the api is executed
            super.onPostExecute(s);

            try{
                JSONObject json = new JSONObject(s); // object of JSONObject and assigned to json
                String conversion_value = json.getString("conversion_value"); // convert the string to a json object
                result.setText(conversion_value);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    // to calculate the conversion of the amount
    public void convert(View view) {
        String amount_value = amount.getText().toString(); // getting the amount as a string
        String rate_value = rate.getText().toString(); // getting the rate as a string

        double parse_amount = Double.parseDouble(amount_value); // parsing the amount string to a double
        double parse_rate = Double.parseDouble(rate_value); // parsing the rate string to a double

        if (amount_value.equals("")) {
            amount.setError("Enter an Amount to Convert!");
        } else if(rate_value.isEmpty()){
            rate.setError("Wait or Please Try Again Later");
        } else if(sp1.getSelectedItem().toString().equals("LBP") && sp2.getSelectedItem().toString().equals("LBP")){
            Toast.makeText(getApplicationContext(), "ERROR: converting to same currency", Toast.LENGTH_LONG).show();
        } else if(sp1.getSelectedItem().toString().equals("USD") && sp2.getSelectedItem().toString().equals("USD")){
            Toast.makeText(getApplicationContext(), "ERROR: converting to same currency", Toast.LENGTH_LONG).show();
        } else {
            // url of the local host of the api to send data to the database
            String url2 = "http://192.168.0.108/Currency_Converter_Server/post_conversions.php?amount=" + parse_amount +
                    "&rate=" + parse_rate + "&converted_from=" + sp1.getSelectedItem().toString() + "&converted_to=" + sp2.getSelectedItem().toString();
            // concatenated the values to the api url to send the data to it

            DownloadTask2 task2 = new DownloadTask2();
            task2.execute(url2);
        }
    }
}