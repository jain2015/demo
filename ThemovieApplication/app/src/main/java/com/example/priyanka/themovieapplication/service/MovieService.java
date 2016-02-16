package com.example.priyanka.themovieapplication.service;

import android.net.Uri;
import android.util.Log;

import com.example.priyanka.themovieapplication.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by priyanka on 10/2/16.
 */
public class MovieService {

    private final String LOG_TAG = MovieService.class.getSimpleName();

    final String RESULTS = "results";
    final String POSTER_PATH = "poster_path";
    final String ID = "id";
    final String ORIGINAL_TITLE = "original_title";
    final String OVERVIEW = "overview";
    final String RELEASE_DATE = "release_date";
    final String USER_VOTE = "vote_average";

    final String KEY = "key";
    final String NAME = "name";
    final String SITE = "site";

    final String AUTHOR = "author";
    final String CONTENT = "content";

    final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185";

    final String SORTBY_PARAM = "sort_by";
    final String APPID = "api_key";
    final String appid = "590e44278fc96621798bcff64fd36990";


    public ArrayList<Movie> getMovieData() {
        final String MOVIE_BASE_URL = "http://api.themoviedb.org/3";
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        ArrayList<Movie> movies = new ArrayList<>();
        String sort_by = "popularity.desc";
        try {

            Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                    .appendEncodedPath("discover/movie")
                    .appendQueryParameter(SORTBY_PARAM, sort_by)
                    .appendQueryParameter(APPID, appid)
                    .build();
            URL url = new URL(builtUri.toString());
            Log.v(LOG_TAG, "Built Uri" + builtUri.toString());
            String data = getResponse(url.toString());

            if (data.contains("status_message")) {
                return null;
            }


            JSONObject forecastJson = new JSONObject(data);
            JSONArray resultArray = forecastJson.getJSONArray(RESULTS);

            for (int i = 0; i < resultArray.length(); i++) {

                // Get the JSON object representing the day
                JSONObject MovieObject = resultArray.getJSONObject(i);

                String poster_path;
                String title;
                String poster_path_image_url;
                String id;

                poster_path = MovieObject.getString(POSTER_PATH);
                id = MovieObject.getString(ID);
                poster_path_image_url = IMAGE_BASE_URL + poster_path;
                title = MovieObject.getString(ORIGINAL_TITLE);

                Movie movie = new Movie();
                movie.setPosterimage(poster_path_image_url);
                movie.setName(title);
                movie.setId(id);
                movies.add(movie);
            }
            return movies;

        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return null;
    }

    public Movie getMovieDetail(String id) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        final String MOVIE_BASE_URL = "http://api.themoviedb.org/3";
        try {

            Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                    .appendEncodedPath("movie")
                    .appendEncodedPath(id)
                    .appendQueryParameter(APPID, appid)
                    .build();

            URL url = new URL(builtUri.toString());

            Log.v(LOG_TAG, "Built Uri" + builtUri.toString());

            String data = getResponse(url.toString());

            Movie movie = getMovieFromJson(data);

            return movie;
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        return null;

    }

    public Movie getMovieFromJson(String movieJsonString) throws JSONException {

        JSONObject movieObject = new JSONObject(movieJsonString);

        String poster_path_image_url = IMAGE_BASE_URL + movieObject.getString(POSTER_PATH);
        String name = movieObject.getString(ORIGINAL_TITLE);
        String overview = movieObject.getString(OVERVIEW);
        String releaseDate = movieObject.getString(RELEASE_DATE);
        String rating = movieObject.getString(USER_VOTE);

        Movie movie = new Movie();
        movie.setPosterimage(poster_path_image_url);
        movie.setName(name);
        movie.setOverview(overview);
        movie.setRating(rating);
        movie.setReleaseDate(releaseDate);

        return movie;
    }


    private String getResponse(String endPoint) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(endPoint);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            return buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return null;
    }

}
