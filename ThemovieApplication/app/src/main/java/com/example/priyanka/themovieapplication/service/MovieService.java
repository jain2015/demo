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
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by priyanka on 10/2/16.
 */
public class MovieService {

    private final String LOG_TAG = MovieService.class.getSimpleName();

    public ArrayList<Movie> getMovieData()
    {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        ArrayList<Movie> movies = new ArrayList<>();
        String sort_by = "popularity.desc";
        String appid = "590e44278fc96621798bcff64fd36990";


        try {
            final String IMAGE_BASE_URL="http://image.tmdb.org/t/p/w185";
            final String FORECAST_BASE_URL = "http://api.themoviedb.org/3";
            final String SORTBY_PARAM = "sort_by";
            final String APPID = "api_key";

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendEncodedPath("discover/movie")
                    .appendQueryParameter(SORTBY_PARAM, sort_by )
                    .appendQueryParameter(APPID, appid)
                    .build();
            URL url = new URL(builtUri.toString());
            Log.v(LOG_TAG, "Built Uri" + builtUri.toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return movies;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            String  data = buffer.toString();
            Log.e(LOG_TAG, data);

            if(data.contains("status_message")){
                return null;
            }

            final String OWM_RESULTS = "results";
            final String OWM_POSTERPATH = "poster_path";
            final String OWM_TITLE = "title";
            JSONObject forecastJson = new JSONObject(data);
            JSONArray resultArray = forecastJson.getJSONArray(OWM_RESULTS);

            for (int i = 0; i < resultArray.length(); i++) {

                // Get the JSON object representing the day
                JSONObject MovieObject = resultArray.getJSONObject(i);

                String poster_path;
                String title;
                String poster_path_image_url;

                poster_path = MovieObject.getString(OWM_POSTERPATH);
                poster_path_image_url = IMAGE_BASE_URL+poster_path;
                title = MovieObject.getString(OWM_TITLE);

                Movie movie = new Movie();
                movie.setPosterimage(poster_path_image_url);
                movie.setName(title);

                movies.add(movie);
            }

            return movies;

        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }  catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }finally {
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
