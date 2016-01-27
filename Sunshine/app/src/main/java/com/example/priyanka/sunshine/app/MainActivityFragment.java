package com.example.priyanka.sunshine.app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        String[] forecastArray = {
                "Today - Sunny - 88/66",
                "Tommorow - Foggy - 70/40   ",
                "Weds - Hravy Rain - 88/66",
                "Thurs   - Sunny - 88/66",
                "Fri - Asteroids - 88/66",
                "Sat - Cloudy - 88/66",
                "Sun - Sunny - 88/66",
                "Sun - Cloudy - 88/66",
                "Sun - Rainy - 88/66",
                "Sun - Sunny - 88/66",
                "Sun - Sunny - 88/66",

        };

        List<String> weekForecast = new ArrayList<String>(Arrays.asList(forecastArray));
        ArrayAdapter<String> mForecastAdaptor = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.list_item_forecast_textview, weekForecast);
        ListView listView=(ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdaptor);


        return rootView;
    }
}
