package com.example.app_.MenuUserOptions;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app_.AdminModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatabaseClients extends SQLiteOpenHelper {

    public static final String CLIENTS_TABLE = "CLIENTS_TABLE";
    public static final String COLUMN_CLIENTS_ID = "COLUMN_CLIENTS_ID";
    public static final String COLUMN_CLIENTS_NAME = "COLUMN_CLIENTS_NAME";
    public static final String COLUMN_CLIENTS_SURNAME = "COLUMN_CLIENTS_SURNAME";
    public static final String COLUMN_CLIENTS_CITY = "COLUMN_CLIENTS_CITY";
    public static final String COLUMN_CLIENTS_DEPARTMENT = "COLUMN_CLIENTS_DEPARTMENT";
    public static final String COLUMN_CLIENTS_SALARY = "COLUMN_CLIENTS_SALARY";
    public DatabaseClients(@Nullable Context context) {
        super(context, "clients.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + CLIENTS_TABLE + " (" + COLUMN_CLIENTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_CLIENTS_NAME + " TEXT, " + COLUMN_CLIENTS_SURNAME + " TEXT, " + COLUMN_CLIENTS_CITY + " TEXT, " +
                COLUMN_CLIENTS_DEPARTMENT + " TEXT, " + COLUMN_CLIENTS_SALARY + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addClients(ClientsModel clientsModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        String name = clientsModel.getName();
        String surname = clientsModel.getSurname();
        String city  = clientsModel.getCity();
        String department = clientsModel.getDepartment();
        int salary = clientsModel.getSalary();

        cv.put(COLUMN_CLIENTS_NAME, name);
        cv.put(COLUMN_CLIENTS_SURNAME, surname);
        cv.put(COLUMN_CLIENTS_CITY,city);
        cv.put(COLUMN_CLIENTS_DEPARTMENT,department);
        cv.put(COLUMN_CLIENTS_SALARY,salary);

        long insert = db.insert(CLIENTS_TABLE, null, cv);

        if (insert == -1) {
            return false;
        } else return true;

    }

    public List<ClientsModel> getEveryone(){
        List<ClientsModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CLIENTS_TABLE;

        return getClientsModels(returnList, queryString);
    }
    @org.jetbrains.annotations.Nullable
    private static String getString(String orderByColumn) {

        if (Objects.equals(orderByColumn, "ID")){
            orderByColumn = COLUMN_CLIENTS_ID;
        }
        if (Objects.equals(orderByColumn, "Name")){
            orderByColumn = COLUMN_CLIENTS_NAME;
        }
        if (Objects.equals(orderByColumn, "Surname")){
            orderByColumn = COLUMN_CLIENTS_SURNAME;
        }
        if (Objects.equals(orderByColumn, "City")){
            orderByColumn = COLUMN_CLIENTS_CITY;
        }
        if (Objects.equals(orderByColumn, "Department")){
            orderByColumn = COLUMN_CLIENTS_DEPARTMENT;
        }
        if (Objects.equals(orderByColumn, "Salary")){
            orderByColumn = COLUMN_CLIENTS_SALARY;
        }
        return orderByColumn;
    }
    public List<ClientsModel> groupBy(String orderByColumn){

        orderByColumn = getString(orderByColumn);

        List<ClientsModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CLIENTS_TABLE + " GROUP BY "+ orderByColumn;

        return getClientsModels(returnList, queryString);
    }



    public List<ClientsModel> orderBy(String orderByColumn, String option){

        orderByColumn = getString(orderByColumn);

        List<ClientsModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CLIENTS_TABLE + " ORDER BY "+ orderByColumn + " " + option;

        return getClientsModels(returnList, queryString);
    }

    private List<ClientsModel> getClientsModels(List<ClientsModel> returnList, String queryString) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){

            do {
                int clientID = cursor.getInt(0);
                String clientName = cursor.getString(1);
                String clientSurname = cursor.getString(2);
                String clientCity = cursor.getString(3);
                String clientDepartment = cursor.getString(4);
                int clientSalary = cursor.getInt(5);

                ClientsModel clientsModel = new ClientsModel(clientID, clientName, clientSurname, clientCity,clientDepartment,clientSalary);

                returnList.add(clientsModel);

            }while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return returnList;
    }

    public boolean deleteOne(String column, String text) {
        SQLiteDatabase db = this.getWritableDatabase();

        String queryString;

        if (column.equals("ID")) {

            queryString = "DELETE FROM " + CLIENTS_TABLE + " WHERE " + COLUMN_CLIENTS_ID + " = " + text;
        } else {

            column = getString(column);
            queryString = "DELETE FROM " + CLIENTS_TABLE + " WHERE " + column + " = '" + text + "'";
        }

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    @SuppressLint("Range")
    public int updateValues(String currentColumn, String currently, String spinnerUpdate, String update) {

        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = null;



        if (spinnerUpdate.equals("ID") && currentColumn.equals("ID")) {
            queryString = "UPDATE " + CLIENTS_TABLE +
                    " SET " + COLUMN_CLIENTS_ID + " = '" + update +
                    "' WHERE " + COLUMN_CLIENTS_ID + " = '" + currently + "'";
        }else
        {
            currentColumn = getString(currentColumn);
            spinnerUpdate = getString(spinnerUpdate);
            queryString = "UPDATE " + CLIENTS_TABLE +
                    " SET " + spinnerUpdate + " = '" + update +
                    "' WHERE " + currentColumn + " = '" + currently + "'";
        }

        db.execSQL(queryString);


        Cursor cursor = db.rawQuery("SELECT changes() AS affected_rows", null);
        int affectedRows = 0;

        if (cursor.moveToFirst()) {
            affectedRows = cursor.getInt(cursor.getColumnIndex("affected_rows"));
        }

        cursor.close();
        db.close();

        return affectedRows;


        /*if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }*/
    }

}
