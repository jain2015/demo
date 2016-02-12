package com.example.priyanka.themovieapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.priyanka.themovieapplication.model.Movie;
import com.example.priyanka.themovieapplication.service.MovieService;
import com.squareup.picasso.Picasso;



public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String movie_id=null;

        Intent intent = this.getIntent();

        if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT)){
            movie_id= intent.getStringExtra(intent.EXTRA_TEXT);
        }

        MovieService MovieService=new MovieService();
        Movie mMovie=MovieService.getMovieDetail(movie_id);


        ImageView mImageView = (ImageView) findViewById(R.id.detailImageView);
        TextView mNameView = (TextView) findViewById(R.id.detailTitle);
//        mOverviewView = (TextView) view.findViewById(R.id.detail_overview);
//        mReleaseDateView = (TextView) view.findViewById(R.id.detail_release_date);
//        mRatingView = (TextView) view.findViewById(R.id.detail_rating);
//        mFavourite = (ImageButton) view.findViewById(R.id.favourite_button);

//        mTrailersView = (TextView) view.findViewById(R.id.detail_trailers);
//        mVideoListView = (ListView) view.findViewById(R.id.video_list_view);

//        mReviewView = (TextView) view.findViewById(R.id.detail_reviews);
//        mReviewListView = (ListView) view.findViewById(R.id.review_list_view);


        mNameView.setText(mMovie.getName());
        Picasso.with(this).load(mMovie.getPosterimage()).into(mImageView);

//        mOverviewView.setText(mMovie.overview);
//        mReleaseDateView.setText(getString(R.string.label_relase) + mMovie.releaseDate);
//        mRatingView.setText(getString(R.string.label_rating) + mMovie.rating);


//        if(!isTaskRunning && mMovie.id > 0)
//            new FetchVideoTask(this).execute(String.valueOf(mMovie.id));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
