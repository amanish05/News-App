package com.example.kumar.newsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.kumar.newsapp.database.DBHelper;
import com.example.kumar.newsapp.database.DatabaseUtils;
import com.example.kumar.newsapp.models.NewsItem;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import java.net.URL;

import static com.example.kumar.newsapp.database.DatabaseUtils.updateDatabaseFromServer;

/**
 * Created by Kumar on 7/28/2017.
 */

public class SchedularServices extends JobService {
    private static final String TAG = "NewsLoaderService";

    //Job scheduler that updates the database with latest
    @Override
    public boolean onStartJob(JobParameters job) {
        Log.d(TAG, "$$$ Service onStartJob called");
        new AsyncTask<Void, Void, Void>() {
            @Override protected Void doInBackground(Void... params) {
                updateDatabaseFromServer(SchedularServices.this);
                return null;
            }
        }.execute();

        Toast.makeText(SchedularServices.this, "News updated from the server!", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        Log.d(TAG, "$$$ Service onStopJob called");
        return false;
    }

}
