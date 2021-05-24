package com.example.minesweeper;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.nio.channels.NotYetBoundException;

public class HighScoreActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

   public HighScoreActivity(){}
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);
        tabLayout = (TabLayout) findViewById(R.id.tablayoutID);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarID);
        viewPager = (ViewPager) findViewById(R.id.viewpageID);
        ViewAdapter adapter = new ViewAdapter(getSupportFragmentManager());

        // Add fragments
        adapter.AddAFragment(new Easy_fragment(), "Easy");
        adapter.AddAFragment(new Intermediate_fragment(), "Intermediate");
        adapter.AddAFragment(new Hard_fragment(), "hard");

        //Adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
