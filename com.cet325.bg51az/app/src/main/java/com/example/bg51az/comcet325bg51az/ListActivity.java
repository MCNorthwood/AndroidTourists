package com.example.bg51az.comcet325bg51az;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.bg51az.comcet325bg51az.database.DBOpenHelper;
import com.example.bg51az.comcet325bg51az.database.Tourist;
import com.example.bg51az.comcet325bg51az.database.TouristCursorAdapter;

public class ListActivity extends AppCompatActivity {
    //private CursorAdapter cursorAdapter = null;
    DBOpenHelper dbOpenHelper = new DBOpenHelper(this);

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_tourists);

        //cursorAdapter = new TouristCursorAdapter(this, null, 0);

        // Get ListView to populate
        final ListView list = (ListView)findViewById(android.R.id.list);

        listPopulate(list);

        list.setLongClickable(true);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
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
                final EditText priceInput = (EditText)getEmpIdView.findViewById(R.id.editInputTouristPrice);
                final EditText rankInput = (EditText)getEmpIdView.findViewById(R.id.editInputTouristRank);
                final EditText notesInput = (EditText)getEmpIdView.findViewById(R.id.editInputTouristNotes);

                alertDiaBui.setCancelable(true)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                insertTourist(nameInput.getText().toString(),
                                        locationInput.getText().toString(),
                                        descriptionInput.getText().toString(),
                                        priceInput.getText().toString(),
                                        rankInput.getText().toString(),
                                        notesInput.getText().toString());
                                listPopulate(list);
                            }
                        }).create().show();
            }
        });

        //list.setOnClickListener(new AdapterView.OnClickListener());

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id){
                LayoutInflater layIn = LayoutInflater.from(ListActivity.this);
                View getEmpIdView = layIn.inflate(R.layout.dialog_delete_tourist_info, null);

                // Pop up layout
                final AlertDialog.Builder alertDB = new AlertDialog.Builder(ListActivity.this);
                alertDB.setView(getEmpIdView);

                final TextView touristString = (TextView)getEmpIdView.findViewById(R.id.txtTouristString);
                touristString.setText(getTouristInfo(Long.toString(parent.getItemIdAtPosition(position))));

                alertDB.setCancelable(true)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteTourist(Long.toString(parent.getItemIdAtPosition(position)));
                            }
                        }).create().show();
                listPopulate(list);
                return true;
            }
        });
    }

    private void insertTourist(String name, String location, String description, String price, String rank, String notes){
        try {
            dbOpenHelper.addTourist(new Tourist
                    (name,
                            location,
                            description,
                            false,
                            "@drawable/earth",
                            "0,0",
                            Double.parseDouble(price),
                            Integer.parseInt(rank),
                            true));
            Toast.makeText(getApplicationContext(),"Insert Successful", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Failed to add new Tourist Location", Toast.LENGTH_LONG).show();
        }
    }

    private void deleteTourist(String _id){
        Tourist tourist = getTourist(_id);

        if(tourist.deletable){
            try{
                Toast.makeText(getApplicationContext(),"Deleting " + tourist.name, Toast.LENGTH_SHORT).show();
                dbOpenHelper.deleteTourist(tourist);
                Toast.makeText(getApplicationContext(),"Delete Successful", Toast.LENGTH_LONG).show();
            }
            catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Failed to delete", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "This item is not deletable", Toast.LENGTH_LONG).show();
        }
    }

    private String getTouristInfo (String _id){
        int id = Integer.parseInt(_id);

        Tourist tourist = dbOpenHelper.getTourist(id);
        return tourist.toString();
    }

    private Tourist getTourist (String _id){
        int id = Integer.parseInt(_id);

        Tourist tourist = dbOpenHelper.getTourist(id);
        return tourist;
    }

    private void listPopulate(ListView list){
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
}