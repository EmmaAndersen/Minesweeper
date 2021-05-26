package com.example.minesweeper.startScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.minesweeper.Easy_fragment;
import com.example.minesweeper.GameActivity;
import com.example.minesweeper.Hard_fragment;
import com.example.minesweeper.HighScoreActivity;
import com.example.minesweeper.Intermediate_fragment;
import com.example.minesweeper.MainActivity;
import com.example.minesweeper.R;
import com.example.minesweeper.ViewAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

/**
 * <h1>The highscore fragment.</h1>
 *
 * @author Jenny Johannesson
 * @since 2021-05-13
 */
public class HighscoreFragment extends Fragment
{
    private View view;
    private Button backButton;
   // HighScoreActivity activity;
    public HighscoreFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        //Inflate the layout for this fragment
        view = inflater.inflate(R.layout.highscore, container, false);
        //buttonListener();
        return view;
    }



    private  void buttonListener(){
        backButton = view.findViewById(R.id.back_button);
        backButton.setOnClickListener(buttonListener);

    }
    private View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            MainActivity.fragmentManager.beginTransaction().replace(R.id.container, MenuManager.getInstance().startFragment, null ).commit();
        }
    };

}
