package com.example.kumar.newsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kumar.newsapp.R;
import com.example.kumar.newsapp.models.ListItems;

import java.util.List;

/**
 * Created by Kumar on 6/17/2017.
 */

public class MainViewAdapter extends RecyclerView.Adapter<MainViewAdapter.ViewHolder>{

    private List<ListItems> items;
    private Context context;

    public MainViewAdapter(List<ListItems> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItems item = items.get(position);
        holder.textViewTitle.setText(item.getTitle());
        holder.textViewDescription.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitle;
        private TextView textViewDescription;

        public ViewHolder(View itemView) {

            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.newsTitle);
            textViewDescription = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
