package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.minesweeper.startScreen.HighscoreFragment;
import com.example.minesweeper.startScreen.MenuManager;
import com.example.minesweeper.startScreen.StartFragment;

public class MainActivity extends AppCompatActivity {

    public StartFragment startFragment;
    HighscoreFragment highscoreFragment;
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        fragmentManager =  getSupportFragmentManager();
        if(findViewById(R.id.container) != null){
            if(savedInstanceState != null)
                return;

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            startFragment = new StartFragment();
            fragmentTransaction.add(R.id.container, MenuManager.getInstance().startFragment, null);
            fragmentTransaction.commit();
        }
    }
}