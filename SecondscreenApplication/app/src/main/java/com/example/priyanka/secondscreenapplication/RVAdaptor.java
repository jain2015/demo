package com.example.priyanka.secondscreenapplication;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by priyanka on 11/3/16.
 */
public class RVAdaptor extends RecyclerView.Adapter<RVAdaptor.DataHolder>{


    Context context;
    ArrayList<MyData> my_data;


    RVAdaptor(ArrayList<MyData> mydata,Context context) {
        this.my_data = mydata;
        this.context = context;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_view, viewGroup, false);
        DataHolder mvh = new DataHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class DataHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView image;
        ImageView small_image;
        TextView title;
        TextView small_title;

        DataHolder(View itemView) {
            super(itemView);
//            cv = (CardView) itemView.findViewById(R.id.cv);
//            image = (ImageView) itemView.findViewById(R.id.banner);
//            small_image = (ImageView) itemView.findViewById(R.id.imageView2);
//            title = (TextView) itemView.findViewById(R.id.textView);
//            small_title = (TextView) itemView.findViewById(R.id.subtext);
        }
    }
}
