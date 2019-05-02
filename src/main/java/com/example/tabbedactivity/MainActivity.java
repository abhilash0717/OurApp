package com.example.tabbedactivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    private SectionsPageAdapter mSectinsPageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mSectinsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        setUpViewPager(viewPager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setUpViewPager(ViewPager viewPager){

        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new TimerFragment(),"TIMER");
        adapter.addFragment(new ClockFragment(),"CLOCK");
        adapter.addFragment(new CalendarFragment(),"CALENDAR");
        viewPager.setAdapter(adapter);
    }
}
