package com.example.bg51az.comcet325bg51az.database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bg51az.comcet325bg51az.R;

public class TouristCursorAdapter extends CursorAdapter {
    public TouristCursorAdapter(Context context, Cursor cursor, int flags) { super (context, cursor, flags); }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup){
        return LayoutInflater.from(context).inflate(R.layout.tourist_list_item,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        //Get the information
        String touristName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.KEY_NAME));
        String touristLocation = cursor.getString(cursor.getColumnIndex(DBOpenHelper.KEY_LOCATION));
        String touristDescription = cursor.getString(cursor.getColumnIndex(DBOpenHelper.KEY_DESCRIPTION));
        String touristImage = cursor.getString(cursor.getColumnIndex(DBOpenHelper.KEY_IMAGE));
        String touristGeolocation = cursor.getString(cursor.getColumnIndex(DBOpenHelper.KEY_GEOLOCATION));
        String touristPrice = cursor.getString(cursor.getColumnIndex(DBOpenHelper.KEY_PRICE));
        String touristRank = cursor.getString(cursor.getColumnIndex(DBOpenHelper.KEY_RANK));

        // Initialise TextView and ImageView
        TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        TextView locationTextView = (TextView) view.findViewById(R.id.locationTextView);
        TextView descriptionTextView = (TextView) view.findViewById(R.id.descriptionTextView);
        TextView geolocationTextView = (TextView) view.findViewById(R.id.geolocationTextView);
        TextView priceTextView = (TextView) view.findViewById(R.id.priceTextView);
        TextView rankTextView = (TextView) view.findViewById(R.id.rankTextView);
        ImageView imageIcon = (ImageView) view.findViewById(R.id.imageIcon);

        // Set the TextView with the information gathered from the db
        nameTextView.setText(touristName);
        locationTextView.setText(touristLocation);
        descriptionTextView.setText(touristDescription);
        geolocationTextView.setText(touristGeolocation);
        priceTextView.setText("£" + touristPrice);
        rankTextView.setText("Rank: " + touristRank);

        // How to set the ImageView
        int id = context.getResources().getIdentifier(touristImage, null, context.getPackageName());
        imageIcon.setImageResource(id);
    }
}
