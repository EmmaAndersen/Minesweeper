package com.example.minesweeper.startScreen;

import android.os.Bundle;
import android.view.Menu;
import android.view.accessibility.AccessibilityRecord;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.minesweeper.Easy_fragment;
import com.example.minesweeper.GameActivity;
import com.example.minesweeper.Hard_fragment;
import com.example.minesweeper.R;

/**
 * <h1>The menumanager that instantiate all menu options.</h1>
 *
 * @author Jenny Johannesson
 * @since 2021-05-13
 */
public  class MenuManager {

    public static MenuManager instance;
    public StartFragment startFragment;
    public HighscoreFragment highscoreFragment;
    public CreditFragment creditFragment;
    public PlayFragment playFragment;

    /**
     * <h1>A singleton for the class</h1>
     *
     * @author Jenny Johannesson
     * @since 2021-05-13
     */
    public static MenuManager getInstance()
    {
        if (instance == null)
        {
            instance = new MenuManager();
        }
        return instance;
    }
    private MenuManager(){
        startFragment = new StartFragment();
        highscoreFragment = new HighscoreFragment();
        creditFragment = new CreditFragment();

    }

}
