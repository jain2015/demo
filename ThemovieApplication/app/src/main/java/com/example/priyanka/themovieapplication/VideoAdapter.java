package com.example.priyanka.themovieapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.priyanka.themovieapplication.model.Movie;
import com.example.priyanka.themovieapplication.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by priyanka on 17/2/16.
 */
public class VideoAdapter extends ArrayAdapter<Video>{

    private Context mContext;
    private int ListItemLayout;

    public VideoAdapter(Context c, int itemLayout, List<Video>objects) {
        super(c, itemLayout, objects);
        mContext = c;
        ListItemLayout = itemLayout;
    }
    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((DetailActivity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.video_item, parent, false);

            holder = new ViewHolder();
            holder.image = (ImageView) row.findViewById(R.id.video_thumbnail);
            holder.title = (TextView) row.findViewById(R.id.detailVideoTitle);
            holder.site = (TextView) row.findViewById(R.id.video_site);

            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        Video video = getItem(position);

        Picasso.with(mContext).load(video.getThumbnail()).into(holder.image);
        holder.title.setText(video.getName());
        holder.site.setText(video.getSite());

        return row;
    }
    static class ViewHolder {
        TextView title;
        TextView site;
        ImageView image;
    }

}
