package com.example.kumar.newsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kumar.newsapp.ClickListener;
import com.example.kumar.newsapp.NewsClickEvent;
import com.example.kumar.newsapp.R;
import com.example.kumar.newsapp.database.MyCursor;
import com.example.kumar.newsapp.models.NewsItem;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Kumar on 6/17/2017.
 */

public class MainViewAdapter extends RecyclerView.Adapter<MainViewAdapter.ViewHolder>{

    private static final String TAG = MainViewAdapter.class.getName();
    private List<NewsItem> items;
    private Context context;
    private ClickListener listener;
    private MyCursor newsCursor;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd");

    //Implementing adapter to load news from the database

    public MainViewAdapter(MyCursor cursor, ClickListener listener) {
        this.newsCursor = cursor;
        this.listener = listener;
    }

    public void cursorSwap(MyCursor cursor) {
        this.newsCursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int pos) {
        newsCursor.moveToPosition(pos);
        holder.url = newsCursor.getUrl();
        holder.textViewTitle.setText(newsCursor.getTitle());
        holder.textViewDescription.setText(newsCursor.getDescription());

        loadImageNews(holder.imageView.getContext(), newsCursor.getImageUrl(),
                holder.imageView);

        try {
            Date publishedDate = dateFormat.parse(newsCursor.getPublishedAt());
            holder.textViewPublishedAt.setText(publishedDate.toString());
        } catch (ParseException e) {
            Log.e(TAG, "Error Parsing date: " + e.getClass().getName() + ": " + e.getMessage(), e);
            holder.textViewPublishedAt.setText(newsCursor.getPublishedAt());
        }
    }

    //Using Picasso to load the image
    private void loadImageNews(Context context, String imageU,ImageView imageV) {
        Picasso.with(context).load(imageU).into(imageV);
    }

    @Override
    public int getItemCount() {
        return newsCursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPublishedAt;
        private ImageView imageView;
        private String url;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.newsTitle);
            textViewDescription = (TextView) itemView.findViewById(R.id.description);
            textViewPublishedAt = (TextView) itemView.findViewById(R.id.publishedAt);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textViewTitle.setOnClickListener(this);
        }

        //
        @Override
        public void onClick(View view) {
            if (url != null) {
                NewsClickEvent event = new NewsClickEvent(view, url);
                listener.onNewsClick(event);
            }
        }
    }
}