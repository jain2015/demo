package com.example.priyanka.themovieapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.priyanka.themovieapplication.model.Movie;
import com.example.priyanka.themovieapplication.service.MovieService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {
    ImageView mImageView = null;
    TextView mNameView = null;
    TextView mRatingView = null;
    TextView mReleaseDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        FetchMovieDetail fetch_movie_detail = new FetchMovieDetail();

        String id = getIntent().getStringExtra("id");

        mImageView = (ImageView) findViewById(R.id.detailImageView);
        mNameView = (TextView) findViewById(R.id.detailTitle);
        mRatingView=(TextView) findViewById(R.id.detailRating);
        mReleaseDate=(TextView) findViewById(R.id.detailReleaseDate);
        fetch_movie_detail.execute(id);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class FetchMovieDetail extends AsyncTask<String, Void, Movie> {


        @Override
        protected Movie doInBackground(String... params) {
            String movie_id = params[0];
            MovieService MovieService = new MovieService();
            Movie mMovie = MovieService.getMovieDetail(movie_id);
            return mMovie;
        }


        @Override
        protected void onPostExecute(Movie movies) {
            if (movies != null) {
                mNameView.setText(movies.getName());
                mRatingView.setText(movies.getRating());
                mReleaseDate.setText(movies.getReleaseDate());
                Picasso.with(getBaseContext()).load(movies.getPosterimage()).into(mImageView);
            }
        }
    }
}
