package com.example.bg51az.comcet325bg51az.convert;

import android.os.AsyncTask;

import org.json.JSONException;

public class JSONCurrencyTask extends AsyncTask<String, Void, Currency> {
    @Override
    protected Currency doInBackground(String... params){
        // Log.d("data", params);
        Currency currency = new Currency();
        String data = ((new CurrencyHttpClient()).getCurrencyData());
        if(data == null){
            return null;
        } else {
            try{
                currency = CurrencyParser.getCurrency(data);
            } catch (JSONException e){
                e.printStackTrace();
            }
            return currency;
        }
    }

    @Override
    protected void onPostExecute(Currency currency){
        super.onPostExecute(currency);
        if(currency != null){

        }
        else{
            //Toast.makeText(getApplicationContext(), "Unable to retrieve data", Toast.LENGTH_LONG).show();
        }
    }
}
