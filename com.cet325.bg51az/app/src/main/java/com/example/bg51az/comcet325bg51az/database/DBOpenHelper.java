package com.example.bg51az.comcet325bg51az.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class DBOpenHelper extends SQLiteOpenHelper
{
    // Database Version
    private static final int DATABASE_VERSION = 3;
    //Database Name
    private static final String DATABASE_NAME = "TouristDB";

    // Static constants to use in CRUD
    //Table name
    private static final String TABLE_TOURIST = "tourist";

    //Tourist Table Columns names
    private static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_FAVOURITE = "favourite";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_GEOLOCATION = "geolocation";
    public static final String KEY_PRICE = "price";
    public static final String KEY_RANK = "rank";
    public static final String KEY_DELETABLE = "deletable";

    //Columns
    private static final String[] COLUMNS = {KEY_ID, KEY_NAME, KEY_LOCATION, KEY_DESCRIPTION, KEY_FAVOURITE, KEY_IMAGE, KEY_GEOLOCATION, KEY_PRICE, KEY_RANK, KEY_DELETABLE};

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Check Text Fields for text length e.g. for Description
    //Image possibly be put in externally?
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL statement to create tourist table
        String CREATE_TOURIST_TABLE = "CREATE TABLE " + TABLE_TOURIST + " ( " +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, " +
                KEY_LOCATION + " TEXT, " +
                KEY_DESCRIPTION + " TEXT, " +
                KEY_FAVOURITE + " INTEGER, " +
                KEY_IMAGE + " TEXT, " +
                KEY_GEOLOCATION + " TEXT, " +
                KEY_PRICE + " DOUBLE, " +
                KEY_RANK + " INTEGER, " +
                KEY_DELETABLE + " INTEGER )";

        //create tourist table
        db.execSQL(CREATE_TOURIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older tourist table if already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOURIST);

        //create fresh tourist table
        this.onCreate(db);
    }

    //-------------------------------------------
    // CRUD - Create, Read, Update, Delete

    public void addTourist(Tourist tourist) {
        Log.d("addTourist", tourist.toString());

        //1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        String n = db.getPath();
        Log.d("addTourist", n);

        //2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, tourist.name); //get name
        values.put(KEY_LOCATION, tourist.location); //get location
        values.put(KEY_DESCRIPTION, tourist.description); //get description
        values.put(KEY_FAVOURITE, tourist.favourite); //get favourite
        values.put(KEY_IMAGE, tourist.image); //get image
        values.put(KEY_GEOLOCATION, tourist.geolocation); //get geolocation
        values.put(KEY_PRICE, tourist.price); //get price
        values.put(KEY_RANK, tourist.rank); //get rank
        values.put(KEY_DELETABLE, tourist.deletable); //get rank

        //3. insert
        db.insert(TABLE_TOURIST, //table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values = column values

        //4. close
        db.close();
    }

    public Tourist getTourist(int id)
    {
        //1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        //2. build query
        Cursor cursor = db.query(
                TABLE_TOURIST, //a. table
                COLUMNS, //b. column names
                KEY_ID + " = ?", //c. selections
                new String[]{String.valueOf(id)}, //d.  selections args
                null, //e. group by
                null, //f. having
                null, //g. order by
                null); //h. limit

        //3. if results get the first one
        Tourist tourist = null;
        if (cursor != null && cursor.moveToFirst())
        {
            //4. build book object
            tourist = new Tourist();
            tourist.id = cursor.getInt(0);
            tourist.name = cursor.getString(1);
            tourist.location = cursor.getString(2);
            tourist.description = cursor.getString(3);
            tourist.favourite = (cursor.getInt(cursor.getColumnIndex(KEY_FAVOURITE)) == 1);
            tourist.image = cursor.getString(5);
            tourist.geolocation = cursor.getString(6);
            tourist.price = cursor.getDouble(7);
            tourist.rank = cursor.getInt(8);
            tourist.deletable = (cursor.getInt(cursor.getColumnIndex(KEY_DELETABLE)) == 1);

            Log.d("getTourist(" + id + ")", tourist.toString());
        }
        cursor.close();
        //5. return tourist
        return tourist;
    }

    public List<Tourist> getAllTourists()
    {
        List<Tourist> tourists = new LinkedList<Tourist>();

        //1. build the query
        String query = "SELECT  * FROM " + TABLE_TOURIST;

        //2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //3. go over each row, build tracks and add it to list
        Tourist tourist = null;
        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                tourist = new Tourist();
                tourist.id = cursor.getInt(0);
                tourist.name = cursor.getString(1);
                tourist.location = cursor.getString(2);
                tourist.description = cursor.getString(3);
                tourist.favourite = (cursor.getInt(cursor.getColumnIndex(KEY_FAVOURITE)) == 1);
                tourist.image = cursor.getString(5);
                tourist.geolocation = cursor.getString(6);
                tourist.price = cursor.getDouble(7);
                tourist.rank = cursor.getInt(8);
                tourist.deletable = (cursor.getInt(cursor.getColumnIndex(KEY_DELETABLE)) == 1);

                //Add tourist to tourists
                tourists.add(tourist);
            } while (cursor.moveToNext());
        }
        Log.d("getAllTracks()", tourists.toString());

        cursor.close();
        //return tourists
        return tourists;
    }

    //Update single tourist
    public int updateTourist(Tourist tourist)
    {
        //1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, tourist.name); //get name
        values.put(KEY_LOCATION, tourist.location); //get location
        values.put(KEY_DESCRIPTION, tourist.description); //get description
        values.put(KEY_FAVOURITE, tourist.favourite); //get favourite
        values.put(KEY_IMAGE, tourist.image); //get image
        values.put(KEY_GEOLOCATION, tourist.geolocation); //get geolocation
        values.put(KEY_PRICE, tourist.price); //get price
        values.put(KEY_RANK, tourist.rank); //get rank
        values.put(KEY_DELETABLE, tourist.deletable); //get rank

        //3. updating row
        int i = db.update(TABLE_TOURIST, //table
                values, //column/value
                KEY_ID + " = ?", //selections
                new String[]{String.valueOf(tourist.id)});//selection args

        //4. close
        db.close();

        return i;
    }

    //Delete single tourist
    public void deleteTourist(Tourist tourist)
    {
        //1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        //2. delete
        db.delete(TABLE_TOURIST,
                KEY_ID + " = ?",
                new String[]{String.valueOf(tourist.id)});

        //3. close
        db.close();

        Log.d("deleteTrack", tourist.toString());
    }

    public Tourist getTouristByName(String name)
    {
        //1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        //2. build query
        Cursor cursor =
                db.query(TABLE_TOURIST, //a. table
                        COLUMNS, //b. column names
                        KEY_NAME + " = ?", //c. selections
                        new String[]{name}, //d. selection args
                        null, //e. group by
                        null, //f. having
                        null, //g. order by
                        null); //h. limit

        //3. if a match was made
        Tourist tourist = null;
        if (cursor != null && cursor.moveToFirst())
        {
            //4. build tourist object
            tourist = new Tourist();
            tourist.id = cursor.getInt(0);
            tourist.name = cursor.getString(1);
            tourist.location = cursor.getString(2);
            tourist.description = cursor.getString(3);
            tourist.favourite = (cursor.getInt(cursor.getColumnIndex(KEY_FAVOURITE)) == 1);
            tourist.image = cursor.getString(5);
            tourist.geolocation = cursor.getString(6);
            tourist.price = cursor.getDouble(7);
            tourist.rank = cursor.getInt(8);
            tourist.deletable = (cursor.getInt(cursor.getColumnIndex(KEY_DELETABLE)) == 1);

            Log.d("getName(" + name + ")", tourist.toString());
        }

        cursor.close();
        //5. return tourist
        return tourist;
    }
}
