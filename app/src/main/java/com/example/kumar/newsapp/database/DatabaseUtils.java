package com.example.kumar.newsapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.kumar.newsapp.NetworkUtils;
import com.example.kumar.newsapp.models.NewsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

/**
 * Created by Kumar on 7/18/17.
 */

public class DatabaseUtils {

    private static final String TAG = "DatabaseUtils";

    //To read all records from the Database
    public static MyCursor getAll(SQLiteDatabase db) {
        Cursor cursor = db.query(Contract.TABLE_NEWS.TABLE_NAME, null, null,
                null, null, null,
                Contract.TABLE_NEWS.COLUMN_NAME_PUBLISHEDAT + " DESC");
        return new MyCursor(cursor);
    }

    //To update the data in the database
    public static void updateNews(SQLiteDatabase db, NewsItem[] newsItems) {
        db.beginTransaction();
        deleteAllNews(db);
        try {
            for (NewsItem newsItem : newsItems) {
                ContentValues cv = new ContentValues();
                cv.put(Contract.TABLE_NEWS.COLUMN_NAME_TITLE,
                        newsItem.getTitle());
                cv.put(Contract.TABLE_NEWS.COLUMN_NAME_DESCRIPTION,
                        newsItem.getDescription());
                cv.put(Contract.TABLE_NEWS.COLUMN_NAME_PUBLISHEDAT,
                        newsItem.getPublishedAt());
                cv.put(Contract.TABLE_NEWS.COLUMN_NAME_IMAGEURL,
                        newsItem.getImageUrl());
                cv.put(Contract.TABLE_NEWS.COLUMN_NAME_URL,
                        newsItem.getUrl());
                db.insert(Contract.TABLE_NEWS.TABLE_NAME, null, cv);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    private static void deleteAllNews(SQLiteDatabase db) {
        db.delete(Contract.TABLE_NEWS.TABLE_NAME, null, null);
    }

    public static void updateDatabaseFromServer(Context context) {

        String searchQuery = "the-next-web";
        try {
            URL newsURL = NetworkUtils.buildURL(searchQuery);
            Log.e(TAG, "Update db from server ");
            String jsonResponse = NetworkUtils.getResponseFromHttpUrl(newsURL);
            if (jsonResponse != null && !jsonResponse.isEmpty()) {
                NewsItem[] newsItems = getNewsItems(jsonResponse);
                SQLiteDatabase db = new DBHelper(context)
                        .getWritableDatabase();
                updateNews(db, newsItems);
            }
            Log.e(TAG, "Update db from server");
        } catch (Exception e) {
            Log.e(TAG, "Error reading new API: " + e.getClass().getName() +
                    ": " + e.getMessage(), e);
            Toast.makeText(context,
                    "Error reading new API: " + e.getClass().getName() +
                            ": " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static NewsItem[] getNewsItems(String jsonResponse) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray newsItemJSONArray = jsonObject.getJSONArray("articles");
            NewsItem[] newsItems = null;
            if (newsItemJSONArray != null) {
                newsItems = new NewsItem[newsItemJSONArray.length()];
                for (int i = 0; i < newsItemJSONArray.length(); i++) {
                    JSONObject o = newsItemJSONArray.getJSONObject(
                            i);
                    newsItems[i] = new NewsItem(o.getString("title"),
                            o.getString("description"),
                            o.getString("urlToImage"),
                            o.getString("url"),
                            o.getString("publishedAt"));
                }
            }
            return newsItems;
        } catch (JSONException e) {
            Log.e(TAG, "Error convert NewsJSON to NewsItem objects", e);
            throw new RuntimeException("Error getting NewsJSON from news API", e);
        }
    }

}
