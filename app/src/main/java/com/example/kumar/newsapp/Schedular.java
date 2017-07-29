package com.example.kumar.newsapp;

import android.content.Context;
import android.util.Log;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

/**
 * Created by Kumar on 7/28/2017.
 */

public class Schedular {

    private static final String TAG = "Scheduler";
    private static final String TAG_NEWS_JOB = "SchedulerJob";

    //Firebase Dispatcher to schedule the background job for updating every minute
    public static void scheduleNewsLoad(Context context) {
        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
        Job refreshJob = dispatcher.newJobBuilder().setService(
                SchedularServices.class).setTag(TAG_NEWS_JOB).setConstraints(
                Constraint.ON_ANY_NETWORK).setLifetime(Lifetime.FOREVER)
                .setRecurring(true).setTrigger(Trigger.executionWindow(0, 60))
                .setReplaceCurrent(true).build();
        dispatcher.schedule(refreshJob);
        Log.d(TAG, "$$$ News Service to run in the background");
    }

    public static void stopScheduledNewsLoad(Context context) {
        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
        dispatcher.cancel(TAG_NEWS_JOB);
        Log.d(TAG, "$$$ News Service background: job stop");
    }
}
