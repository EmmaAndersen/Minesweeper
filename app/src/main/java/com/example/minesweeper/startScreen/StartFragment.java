package com.example.minesweeper.startScreen;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

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
        startButton.setOnClickListener(buttonListener);

        creditButton = view.findViewById(R.id.credit_Button);
        creditButton.setOnClickListener(buttonListener);

        quitButton = view.findViewById(R.id.credit_button);
        quitButton.setOnClickListener(buttonListener);

        highscoreButton = view.findViewById(R.id.highscore_button);
        highscoreButton.setOnClickListener(buttonListener);
    }

    /**
     * <h1>Checks which button is pressed and then changes the fragments.</h1>
     *
     * @author Jenny Johannesson
     * @since 2021-05-13
     */
    private View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            CharSequence quit = "You pressed quit";
            if(v.equals(startButton)){
                MainActivity.fragmentManager.beginTransaction().replace(R.id.container, MenuManager.getInstance().playFragment, null ).commit();

            }
            else if(v.equals(creditButton)){
                MainActivity.fragmentManager.beginTransaction().replace(R.id.container, MenuManager.getInstance().creditFragment, null ).commit();

            }
            else  if(v.equals(highscoreButton)){
                MainActivity.fragmentManager.beginTransaction().replace(R.id.container, MenuManager.getInstance().highscoreFragment, null ).commit();
            }
            else if(v.equals(quitButton)){
               // Activity.finish();
                //System.exit(0);
                Toast.makeText(getActivity().getApplicationContext(), quit, Toast.LENGTH_SHORT).show();
                //MainActivity.fragmentManager.beginTransaction().replace(R.id.container, MenuManager.getInstance().startFragment, null ).commit();
            }
        }

    };

}
