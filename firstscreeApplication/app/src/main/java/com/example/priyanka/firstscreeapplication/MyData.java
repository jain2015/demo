package com.example.priyanka.firstscreeapplication;

/**
 * Created by priyanka on 4/3/16.
 */
public class MyData {

    private int image;
    private String title;
    private int small_image;
    private String small_text;

    public MyData(int image, String title, int small_image, String small_text) {
        this.image = image;
        this.title = title;
        this.small_image = small_image;
        this.small_text = small_text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSmall_image() {
        return small_image;
    }

    public void setSmall_image(int small_image) {
        this.small_image = small_image;
    }

    public String getSmall_text() {
        return small_text;
    }

    public void setSmall_text(String small_text) {
        this.small_text = small_text;
    }
}
