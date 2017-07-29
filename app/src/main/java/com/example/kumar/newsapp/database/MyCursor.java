package com.example.kumar.newsapp.database;

import android.database.Cursor;

/**
 * Created by Kumar on 7/28/2017.
 */

//Class to Db Cursor to get records from the table
public class MyCursor {

    private Cursor cursor;
    public MyCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    public void moveToPosition(int pos) {
        cursorNull();
        cursor.moveToPosition(pos);
    }

    public String getUrl() {
        cursorNull();
        return cursor.getString(cursor.getColumnIndex(
                Contract.TABLE_NEWS.COLUMN_NAME_URL));
    }

    public String getTitle() {
        cursorNull();
        return cursor.getString(
                cursor.getColumnIndex(Contract.TABLE_NEWS.COLUMN_NAME_TITLE));
    }

    public String getDescription() {
        cursorNull();
        return cursor.getString(cursor.getColumnIndex(
                Contract.TABLE_NEWS.COLUMN_NAME_DESCRIPTION));
    }

    public String getImageUrl() {
        cursorNull();
        return cursor.getString(cursor.getColumnIndex(
                Contract.TABLE_NEWS.COLUMN_NAME_IMAGEURL));
    }

    public String getPublishedAt() {
        cursorNull();
        return cursor.getString(cursor.getColumnIndex(
                Contract.TABLE_NEWS.COLUMN_NAME_PUBLISHEDAT));
    }

    public int getCount() {
        cursorNull();
        return cursor.getCount();
    }

    //Checking for the cursor point being null. If true throwing an exception
    private void cursorNull() {
        if (cursor == null) {
            throw new RuntimeException("cursor is null @ NewsCursor");
        }
    }
}
