package com.example.kumar.newsapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "news.db";
    private static final String TAG = "dbhelper";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryString = "CREATE TABLE " + Contract.TABLE_NEWS.TABLE_NAME + " ("+
                Contract.TABLE_NEWS.COLUMN_NAME_AUTHOR + " TEXT NOT NULL, " +
                Contract.TABLE_NEWS.COLUMN_NAME_DESCRIPTION + " TEXT NOT NULL, " +
                Contract.TABLE_NEWS.COLUMN_NAME_IMAGEURL + " TEXT NOT NULL, " +
                Contract.TABLE_NEWS.COLUMN_NAME_PUBLISHEDAT + " TEXT NOT NULL, " +
                Contract.TABLE_NEWS.COLUMN_NAME_TITLE + " TEXT NOT NULL, " +
                Contract.TABLE_NEWS.COLUMN_NAME_URL + " TEXT NOT NULL " + "); ";

        Log.d(TAG, "Create table SQL: " + queryString);
        db.execSQL(queryString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table " + Contract.TABLE_TODO.TABLE_NAME + " if exists;");
    }
}
