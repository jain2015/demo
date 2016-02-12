package com.example.priyanka.themovieapplication.model;

import android.widget.BaseAdapter;

public class Movie {

    private String posterimage;
    private String name;
    private int id;
    private String overview;
    private String releaseDate;
    private String rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

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