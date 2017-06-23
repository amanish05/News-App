package com.example.kumar.newsapp;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Kumar on 6/14/2017.
 */

public class NetworkUtils {

    public static final String BASE_URL = "https://newsapi.org/v1/articles";
    public static final String SOURCE_PARAM_QUERY = "source";
    public static final String SORTBY_PARAM_QUERY = "sortBy";
    public static final String APIKEY_PARAM_QUERY = "apiKey";

    public static URL buildURL(String searchQuery){
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(SOURCE_PARAM_QUERY, searchQuery)
                .appendQueryParameter(SORTBY_PARAM_QUERY, "latest")
                .appendQueryParameter(APIKEY_PARAM_QUERY, "5cba50b51e8e422aaeb6696c7a547512").build();
        URL url = null;

        try {
            url = new URL(uri.toString());
            Log.d("NetworkUtils::buildURL", "Url: " +url);
        }catch(Exception e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
