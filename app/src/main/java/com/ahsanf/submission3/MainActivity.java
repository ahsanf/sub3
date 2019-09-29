package com.ahsanf.submission3;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.ahsanf.submission3.movie.model.Movie;
import com.ahsanf.submission3.movie.view.FragmentMovie;
import com.ahsanf.submission3.tvshow.model.TvShow;
import com.ahsanf.submission3.tvshow.view.FragmentTvShow;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_movie:
                    changeFragment(FragmentMovie.getInstance());
                    return true;
                case R.id.action_tvshow:
                    changeFragment(FragmentTvShow.getInstance());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (null==savedInstanceState){
           changeFragment(FragmentMovie.getInstance());

        }

    }

    private void changeFragment(Fragment fragment){
        FragmentManager transaction=getSupportFragmentManager();
                transaction.beginTransaction()
                        .replace(R.id.container_layout,fragment,null)
                        .commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
