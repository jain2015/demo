package com.example.priyanka.themovieapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.example.priyanka.themovieapplication.model.Movie;
import com.squareup.picasso.Picasso;
import java.util.List;

public class Adapter extends ArrayAdapter<Movie> {

    private Context mContext;
    private int gridItemLayout;

    public Adapter(Context c, int resource, List<Movie> objects) {
        super(c, resource, objects);
        mContext = c;
        gridItemLayout = resource;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(gridItemLayout, parent, false);

            holder = new ViewHolder();
            holder.image = (ImageView) row.findViewById(R.id.imageView);

            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        Movie movie = getItem(position);
        Picasso.with(mContext).load(movie.getPosterimage()).into(holder.image);

        return row;
    }

    static class ViewHolder {
        ImageView image;
    }




}
