package com.example.minesweeper.startScreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.minesweeper.GameActivity;
import com.example.minesweeper.HighScoreActivity;
import com.example.minesweeper.MainActivity;
import com.example.minesweeper.R;

/**
 * <h1>Startfragment.</h1>
 *
 * @author Jenny Johannesson
 * @since 2021-05-13
 */
public class StartFragment extends Fragment
{
    private View view;
    private Button startButton;
    private Button creditButton;
    private Button quitButton;
    private Button highscoreButton;

    public StartFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        //Inflate the layout for this fragment
        view = inflater.inflate(R.layout.start_screen, container, false);
       buttonListener();
        return view;
    }

    /**
     * <h1>Instantiate the buttons.</h1>
     *
     * @author Jenny Johannesson
     * @since 2021-05-13
     */
    private void buttonListener()
    {
        startButton = view.findViewById(R.id.start_button);
        startButton.setOnClickListener(buttonListenerStart);

        creditButton = view.findViewById(R.id.credit_Button);
        creditButton.setOnClickListener(buttonListenerCredit);

        quitButton = view.findViewById(R.id.quit_button);
        quitButton.setOnClickListener(buttonListenerQuit);

        highscoreButton = view.findViewById(R.id.highscore_button);
        highscoreButton.setOnClickListener(buttonListenerHighscore);
    }

    /**
     * <h1>Checks which button is pressed and then changes the fragments.</h1>
     *
     * @author Jenny Johannesson
     * @since 2021-05-13
     */
    private View.OnClickListener buttonListenerStart = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.container, MenuManager.getInstance().playFragment, null ).commit();
        }

    };

    private View.OnClickListener buttonListenerCredit = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

                MainActivity.fragmentManager.beginTransaction().replace(R.id.container, MenuManager.getInstance().creditFragment, null ).commit();

        }

    };
    private View.OnClickListener buttonListenerHighscore = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

         Intent intent = new Intent(getActivity(), HighScoreActivity.class);

            if(intent != null)
            {
                //v.getContext().startActivity(intent);
                startActivity(intent);
                Log.d("HEJSAN" , String.valueOf(getActivity()));

            }
               // MainActivity.fragmentManager.beginTransaction().replace(R.id.container, MenuManager.getInstance().highscoreFragment, null ).commit();

        }

    };
    private View.OnClickListener buttonListenerQuit = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            CharSequence quit = "You pressed quit";

                // Activity.finish();
                //System.exit(0);
                Toast.makeText(getActivity().getApplicationContext(), quit, Toast.LENGTH_SHORT).show();

        }

    };

}
