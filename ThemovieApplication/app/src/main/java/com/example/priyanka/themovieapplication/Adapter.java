package com.example.priyanka.themovieapplication;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.example.priyanka.themovieapplication.model.Movie;
import com.squareup.picasso.Picasso;
import java.util.List;

public class Adapter extends ArrayAdapter<Movie> {

    public Adapter(Context context, int resource, List<Movie> objects) {
        super(context, resource, objects);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView img = new ImageView(getContext());

        Movie movie = getItem(position);

        Picasso.with(getContext()).load(movie.getPosterimage()).into(img);
//        img.setScaleType(ImageView.ScaleType.CENTER);
//        img.setLayoutParams(new GridView.LayoutParams(700,700));

        return img;
    }
}
