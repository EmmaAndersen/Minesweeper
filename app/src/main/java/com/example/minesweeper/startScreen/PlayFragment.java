package com.example.minesweeper.startScreen;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.minesweeper.GameActivity;
import com.example.minesweeper.MainActivity;
import com.example.minesweeper.R;

import java.lang.reflect.InvocationTargetException;

/**
 * <h1>The Startfragment .</h1>
 *
 * @author Jenny Johannesson
 * @since 2021-05-13
 */
public class PlayFragment extends Fragment
{
    Button easyButton;
    Button intermediateButton;
    Button hardButton;
    Button backButton;
    private View view;
    private MediaPlayer mediaPlayer;


    public PlayFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        //Inflate the layout for this fragment
        view = inflater.inflate(R.layout.play_screen, container, false);
        buttonListener();
        //display the bling sound
        blingsound();

        return view;
    }

    /**
     * <h1>Instantiate all the buttons.</h1>
     *
     * @author Jenny Johannesson
     * @since 2021-05-13
     */
    private  void buttonListener()
    {
        easyButton = view.findViewById(R.id.easy_button);
        easyButton.setOnClickListener(buttonListenerEasy);

        intermediateButton = view.findViewById(R.id.intermediate_button);
        intermediateButton.setOnClickListener(buttonListenerIntermediate);

        hardButton = view.findViewById(R.id.hard_button);
        hardButton.setOnClickListener(buttonListenerHard);

        backButton = view.findViewById(R.id.back_button);
        backButton.setOnClickListener(buttonListenerBack);
    }

    /**
     * <h1>Checks which button is pressed and puts now for a placeholder a message saying which button is pressed</h1>
     *<h1>Back button change back to the startfragment</h1>
     * @author Jenny Johannesson
     * @since 2021-05-13
     */
    private View.OnClickListener buttonListenerEasy = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getActivity(),GameActivity.class);
            intent.putExtra("width",9);
            intent.putExtra("height",9);
            intent.putExtra("bombCount",10);   //Changed this to 1 for testing
            intent.putExtra("difficulityLevel",0);

            if(intent != null)
            {
               startActivity(intent);
                getActivity().finish();

                Log.d("HEJSAN1" , String.valueOf(getActivity()));

            }
        }
    };
    private View.OnClickListener buttonListenerIntermediate = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),GameActivity.class);
            intent.putExtra("width",16);
            intent.putExtra("height",16);
            intent.putExtra("bombCount",40);
            intent.putExtra("difficulityLevel",1);
            if(intent != null)
            {
                startActivity(intent);
                getActivity().finish();
                Log.d("HEJSAN2" , String.valueOf(getActivity()));

            }
        }
    };
    private View.OnClickListener buttonListenerHard = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getActivity(),GameActivity.class);
            intent.putExtra("width",30);
            intent.putExtra("height",16);
            intent.putExtra("bombCount",99);
            intent.putExtra("difficulityLevel",2);
            if(intent != null)
            {
                startActivity(intent);
                getActivity().finish();
                Log.d("HEJSAN3" , String.valueOf(getActivity()));
            }
        }
    };
    private View.OnClickListener buttonListenerBack = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

                MainActivity.fragmentManager.beginTransaction().replace(R.id.container, MenuManager.getInstance().startFragment, null ).commit();
        }
    };

   //function to display the bling sound
        public void blingsound(){
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.bling);
            }
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    mediaPlayer.release();
                    mediaPlayer = null;

                }
            });
            mediaPlayer.start();
        }

    @Override
    public void onStop() {
        super.onStop();
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}
