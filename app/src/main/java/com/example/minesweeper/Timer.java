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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer extends AppCompatActivity {

    boolean timerIsRunning;
    public Chronometer chronometer;
    private long pauseOffset;  //To calculate the time difference from when we started the chronometer and until it is paused.
    public int time;
    public String timeString;

    long timeTotal;
    int hour;
    int minute;
    int second;
    String hh;
    String mm;
    String ss;

    String timerValue;  //not used yet, thinking of using this as the variable to store the time in the database (for high score)

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
    }

    public String getTime(){
        time = (int)chronometer.getBase();
        long myTime = chronometer.getBase();
        //Date currentDate = new Date(myTime);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        return dateFormat.format(myTime);
    }

    public String displayedTime(){
        String myTime = hh + ":" + mm + ":" + ss;
        return myTime;
    }

    public String getFormat(){
        timeString = (String)chronometer.getFormat();
        return timeString;
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
