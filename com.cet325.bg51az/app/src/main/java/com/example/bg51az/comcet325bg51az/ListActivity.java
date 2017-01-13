package com.example.bg51az.comcet325bg51az;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Rating;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bg51az.comcet325bg51az.convert.CurrencyExchange;
import com.example.bg51az.comcet325bg51az.convert.CurrencyHttpClient;
import com.example.bg51az.comcet325bg51az.convert.CurrencyParser;
import com.example.bg51az.comcet325bg51az.database.DBOpenHelper;
import com.example.bg51az.comcet325bg51az.database.Tourist;
import com.example.bg51az.comcet325bg51az.database.TouristCursorAdapter;

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ListActivity extends AppCompatActivity
{
    DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
    CurrencyExchange currency = new CurrencyExchange();

    ListView list;

    String queryAddon = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_tourists);

        final LayoutInflater _li = LayoutInflater.from(ListActivity.this);

        try
        {
            listPopulate(queryAddon); //Populate the List with a query
        }
        catch (NullPointerException e){}

        list.setLongClickable(true);

        JSONCurrencyTask task = new JSONCurrencyTask();
        task.execute();

        // Fab Button adds new Tourist info into db
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                View getEmpIdView = _li.inflate(R.layout.tourist_list_add, null);

                AlertDialog.Builder alertDiaBui = new AlertDialog.Builder(ListActivity.this);
                // Set tourist_list_add.xml to alert dialog builder
                alertDiaBui.setView(getEmpIdView);

                final EditText nameInput = (EditText)getEmpIdView.findViewById(R.id.editInputTouristName);
                final EditText locationCityInput = (EditText)getEmpIdView.findViewById(R.id.editInputTouristCity);
                final EditText locationCountryInput = (EditText)getEmpIdView.findViewById(R.id.editInputTouristCountry);
                final EditText descriptionInput = (EditText)getEmpIdView.findViewById(R.id.editInputTouristDescription);
                final EditText latitudeInput = (EditText) getEmpIdView.findViewById(R.id.editInputTouristLatitude);
                final EditText longitudeInput = (EditText) getEmpIdView.findViewById(R.id.editInputTouristLongitude);
                final EditText priceInput = (EditText)getEmpIdView.findViewById(R.id.editInputTouristPrice);
                final RatingBar rankInput = (RatingBar)getEmpIdView.findViewById(R.id.ratingBarInputRank);

                alertDiaBui.setCancelable(true)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Tourist _tourist = new Tourist();
                                _tourist.name = nameInput.getText().toString();
                                _tourist.location = locationCityInput.getText().toString() + "," + locationCountryInput.getText().toString();
                                _tourist.description = descriptionInput.getText().toString();
                                _tourist.favourite = false;
                                _tourist.image = "@drawable/earth";
                                _tourist.geolocation = latitudeInput.getText().toString() + "," + longitudeInput.getText().toString();
                                _tourist.price = Double.parseDouble(priceInput.getText().toString());
                                _tourist.rank = (int)rankInput.getRating();
                                _tourist.deletable = true;

                                insertTourist(_tourist);

                            }
                        }).create().show();
                listPopulate(queryAddon);
            }
        });

        // Detail view displaying more info e.g. converted currency
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                View getEmpIdView = _li.inflate(R.layout.tourist_list_detail, null);

                // Pop up layout
                final AlertDialog.Builder alertDB = new AlertDialog.Builder(ListActivity.this);
                alertDB.setView(getEmpIdView);

                // Display information in TextView of what is contained in Tourist before deleting.
                final TextView touristName = (TextView)getEmpIdView.findViewById(R.id.txtTouristName);
                final TextView touristLocation = (TextView)getEmpIdView.findViewById(R.id.txtTouristLocation);
                final TextView touristDescription = (TextView)getEmpIdView.findViewById(R.id.txtTouristDescription);
                final TextView touristGeolocation = (TextView)getEmpIdView.findViewById(R.id.txtTouristName);
                final TextView touristPrice = (TextView)getEmpIdView.findViewById(R.id.txtTouristPrice);
                final TextView touristPriceExchange = (TextView)getEmpIdView.findViewById(R.id.txtTouristPriceExchange);
                final RatingBar ratingRank = (RatingBar)getEmpIdView.findViewById(R.id.ratingBarDisplayRank);
                final EditText touristNote = (EditText)getEmpIdView.findViewById(R.id.editUpdateTouristNote);
                final EditText touristPlanned = (EditText)getEmpIdView.findViewById(R.id.editUpdateTouristPlanned);
                final EditText touristVisited = (EditText)getEmpIdView.findViewById(R.id.editUpdateTouristVisited);

                // get Id of Item and then display clicked item into EditTexts
                final Tourist _tourist = getTourist(Long.toString(parent.getItemIdAtPosition(position)));
                touristName.setText(_tourist.name);
                touristLocation.setText(_tourist.location);
                touristDescription.setText(_tourist.description);
                touristGeolocation.setText(_tourist.location);
                touristPrice.setText(" £" + Double.toString(_tourist.price));
                if(_tourist.price != 0 || _tourist.price != 0.0)
                {
                    touristPriceExchange.setText(" " + currency.getSymbol(currency.getFav()) +
                            Double.toString(currency.convertCurrency(_tourist.price, currency.getFavourite())) +
                    " Rate:" + currency.getFavourite());
                }
                else
                {
                    touristPrice.setText(" Free");
                }
                ratingRank.setRating(_tourist.rank);
                touristNote.setText(_tourist.notes);

                final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                if(_tourist.planned != null)
                {
                    touristPlanned.setText(sdf.format(_tourist.planned));
                }
                if(_tourist.visited != null)
                {
                    touristVisited.setText(sdf.format(_tourist.visited));
                }

                alertDB.setCancelable(true)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDB.create().cancel();
                            }
                        })
                        .setPositiveButton("Update", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                // get previous information and then change it
                                Tourist updateTourist = dbOpenHelper.getTouristByName(_tourist.name);
                                updateTourist.notes = touristNote.getText().toString();
                                try {
                                    updateTourist.planned = sdf.parse(touristPlanned.getText().toString());
                                    updateTourist.visited = sdf.parse(touristVisited.getText().toString());
                                } catch (ParseException e){
                                    Toast.makeText(getApplicationContext(), "Planned and Visit was not updated", Toast.LENGTH_LONG).show();
                                }

                                // Pass through previous info and updated info
                                updateTourist(_tourist, updateTourist);
                            }
                        }).create().show();
                listPopulate(queryAddon); //Update once updated
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id)
            {
                View getEmpIdView = _li.inflate(R.layout.tourist_list_update, null);

                AlertDialog.Builder alertDiBu = new AlertDialog.Builder(ListActivity.this);
                alertDiBu.setView(getEmpIdView);

                // get Id of Item and then display clicked item into EditTexts
                final Tourist _tourist = getTourist(Long.toString(parent.getItemIdAtPosition(position)));
                if(!_tourist.deletable)
                {
                    Toast.makeText(getApplicationContext(), "This item is not deletable or updatable", Toast.LENGTH_LONG).show();
                    alertDiBu.create().cancel();
                    return false;
                }

                final EditText nameUpdate = (EditText)getEmpIdView.findViewById(R.id.editUpdateTouristName);
                final EditText locationCityUpdate = (EditText)getEmpIdView.findViewById(R.id.editUpdateTouristCity);
                final EditText locationCountryUpdate = (EditText)getEmpIdView.findViewById(R.id.editUpdateTouristCountry);
                final EditText descriptionUpdate = (EditText)getEmpIdView.findViewById(R.id.editUpdateTouristDescription);
                final EditText latitudeUpdate = (EditText) getEmpIdView.findViewById(R.id.editUpdateTouristLatitude);
                final EditText longitudeUpdate = (EditText) getEmpIdView.findViewById(R.id.editUpdateTouristLongitude);
                final EditText priceUpdate = (EditText)getEmpIdView.findViewById(R.id.editUpdateTouristPrice);
                final RatingBar rankUpdate = (RatingBar)getEmpIdView.findViewById(R.id.ratingBarUpdateRank);

                //Input previous info into
                nameUpdate.setText(_tourist.name);
                String[] locationArray = _tourist.location.split(",");
                locationCityUpdate.setText(locationArray[0]);
                locationCountryUpdate.setText(locationArray[1]);
                descriptionUpdate.setText(_tourist.description);
                String[] geoLoc = _tourist.geolocation.split(",");
                latitudeUpdate.setText(geoLoc[0]);
                longitudeUpdate.setText(geoLoc[1]);
                priceUpdate.setText(Double.toString(_tourist.price));
                rankUpdate.setRating(_tourist.rank);


                alertDiBu.setCancelable(true)
                        .setPositiveButton("Update", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                // get previous information and then change it
                                Tourist updateTourist = dbOpenHelper.getTouristByName(_tourist.name);
                                updateTourist.name = nameUpdate.getText().toString();
                                updateTourist.location = locationCityUpdate.getText().toString() + "," + locationCountryUpdate.getText().toString();
                                updateTourist.description = descriptionUpdate.getText().toString();
                                updateTourist.favourite = false;
                                updateTourist.image = "@drawable/earth";
                                updateTourist.geolocation = latitudeUpdate.getText().toString() + "," + longitudeUpdate.getText().toString();
                                updateTourist.price = Double.parseDouble(priceUpdate.getText().toString());
                                updateTourist.rank = (int)rankUpdate.getRating();
                                updateTourist.deletable = true;

                                // Pass through previous info and updated info
                                updateTourist(_tourist, updateTourist);
                            }
                        })
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                if(_tourist.deletable)
                                {
                                    deleteTourist(_tourist); // Confirm Deletion
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "This item is not deletable", Toast.LENGTH_LONG).show();
                                }
                            }
                        }).create().show();
                listPopulate(queryAddon); //Update once deleted
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.EUR:
                favCurrency("EUR");
                return true;
            case R.id.USD:
                favCurrency("USD");
                return true;
            case R.id.JPY:
                favCurrency("JPY");
                return true;
            case R.id.CNY:
                favCurrency("CNY");
                return true;
            case R.id.KRW:
                favCurrency("KRW");
                return true;
            case R.id.orderAll:
                queryAddon = "ORDER BY NAME ASC";
                listPopulate(queryAddon);
                return true;
            case R.id.orderFav:
                queryAddon = "WHERE FAVOURITE == 1 ORDER BY RANK DESC";
                listPopulate(queryAddon);
                return true;
            case R.id.orderPlanned:
                queryAddon = "WHERE PLANNED IS NOT NULL ORDER BY PLANNED ASC";
                listPopulate(queryAddon);
                return true;
            case R.id.orderVisited:
                queryAddon = "WHERE VISITED IS NOT NULL ORDER BY VISITED ASC";
                listPopulate(queryAddon);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void favCurrency(String favCurrency)
    {
        if(favCurrency.contains("EUR")){ // Euro
            currency.setFav(favCurrency);
            currency.setFavourite(currency.getEUR());
        }
        else if(favCurrency.contains("USD")){ // American Dollar
            currency.setFav(favCurrency);
            currency.setFavourite(currency.getUSD());
        }
        else if(favCurrency.contains("JPY")){ // American Dollar
            currency.setFav(favCurrency);
            currency.setFavourite(currency.getJPY());
        }
        else if(favCurrency.contains("CNY")){ // American Dollar
            currency.setFav(favCurrency);
            currency.setFavourite(currency.getCNY());
        }
        else if(favCurrency.contains("KRW")){ // American Dollar
            currency.setFav(favCurrency);
            currency.setFavourite(currency.getKRW());
        }
        else { // A default favourite needs to be set
            currency.setFav("EUR");
            currency.setFavourite(currency.getEUR());
        }
    }

    private void insertTourist(Tourist _tourist)
    {
        try
        {
            dbOpenHelper.addTourist(_tourist);
            Toast.makeText(getApplicationContext(),"Insert Successful", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Failed to add new Tourist Location", Toast.LENGTH_LONG).show();
        }
    }

    private void deleteTourist(Tourist _tourist)
    {
        try
        {
            if (_tourist.deletable)
            {
                Toast.makeText(getApplicationContext(), "Deleting " + _tourist.name, Toast.LENGTH_SHORT).show();
                dbOpenHelper.deleteTourist(_tourist);
                Toast.makeText(getApplicationContext(), "Delete Successful", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "This item is not deletable", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Failed to delete", Toast.LENGTH_LONG).show();
        }
    }

    private void updateTourist(Tourist _tourist, Tourist updateTourist)
    {
        try
        {
            /*if (_tourist.deletable) // Check previous info to see whether it can be deleted
            {*/
                Toast.makeText(getApplicationContext(), "Updating " + updateTourist.name, Toast.LENGTH_SHORT).show();
                dbOpenHelper.updateTourist(updateTourist);
                Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_LONG).show();
            /*}
            else
            {
                Toast.makeText(getApplicationContext(), "This item is not updatable", Toast.LENGTH_LONG).show();
            }*/
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Failed to Update", Toast.LENGTH_LONG).show();
        }
    }

    private Tourist getTourist (String _id)
    {
        int id = Integer.parseInt(_id);

        Tourist tourist = dbOpenHelper.getTourist(id);
        return tourist;
    }

    private void listPopulate(String request)
    {
        list = (ListView)findViewById(android.R.id.list);

        // SQLiteOpenHelper class connecting SQLite
        DBOpenHelper databaseHelper = new DBOpenHelper(this);

        // Get access to underlying writable database
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        String query = "SELECT * FROM tourist";

        if(request != null){
            query += " " + request;
        }

        //Query for items from the database and get a cursor back
        Cursor cursorTODO = database.rawQuery(query, null);

        // Setup cursor adapter using cursor from last step
        TouristCursorAdapter adapterTODO = new TouristCursorAdapter(this, cursorTODO, 0);

        // Attach cursor adapter to the ListView
        list.setAdapter(adapterTODO);
    }

    private class JSONCurrencyTask extends AsyncTask<String, Void, CurrencyExchange>
    {
        @Override
        protected CurrencyExchange doInBackground(String... params)
        {
            String data = ((new CurrencyHttpClient()).getCurrencyData());
            if(data == null)
            {
                return null;
            }
            else
            {
                try
                {
                    currency = CurrencyParser.getCurrency(data);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                return currency;
            }
        }

        @Override
        protected void onPostExecute(CurrencyExchange currency)
        {
            super.onPostExecute(currency);
            if(currency != null)
            {
                favCurrency(currency.getFav()); // Set the default currency (Euro)
                Toast.makeText(getApplicationContext(), "Currency Exchange Loaded", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Unable to retrieve Exchange data", Toast.LENGTH_LONG).show();
            }
        }
    }
}