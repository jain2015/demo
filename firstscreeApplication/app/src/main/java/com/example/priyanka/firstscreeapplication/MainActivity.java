package com.example.priyanka.firstscreeapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.ShareActionProvider;
import android.widget.Adapter;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Adapter adapter;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         // Initialize recycler view
         mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
         mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



//        TabHost  tabHost=(TabHost)findViewById(R.id.tabHost);
//        tabHost.setup();

        // TabHost.TabSpec tabspec=tabHost.newTabSpec("overview");
        // tabspec.setContent(R.id.tab_overview);
        //   tabspec.setIndicator("OVERVIEW");
        //    tabHost.addTab(tabspec);


        //    tabspec = tabHost.newTabSpec("price_and_offers");
        //tabspec.setContent(R.id.tab_price);
        //      tabspec.setIndicator("PRICE AND OFFERS");
        // tabHost.addTab(tabspec);

        //    tabspec = tabHost.newTabSpec("variants");
        //  tabspec.setContent(R.id.tab_variants);
        //     tabspec.setIndicator("VARIANTS");
        //  tabHost.addTab(tabspec);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //getMenuInflater().inflate(R.menu.share_menu, menu);
        return super.onCreateOptionsMenu(menu);
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
}
