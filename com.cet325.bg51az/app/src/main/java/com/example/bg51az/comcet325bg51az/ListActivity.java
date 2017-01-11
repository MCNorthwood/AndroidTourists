package com.example.bg51az.comcet325bg51az;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bg51az.comcet325bg51az.convert.Currency;
import com.example.bg51az.comcet325bg51az.convert.CurrencyHttpClient;
import com.example.bg51az.comcet325bg51az.convert.CurrencyParser;
import com.example.bg51az.comcet325bg51az.database.DBOpenHelper;
import com.example.bg51az.comcet325bg51az.database.Tourist;
import com.example.bg51az.comcet325bg51az.database.TouristCursorAdapter;

import org.json.JSONException;

public class ListActivity extends AppCompatActivity
{
    DBOpenHelper dbOpenHelper = new DBOpenHelper(this);

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_tourists);

        // Get ListView to populate
        final ListView list = (ListView)findViewById(android.R.id.list);

        listPopulate(list);

        list.setLongClickable(true);

        JSONCurrencyTask task = new JSONCurrencyTask();
        task.execute();

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                LayoutInflater _li = LayoutInflater.from(ListActivity.this);
                View getEmpIdView = _li.inflate(R.layout.tourist_list_add, null);

                AlertDialog.Builder alertDiaBui = new AlertDialog.Builder(ListActivity.this);
                // Set tourist_list_add.xml to alert dialog builder
                alertDiaBui.setView(getEmpIdView);

                final EditText nameInput = (EditText)getEmpIdView.findViewById(R.id.editInputTouristName);
                final EditText locationInput = (EditText)getEmpIdView.findViewById(R.id.editInputTouristLocation);
                final EditText descriptionInput = (EditText)getEmpIdView.findViewById(R.id.editInputTouristDescription);
                final EditText latitudeInput = (EditText) getEmpIdView.findViewById(R.id.editInputTouristLatitude);
                final EditText longitudeInput = (EditText) getEmpIdView.findViewById(R.id.editInputTouristLongitude);
                final EditText priceInput = (EditText)getEmpIdView.findViewById(R.id.editInputTouristPrice);
                final EditText rankInput = (EditText)getEmpIdView.findViewById(R.id.editInputTouristRank);
                final EditText notesInput = (EditText)getEmpIdView.findViewById(R.id.editInputTouristNotes);

                alertDiaBui.setCancelable(true)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Tourist _tourist = new Tourist();
                                _tourist.name = nameInput.getText().toString();
                                _tourist.location = locationInput.getText().toString();
                                _tourist.description = descriptionInput.getText().toString();
                                _tourist.favourite = false;
                                _tourist.image = "@drawable/earth";
                                _tourist.geolocation = latitudeInput.getText().toString() + "," + longitudeInput.getText().toString();
                                _tourist.price = Double.parseDouble(priceInput.getText().toString());
                                _tourist.rank = Integer.parseInt(rankInput.getText().toString());
                                _tourist.deletable = true;
                                //_tourist.notes = notesInput.getText.toString();

                                insertTourist(_tourist);

                            }
                        }).create().show();
                listPopulate(list);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                LayoutInflater li = LayoutInflater.from(ListActivity.this);
                View getEmpIdView = li.inflate(R.layout.tourist_list_update, null);

                AlertDialog.Builder alertDiBu = new AlertDialog.Builder(ListActivity.this);
                alertDiBu.setView(getEmpIdView);

                final EditText nameUpdate = (EditText)getEmpIdView.findViewById(R.id.editUpdateTouristName);
                final EditText locationUpdate = (EditText)getEmpIdView.findViewById(R.id.editUpdateTouristLocation);
                final EditText descriptionUpdate = (EditText)getEmpIdView.findViewById(R.id.editUpdateTouristDescription);
                final EditText latitudeUpdate = (EditText) getEmpIdView.findViewById(R.id.editUpdateTouristLatitude);
                final EditText longitudeUpdate = (EditText) getEmpIdView.findViewById(R.id.editUpdateTouristLongitude);
                final EditText priceUpdate = (EditText)getEmpIdView.findViewById(R.id.editUpdateTouristPrice);
                final EditText rankUpdate = (EditText)getEmpIdView.findViewById(R.id.editUpdateTouristRank);
                final EditText notesUpdate = (EditText)getEmpIdView.findViewById(R.id.editUpdateTouristNotes);

                // get Id of Item and then display clicked item into EditTexts
                final Tourist _tourist = getTourist(Long.toString(parent.getItemIdAtPosition(position)));
                nameUpdate.setText(_tourist.name);
                locationUpdate.setText(_tourist.location);
                descriptionUpdate.setText(_tourist.description);
                String[] geoLoc = _tourist.geolocation.split(",");
                latitudeUpdate.setText(geoLoc[0]);
                longitudeUpdate.setText(geoLoc[1]);
                priceUpdate.setText(Double.toString(_tourist.price));
                rankUpdate.setText(Integer.toString(_tourist.rank));
                //notesUpdate.setText(_tourist.notes);

                alertDiBu.setCancelable(true)
                        .setPositiveButton("Update", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                // get previous information and then change it
                                Tourist updateTourist = dbOpenHelper.getTouristByName(_tourist.name);
                                updateTourist.name = nameUpdate.getText().toString();
                                updateTourist.location = locationUpdate.getText().toString();
                                updateTourist.description = descriptionUpdate.getText().toString();
                                updateTourist.favourite = false;
                                updateTourist.image = "@drawable/earth";
                                updateTourist.geolocation = latitudeUpdate.getText().toString() + "," + longitudeUpdate.getText().toString();
                                updateTourist.price = Double.parseDouble(priceUpdate.getText().toString());
                                updateTourist.rank = Integer.parseInt(rankUpdate.getText().toString());
                                updateTourist.deletable = true;

                                // Pass through previous info and updated info
                                updateTourist(_tourist, updateTourist);
                            }
                        }).create().show();
                listPopulate(list); //Update once updated
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id){
                LayoutInflater layIn = LayoutInflater.from(ListActivity.this);
                View getEmpIdView = layIn.inflate(R.layout.dialog_delete_tourist_info, null);

                // Pop up layout
                final AlertDialog.Builder alertDB = new AlertDialog.Builder(ListActivity.this);
                alertDB.setView(getEmpIdView);

                // Display information in TextView of what is contained in Tourist before deleting.
                final TextView touristString = (TextView)getEmpIdView.findViewById(R.id.txtTouristString);
                final Tourist _tourist = getTourist(Long.toString(parent.getItemIdAtPosition(position)));
                touristString.setText(_tourist.toString());

                alertDB.setCancelable(true)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener()
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
                listPopulate(list); //Update once deleted
                return true;
            }
        });
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
            if (_tourist.deletable) // Check previous info to see whether it can be deleted
            {
                Toast.makeText(getApplicationContext(), "Updating " + updateTourist.name, Toast.LENGTH_SHORT).show();
                dbOpenHelper.updateTourist(updateTourist);
                Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_LONG).show();

            }
            else
            {
                Toast.makeText(getApplicationContext(), "This item is not updatable", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Failed to delete", Toast.LENGTH_LONG).show();
        }
    }

    private Tourist getTourist (String _id)
    {
        int id = Integer.parseInt(_id);

        Tourist tourist = dbOpenHelper.getTourist(id);
        return tourist;
    }

    private void listPopulate(ListView list)
    {
        // SQLiteOpenHelper class connecting SQLite
        DBOpenHelper databaseHelper = new DBOpenHelper(this);

        // Get access to underlying writable database
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        //Query for items from the database and get a cursor back
        Cursor cursorTODO = database.rawQuery("SELECT * FROM tourist", null);

        // Setup cursor adapter using cursor from last step
        TouristCursorAdapter adapterTODO = new TouristCursorAdapter(this, cursorTODO, 0);

        // Attach cursor adapter to the ListView
        list.setAdapter(adapterTODO);
    }

    private class JSONCurrencyTask extends AsyncTask<String, Void, Currency>
    {
        @Override
        protected Currency doInBackground(String... params)
        {
            Currency currency = new Currency();
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
        protected void onPostExecute(Currency currency)
        {
            super.onPostExecute(currency);
            if(currency != null)
            {
                Toast.makeText(getApplicationContext(), "Currency Exchange Loaded", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Unable to retrieve data", Toast.LENGTH_LONG).show();
            }
        }
    }
}