package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.minesweeper.startScreen.MenuManager;
import com.example.minesweeper.startScreen.StartFragment;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Minesweeper);
        fragmentManager =  getSupportFragmentManager();
        setContentView(R.layout.activity_main);


        if(findViewById(R.id.container) != null) {
            if (savedInstanceState != null)
                return;
        }
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, MenuManager.getInstance().startFragment, null);
            fragmentTransaction.commit();
    }



}