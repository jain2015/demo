package com.example.priyanka.themovieapplication;

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
import android.widget.ListView;
import android.widget.TextView;

import com.example.priyanka.themovieapplication.model.Movie;
import com.example.priyanka.themovieapplication.model.Review;
import com.example.priyanka.themovieapplication.model.Video;
import com.example.priyanka.themovieapplication.service.MovieService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {
    ImageView mImageView = null;
    TextView mNameView = null;
    TextView mRatingView = null;
    TextView mReleaseDate = null;
    TextView mOverview = null;

    public VideoAdapter mVideoAdapter = null;
    public ReviewAdapter mReviewAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        FetchMovieDetail fetch_movie_detail = new FetchMovieDetail();

        String id = getIntent().getStringExtra("id");

        mImageView = (ImageView) findViewById(R.id.detailImageView);
        mNameView = (TextView) findViewById(R.id.detailTitle);
        mRatingView = (TextView) findViewById(R.id.detailRating);
        mReleaseDate = (TextView) findViewById(R.id.detailReleaseDate);
        mOverview = (TextView) findViewById(R.id.detailOverview);


        final ArrayList<Video> videos = new ArrayList<>();
        final ListView lv = (ListView) findViewById(R.id.detailVideolistView);
        mVideoAdapter = new VideoAdapter(this, R.layout.video_item, videos);
        lv.setAdapter(mVideoAdapter);

        final ArrayList<Review> reviews = new ArrayList<>();
        final ListView review_list= (ListView) findViewById(R.id.detailReviewListView);
        mReviewAdapter = new ReviewAdapter(this, R.layout.review_item, reviews);
        review_list.setAdapter(mReviewAdapter);

        fetch_movie_detail.execute(id);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
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

    public class FetchMovieDetail extends AsyncTask<String, Void, MovieVideoReviews> {


        @Override
        protected MovieVideoReviews doInBackground(String... params) {
            String movie_id = params[0];
            ArrayList<Video> videos = new ArrayList<>();
            ArrayList<Review> reviews = new ArrayList<>();

            MovieService MovieService = new MovieService();
            MovieVideoReviews movie_video_reviews=new MovieVideoReviews();

            Movie mMovie = MovieService.getMovieDetail(movie_id);
            videos = MovieService.getVideos(movie_id);
            reviews = MovieService.getReviews(movie_id);


            movie_video_reviews.setMovie(mMovie);
            movie_video_reviews.setVideos(videos);
            movie_video_reviews.setReviews(reviews);

             return  movie_video_reviews;
        }


        @Override
        protected void onPostExecute(MovieVideoReviews movie_video_reviews) {
            ArrayList<Video> mVideos = new ArrayList<>();
            ArrayList<Review> mReviews = new ArrayList<>();
            Movie movies=movie_video_reviews.getMovie();
            mVideos = movie_video_reviews.getVideos();
            mReviews= movie_video_reviews.getReviews();
            if (movies != null) {
                mNameView.setText(movies.getName());
                mRatingView.setText(movies.getRating());
                mReleaseDate.setText(movies.getReleaseDate());
                mOverview.setText(movies.getOverview());
                Picasso.with(getBaseContext()).load(movies.getPosterimage()).into(mImageView);
            }
            if(mVideos!=null){
                mVideoAdapter.clear();
                mVideoAdapter.addAll(mVideos);
                mVideoAdapter.notifyDataSetChanged();
            }
            if(mReviews!=null){
                mReviewAdapter.clear();
                mReviewAdapter.addAll(mReviews);
                mReviewAdapter.notifyDataSetChanged();
            }
        }
    }

    public static class MovieVideoReviews {
        public Movie movie = new Movie();
        public ArrayList<Video> videos = new ArrayList<>();
        public ArrayList<Review> reviews = new ArrayList<>();

        public Movie getMovie() {
            return movie;
        }

        public void setMovie(Movie movie) {
            this.movie = movie;
        }

        public ArrayList<Video> getVideos() {
            return videos;
        }

        public void setVideos(ArrayList<Video> videos) {
            this.videos = videos;
        }

        public ArrayList<Review> getReviews() {
            return reviews;
        }

        public void setReviews(ArrayList<Review> reviews) {
            this.reviews = reviews;
        }
    }
}
