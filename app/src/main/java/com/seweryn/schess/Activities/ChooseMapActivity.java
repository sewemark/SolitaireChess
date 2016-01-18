package com.seweryn.schess.Activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.seweryn.schess.Adapters.PuzzleTabsAdapter;
import com.seweryn.schess.Controllers.DatabaseContextController;
import com.seweryn.schess.Controllers.IDatabaseContextController;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ChooseMapActivity extends AppCompatActivity  {

    IDatabaseContextController  controller;
    private ViewPager viewPager;
    private PuzzleTabsAdapter puzzleTabsAdapter;
    private ActionBar actionBar;
    String puzzleTabNames[] = {"EASY","MEDIUM", "HARD", "VERY HARD"};
    public ChooseMapActivity(){
    }
    @Override
    public void onCreate(Bundle savedInstanceeState) {
        super.onCreate(savedInstanceeState);
        controller = new DatabaseContextController(this);

        setContentView(R.layout.choose_map);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(puzzleTabsAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (PuzzleType type: PuzzleType.values()) {
            tabLayout.addTab(tabLayout.newTab().setText(String.valueOf(type)));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PuzzleTabsAdapter adapter = new PuzzleTabsAdapter(new DatabaseContextController(this),this.getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
