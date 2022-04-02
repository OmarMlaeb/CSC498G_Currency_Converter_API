package com.lau.spring2022.currencyconverter;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CurrencyConverter extends AppCompatActivity {

    TextView rate;
    EditText amount;
    Spinner sp1;
    Spinner sp2;
    Button convert;
    TextView result;

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
    }

    // to calculate the conversion of the amount
    public void convert(View view) {

    }
}