package com.example.minesweeper;

/**
 * Game timer. Handles the start, pause and reset function for the timer (chronometer). This is used for the highscore
 * @author Emma-sophie Andersen
 * */

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Timer extends AppCompatActivity {

    boolean timerIsRunning;
    public Chronometer chronometer;
    private long pauseOffset;  //To calculate the time difference from when we started the chronometer and until it is paused.
    public long time;

    String timerValue;  //not used yet, thinking of using this as the variable to store the time in the database (for high score)

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);


    }

    public long getTime(){
        time = (int)chronometer.getBase();
        return time;
    }

    //Call the following methods with the co-responding buttons' onClick methods.

    public void StartTimer() {
        if(!timerIsRunning){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);  //Return {@link Clock} that starts at system boot, including time spent in sleep. starts at 0 and subtracts the offset.
            chronometer.start();
            timerIsRunning = true;
        }
    }

    public void PauseTimer() {
        if(timerIsRunning){
            chronometer.stop();  //this only stops the text, the chronometer still ticks with only this
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();  //This actually pauses the timer.
            timerIsRunning = false;
        }
    }

    public void ResetTimer() {
        chronometer.setBase(SystemClock.elapsedRealtime()); //Sets back to 0.
        pauseOffset = 0;
    }
}
