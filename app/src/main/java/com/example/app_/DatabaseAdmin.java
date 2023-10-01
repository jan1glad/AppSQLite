package com.example.app_;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseAdmin extends SQLiteOpenHelper {
    public static final String ADMIN_TABLE = "ADMIN_TABLE";
    public static final String COLUMN_ADMIN_ID = "COLUMN_ADMIN_ID";
    public static final String COLUMN_ADMIN_NAME = "COLUMN_ADMIN_NAME";
    public static final String COLUMN_ADMIN_PASSWORD = "COLUMN_ADMIN_PASSWORD";


    public DatabaseAdmin(@Nullable Context context) {
        super(context, "admin.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + ADMIN_TABLE + " (" + COLUMN_ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ADMIN_NAME + " TEXT, " + COLUMN_ADMIN_PASSWORD + " TEXT)";


        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }



    public boolean addOne(AdminModel adminModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        String name = adminModel.getName();
        String password = adminModel.getPassword();

        cv.put(COLUMN_ADMIN_NAME, name);
        cv.put(COLUMN_ADMIN_PASSWORD, password);

        long insert = db.insert(ADMIN_TABLE, null, cv);

        if (insert == -1) {
            return false;
        } else return true;

    }

    public Boolean check(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + ADMIN_TABLE + " WHERE " + COLUMN_ADMIN_NAME + " = ? AND " + COLUMN_ADMIN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{name, password});

        if (cursor.moveToFirst()) {
            // Match found in the database
            cursor.close();
            return true;
        } else {
            // No match found
            cursor.close();
            return false;
        }
    }


    public Boolean checkPassword(String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select " + COLUMN_ADMIN_PASSWORD + " FROM " + ADMIN_TABLE + " WHERE " + COLUMN_ADMIN_PASSWORD+ " = ?", new String [] {COLUMN_ADMIN_PASSWORD});

        if (cursor.getCount()>0) {
            return true;
        } else return false;
    }
}
