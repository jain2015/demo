package com.example.priyanka.recycleviewapplication;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by priyanka on 7/3/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyDataViewHolder>{


    ArrayList<MyData> my_data;

    RVAdapter(ArrayList<MyData> mydata){
        this.my_data = mydata;
    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    @Override
    public MyDataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_view, viewGroup, false);
        MyDataViewHolder mvh = new MyDataViewHolder(v);
        return mvh;
    }
    @Override
    public void onBindViewHolder(MyDataViewHolder mydataviewholder, int i) {
        mydataviewholder.image.setImageResource(my_data.get(i).image);
        mydataviewholder.small_image.setImageResource(my_data.get(i).small_image);
        mydataviewholder.title.setText(my_data.get(i).title);
        mydataviewholder.small_title.setText(my_data.get(i).small_text);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class MyDataViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView image;
        ImageView small_image;
        TextView title;
        TextView small_title;

        MyDataViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            image=(ImageView) itemView.findViewById(R.id.banner);
            small_image=(ImageView) itemView.findViewById(R.id.imageView2);
            title=(TextView) itemView.findViewById(R.id.textView);
            small_title=(TextView) itemView.findViewById(R.id.subtext);
        }
    }


}
