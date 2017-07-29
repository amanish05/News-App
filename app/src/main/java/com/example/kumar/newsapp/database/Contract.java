package com.example.kumar.newsapp.database;

import android.provider.BaseColumns;

//Defining the structure to store the data
public class Contract {
    public static class TABLE_NEWS implements BaseColumns{
        public static final String TABLE_NAME = "News";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_IMAGEURL = "imageUrl";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_PUBLISHEDAT = "publishedAt";
    }
}