package com.example.it370_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB_sqlite extends SQLiteOpenHelper {
    private Context context;
    public static final String DBname = "data.db";
    public static final String TABLE_NAME = "reg";
    public static final String COL_1 = "id";
    public static final String COL_2 ="fullname";
    public static final String COL_3 = "pn";
    public static final String COL_4 = "city";
    public DB_sqlite(@Nullable Context context) {

        super(context, DBname, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME + "(id TEXT, fullname TEXT , pn TEXT , city TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public boolean registerUser(String id,String fullname , String pn , String city){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1 , id);
        values.put(COL_2 , fullname);
        values.put(COL_3 , pn);
        values.put(COL_4 , city);

        long result = db.insert(TABLE_NAME , null , values);
        if(result == -1)
            return false;
        else
            return true;
    }
}
