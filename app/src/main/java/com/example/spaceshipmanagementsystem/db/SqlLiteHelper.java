package com.example.spaceshipmanagementsystem.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Space;

public class SqlLiteHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SpaceshipEntry.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.SpaceshipEntry.TABLE_NAME + " (" +
                    FeedReaderContract.SpaceshipEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.SpaceshipEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FeedReaderContract.SpaceshipEntry.COLUMN_NAME_MASS + " TEXT," +
                    FeedReaderContract.SpaceshipEntry.COLUMN_NAME_COLOR + " TEXT," +
                    FeedReaderContract.SpaceshipEntry.COLUMN_NAME_LOCATION + " TEXT," +
                    FeedReaderContract.SpaceshipEntry.COLUMN_NAME_IMAGE_URL + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.SpaceshipEntry.TABLE_NAME;



    public SqlLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public static final class FeedReaderContract {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private FeedReaderContract() {}

        /* Inner class that defines the table contents */
        public static class SpaceshipEntry implements BaseColumns {
            public static final String TABLE_NAME = "spaceship";
            public static final String COLUMN_NAME_TITLE = "title";
            public static final String COLUMN_NAME_MASS = "mass";
            public static final String COLUMN_NAME_COLOR = "color";
            public static final String COLUMN_NAME_LOCATION = "location";
            public static final String COLUMN_NAME_IMAGE_URL = "image";
        }
    }
}
