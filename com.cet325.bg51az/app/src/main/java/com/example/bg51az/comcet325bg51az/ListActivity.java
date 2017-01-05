package com.example.bg51az.comcet325bg51az;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.example.bg51az.comcet325bg51az.database.DBOpenHelper;
import com.example.bg51az.comcet325bg51az.database.TouristCursorAdapter;

public class ListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private CursorAdapter cursorAdapter = null;
    Uri uri;
    SQLiteDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_tourists);

        cursorAdapter = new TouristCursorAdapter(this, null, 0);
        listPopulate();
    }

    private void restartLoader() { getLoaderManager().restartLoader(0,null, this); }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle){
        return new CursorLoader(this, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor){
        cursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) { cursorAdapter.swapCursor(null); }

    void listPopulate(){
        // SQLiteOpenHelper class connecting SQLite
        DBOpenHelper databaseHelper = new DBOpenHelper(this);

        // Get access to underlying writable database
        database = databaseHelper.getWritableDatabase();

        //Query for items from teh database and get a cursor back
        Cursor cursorTODO = database.rawQuery("SELECT * FROM tourist", null);

        // Get ListView to populate
        ListView list = (ListView)findViewById(android.R.id.list);

        // Setup cursor adapter using cursor from last step
        TouristCursorAdapter adapterTODO = new TouristCursorAdapter(this, cursorTODO, 0);

        // Attach cursor adapter to the ListView
        list.setAdapter(adapterTODO);
    }
}
