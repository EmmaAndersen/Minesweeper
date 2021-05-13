package com.example.minesweeper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    GraphicsHandler graphicsHandler;
    Board board;
    private Node node;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout =findViewById(R.id.mainView);
        graphicsHandler = new GraphicsHandler(this);   //Call to new constructor
        board = new Board(10,10,99);
    }

    /*public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle savedInstance){
        try{
            View v = inflater.inflate(R.layout.activity_main, null);
        }catch (Exception e){
            Log.e("Creating", "onCreateView", e);
            throw e;
        }
        return vg;
    }*/
}