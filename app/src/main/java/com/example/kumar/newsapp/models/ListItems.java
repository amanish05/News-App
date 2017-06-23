package com.example.kumar.newsapp.models;

/**
 * Created by Kumar on 6/17/2017.
 */

public class ListItems {

    private String title;
    private String description;
    private String imageUrl;

    public ListItems(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
