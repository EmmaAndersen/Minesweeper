package com.example.minesweeper;

/*
 * This class is just for creating the timer itself. The code will be copy pasted into to game later on.
 * */

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Timer extends AppCompatActivity {

    private TextView displayedTimer;
    private Button pauseButton;
    private Button resetButton;

    boolean timerIsRunning;
    private int minutes;
    private int seconds;
    private long milliseconds;
    private String totalTime;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init Buttons and the timer TextView with R.id.buttonID

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerIsRunning) {
                    PauseTimer();
                } else {
                    StartTimer();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetTimer();
            }
        });

        UpdateTimer();
    }

    private void StartTimer() {
        timerIsRunning = true;
    }

    private void PauseTimer() {

    }

    private void ResetTimer() {

    }

    private void UpdateTimer() {
        minutes = (int) milliseconds / 1000 / 60;
        seconds = (int) milliseconds / 1000 % 60;

        totalTime = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        displayedTimer.setText(totalTime);
    }
}
