package com.example.kumar.newsapp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.kumar.newsapp.database.DatabaseUtils;

/**
 * Created by Kumar on 7/28/2017.
 */


//Implementing AsyncTaskLoader to load the news
public class AsyncLoader extends AsyncTaskLoader<Void> {

    private static final String TAG = "AsyncLoader";
    private Context context;

    //To load the news from server to db
    public AsyncLoader(Context context) {
        super(context);
        this.context = context;
    }

    @Override protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
        Log.d(TAG, "$$$ onStartLoading");
    }

    @Override public Void loadInBackground() {
        DatabaseUtils.updateDatabaseFromServer(context);
        Log.d(TAG, "$$$ loadInBackground");
        return null;
    }

    @Override public void deliverResult(Void data) {
        super.deliverResult(data);
        Log.d(TAG, "$$$ deliverResult " + data);
    }
}
