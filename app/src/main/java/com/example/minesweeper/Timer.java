package com.example.minesweeper;

/*
 * This class is just for creating the timer itself. The code will be copy pasted into to game later on.
 * */

import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Timer extends AppCompatActivity {

    /*private TextView displayedTimer;
    private Button pauseButton;
    private Button resetButton;*/

    boolean timerIsRunning;
    private Chronometer chronometer;
    private long pauseOffset;  //To calculate the time difference from when we started the chronometer and until it is paused.
   /* private int minutes;
    private int seconds;
    private long milliseconds;
    private String totalTime;*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init Buttons and the chronometer TextView with R.id.ID

        /*pauseButton.setOnClickListener(new View.OnClickListener() {
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

        UpdateTimer();*/
    }

    public void StartTimer(View view) {
        if(!timerIsRunning){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);  //Return {@link Clock} that starts at system boot, including time spent in sleep. starts at 0 and subtracts the offset.
            chronometer.start();
            timerIsRunning = true;
        }
    }

    public void PauseTimer(View view) {
        if(timerIsRunning){
            chronometer.stop();  //this only stops the text, the chronometer still ticks with only this
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();  //This actually pauses the timer.
            timerIsRunning = false;
        }
    }

    public void ResetTimer(View view) {

    }

   /* private void UpdateTimer() {
        minutes = (int) milliseconds / 1000 / 60;
        seconds = (int) milliseconds / 1000 % 60;

        totalTime = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        displayedTimer.setText(totalTime);
    }*/
}
