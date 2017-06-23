package com.example.kumar.newsapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.kumar.newsapp.adapters.MainViewAdapter;
import com.example.kumar.newsapp.models.ListItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.support.v4.view.MenuItemCompat.getActionView;

public class MainActivity extends AppCompatActivity {

   private ProgressBar mLoadingIndicator;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItems> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        makeApiQuery("the-next-web");
    }

    private void showJsonDataView() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void makeApiQuery(String searchQuery) {
        URL apiUrl = NetworkUtils.buildURL(searchQuery);
        new NewsAppQueryTask().execute(apiUrl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSubmitButtonEnabled(true);

        Log.d("onCOMenu:searchView", searchView.toString());
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                makeApiQuery(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               return false;
            }
        });
        return true;
    }



    public class NewsAppQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String apiResults = null;
            try {
                apiResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return apiResults;
        }

        @Override
        protected void onPostExecute(String apiResults) {

            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (apiResults != null && !apiResults.equals("")) {
                showJsonDataView();
                try {
                    JSONObject obj = new JSONObject(apiResults);
                    JSONArray list =  obj.getJSONArray("articles");

                    items = new ArrayList<>(list.length());
                    for(int i=0; i< list.length(); i++){
                        JSONObject o = list.getJSONObject(i);
                        ListItems item = new ListItems(o.getString("title"), o.getString("description"), o.getString("urlToImage"));
                        items.add(item);

                        Log.d("onPostExecute :: Title", o.getString("title"));
                    }
                    Log.d("onPostExecute", String.valueOf(items.size()));

                    adapter = new MainViewAdapter(items, getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}