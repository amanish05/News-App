package com.example.kumar.newsapp;

import java.util.EventListener;

/**
 * Created by Kumar on 6/24/2017.
 */

//Listener that is implemented in the main activity
public interface ClickListener extends EventListener {

   public abstract void onNewsClick(NewsClickEvent event);
}
