package com.example.bg51az.comcet325bg51az;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.bg51az.comcet325bg51az.convert.Currency;

public class CurrencyActivity extends AppCompatActivity {

    TextView currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        Currency curr = new Currency();

        currency = (TextView)findViewById(R.id.currency);

        currency.setText(curr.GBP+ " " + curr.USD);
    }


}
