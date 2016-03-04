package com.example.priyanka.themovieapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.priyanka.themovieapplication.model.Review;
import com.example.priyanka.themovieapplication.model.Video;

import java.util.List;

/**
 * Created by priyanka on 17/2/16.
 */
public class ReviewAdapter extends ArrayAdapter<Review>{

    private Context mContext;
    private int gridItemLayout;

    public ReviewAdapter(Context c, int itemLayout, List<Review> objects) {
        super(c, itemLayout, objects);
        mContext = c;
        gridItemLayout = itemLayout;
    }

    public long getItemId(int position) {
        return 0;
    }
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        final ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((DetailActivity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.review_item, parent, false);

            holder = new ViewHolder();
            holder.author = (TextView) row.findViewById(R.id.review_author);
            holder.content = (TextView) row.findViewById(R.id.review_content);

            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        row.setClickable(false);

        Review review = getItem(position);

        holder.author.setText(review.getAuthor());
        holder.content.setText(review.getContent());

        return row;
    }

    static class ViewHolder {
        TextView author;
        TextView content;
    }
}
