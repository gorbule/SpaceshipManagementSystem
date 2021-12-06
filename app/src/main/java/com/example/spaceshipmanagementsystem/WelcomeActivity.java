package com.example.spaceshipmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.spaceshipmanagementsystem.db.SqlLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private ListView shipList;

    //Data management variable
    List<String> shipsFromDb = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        SqlLiteHelper dbHelper = new SqlLiteHelper(this);

        //TODO: Move code to own method

//        shipsFromDb.add("Loud and Proud");
//        shipsFromDb.add("Escape Plan");
//        shipsFromDb.add("Flat Earth");
//        shipsFromDb.add("Star Trek");

        String title = "Loud and Proud";
        String mass = "100000";
        String mycolor = "Red";
        String location = "Daugavpils, Latvia";
        String image = "***IMAGE***";

        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(SqlLiteHelper.FeedReaderContract.SpaceshipEntry.COLUMN_NAME_TITLE, title);
        values.put(SqlLiteHelper.FeedReaderContract.SpaceshipEntry.COLUMN_NAME_MASS, mass);
        values.put(SqlLiteHelper.FeedReaderContract.SpaceshipEntry.COLUMN_NAME_COLOR, mycolor);
        values.put(SqlLiteHelper.FeedReaderContract.SpaceshipEntry.COLUMN_NAME_LOCATION, location);
        values.put(SqlLiteHelper.FeedReaderContract.SpaceshipEntry.COLUMN_NAME_IMAGE_URL, image);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(SqlLiteHelper.FeedReaderContract.SpaceshipEntry.TABLE_NAME, null, values);

        SQLiteDatabase readDb = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        String[] projection = {
                BaseColumns._ID,
                SqlLiteHelper.FeedReaderContract.SpaceshipEntry.COLUMN_NAME_TITLE
        };

        Cursor cursor = db.query(
                SqlLiteHelper.FeedReaderContract.SpaceshipEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {
            String myTitle = cursor.getString(cursor.getColumnIndexOrThrow
                    (SqlLiteHelper.FeedReaderContract.SpaceshipEntry.COLUMN_NAME_TITLE));
            shipsFromDb.add(myTitle);
        }
        cursor.close();

        //Reference to the Layout - shipList element
        shipList = (ListView) findViewById(R.id.shipList);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                shipsFromDb);
        shipList.setAdapter(arrayAdapter);
    }
}