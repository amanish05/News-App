package com.example.kumar.newsapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "newsTest.db";
    private static final String TAG = "dbhelper";
    //Implementation of Database SQLite to add news

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryString = "CREATE TABLE " + Contract.TABLE_NEWS.TABLE_NAME + " ("+
                Contract.TABLE_NEWS.COLUMN_NAME_AUTHOR + " TEXT, " +
                Contract.TABLE_NEWS.COLUMN_NAME_DESCRIPTION + " TEXT, " +
                Contract.TABLE_NEWS.COLUMN_NAME_IMAGEURL + " TEXT, " +
                Contract.TABLE_NEWS.COLUMN_NAME_PUBLISHEDAT + " TEXT, " +
                Contract.TABLE_NEWS.COLUMN_NAME_TITLE + " TEXT, " +
                Contract.TABLE_NEWS.COLUMN_NAME_URL + " TEXT" + "); ";

        Log.d(TAG, "Create table SQL: " + queryString);
        db.execSQL(queryString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table " + Contract.TABLE_TODO.TABLE_NAME + " if exists;");
    }
}
