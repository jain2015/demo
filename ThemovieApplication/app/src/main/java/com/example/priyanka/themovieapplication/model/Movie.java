package com.example.priyanka.themovieapplication.model;

import android.widget.BaseAdapter;

public class Movie {

    private String posterimage;
    private String name;


    public String getPosterimage() {
        return posterimage;
    }

    public void setPosterimage(String poster_image) {
        this.posterimage = poster_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}