package com.example.priyanka.recycleviewapplication;

import java.util.ArrayList;

/**
 * Created by priyanka on 4/3/16.
 */
public class MyData {

    public int image;
    public int small_image;
    public String title;
    public String small_text;

    public MyData(int image, String title, String small_text, int small_image) {
        this.image = image;
        this.title = title;
        this.small_image = small_image;
        this.small_text = small_text;
    }
}

