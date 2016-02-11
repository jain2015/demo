package com.example.priyanka.themovieapplication;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.R.integer;
import android.widget.ListView;
import android.widget.Toast;


import com.example.priyanka.themovieapplication.model.Movie;
import com.example.priyanka.themovieapplication.service.MovieService;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Adapter mAdapter = null;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FetchMovieList fetch_movie_list = new FetchMovieList();
        fetch_movie_list.execute();

        ArrayList<Movie> movies = new ArrayList<>();
        GridView gv = (GridView) findViewById(R.id.gridView);
        mAdapter = new Adapter(this,R.layout.grid_item,movies);
        gv.setAdapter(mAdapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Image" + position, Toast.LENGTH_SHORT).show();

            }
        });
        //     Set Data to Gridview
//        GridView gv = (GridView) findViewById(R.id.gridView);
//        ArrayAdapter<String> adapter = (ArrayAdapter) new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, poster_image);
//        gv.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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

    public class FetchMovieList extends AsyncTask<Void, Void, ArrayList<Movie>> {


        @Override
        protected ArrayList<Movie> doInBackground(Void... params) {

            MovieService movie_service = new MovieService();
            return movie_service.getMovieData();

        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            if (movies != null) {
                mAdapter.clear();
                mAdapter.addAll(movies);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}



