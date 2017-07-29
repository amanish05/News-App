package com.example.kumar.newsapp;

import android.view.View;

/**
 * Created by Kumar on 6/24/2017.
 */
public class NewsClickEvent {
   private View view;
   private String url;

   public NewsClickEvent(View view, String url) {
      this.view = view;
      this.url = url;
   }
   public View getSource() {
      return view;
   }

   public String getUrl() {
      return url;
   }
}
