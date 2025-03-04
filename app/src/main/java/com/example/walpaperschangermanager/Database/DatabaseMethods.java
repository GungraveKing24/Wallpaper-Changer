package com.example.walpaperschangermanager.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.walpaperschangermanager.models.Folder;

import java.util.ArrayList;

public class DatabaseMethods {

    private DataBaseHelper database;

    public DatabaseMethods(Context context) {
        this.database = new DataBaseHelper(context);
    }

    public boolean CreateFolder(String nombre_folder, String imagePath) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("folder_name", nombre_folder);
        values.put("image_path", imagePath);

        long results = db.insert("folders", null, values);
        return results != -1;
    }

    public ArrayList<Folder> getAllFolders() {
        SQLiteDatabase db = database.getReadableDatabase();
        ArrayList<Folder> folderList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM folders", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("folder_name"));
                String imagePath = cursor.getString(cursor.getColumnIndexOrThrow("image_path"));

                folderList.add(new Folder(id, name, imagePath));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return folderList;
    }
}
