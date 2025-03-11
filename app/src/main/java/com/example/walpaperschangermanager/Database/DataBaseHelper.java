package com.example.walpaperschangermanager.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "folders.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "folders";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "folder_name";
    private static final String COLUMN_IMAGE = "image_path";

    public DataBaseHelper(Context context) {
        super(context != null ? context.getApplicationContext() : null, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_IMAGE + " TEXT)";

        String createTableImages = "CREATE TABLE images ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "folder_id INTEGER, " +
                "image_path TEXT NOT NULL, " +
                "new_image_path TEXT," +
                "FOREIGN KEY(folder_id) REFERENCES folders(id))";

        db.execSQL(createTable);
        db.execSQL(createTableImages);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS images");
        onCreate(db);
    }
}
