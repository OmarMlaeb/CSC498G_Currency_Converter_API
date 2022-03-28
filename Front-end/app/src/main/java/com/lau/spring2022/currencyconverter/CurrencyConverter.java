package com.lau.spring2022.currencyconverter;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

public class CurrencyConverter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar of this activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_currency_converter);
    }
}