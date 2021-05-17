package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

  //GraphicsHandler graphicsHandler;
   // Board board;
   // private LinearLayout linearLayout;
    private TextView textView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //linearLayout =findViewById(R.id.mainView);
        // graphicsHandler = new GraphicsHandler(this,);
        //  board = new Board(10,10,99);

        // Example to use the dadjoke class (obtain a joke)
        textView=findViewById(R.id.textView);
        btn=findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DadJoke sentence =new DadJoke();
                sentence.ConnectionAPI(textView);

            }
        });


    }
}