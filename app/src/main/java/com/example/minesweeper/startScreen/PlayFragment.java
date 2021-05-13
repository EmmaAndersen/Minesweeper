package com.example.minesweeper.startScreen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.minesweeper.MainActivity;
import com.example.minesweeper.R;

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
    Button customButton;
    Button backButton;
    private View view;

    public PlayFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        //Inflate the layout for this fragment
        view = inflater.inflate(R.layout.play_screen, container, false);
        buttonListener();
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
        easyButton.setOnClickListener(buttonListener);

        intermediateButton = view.findViewById(R.id.intermediate_button);
        intermediateButton.setOnClickListener(buttonListener);

        hardButton = view.findViewById(R.id.hard_button);
        hardButton.setOnClickListener(buttonListener);

        customButton = view.findViewById(R.id.cusom_button);
        customButton.setOnClickListener(buttonListener);

        backButton = view.findViewById(R.id.back_button);
        backButton.setOnClickListener(buttonListener);
    }

    /**
     * <h1>Checks which button is pressed and puts now for a placeholder a message saying which button is pressed</h1>
     *<h1>Back button change back to the startfragment</h1>
     * @author Jenny Johannesson
     * @since 2021-05-13
     */
    private View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            CharSequence  easy = "You chose the easymode";
            CharSequence  intermidate = "You chose the intermidatemode";
            CharSequence  hard = "You chose the hardmode";
            CharSequence  custom = "You chose the custommode";
            if(v.equals(easyButton)){
                Toast.makeText(getActivity().getApplicationContext(), easy, Toast.LENGTH_SHORT).show();
            }
            else if(v.equals(intermediateButton)){
                Toast.makeText(getActivity().getApplicationContext(), intermidate, Toast.LENGTH_SHORT).show();
            }
            else if(v.equals(hardButton)){
                Toast.makeText(getActivity().getApplicationContext(), hard, Toast.LENGTH_SHORT).show();
            }
            else if(v.equals(customButton)){
                Toast.makeText(getActivity().getApplicationContext(), custom, Toast.LENGTH_SHORT).show();
            }
            else if(v.equals(backButton)){
                //Toast.makeText(getActivity().getApplicationContext(), custom, Toast.LENGTH_SHORT).show();
                MainActivity.fragmentManager.beginTransaction().replace(R.id.container, MenuManager.getInstance().startFragment, null ).commit();
            }
        }

    };

}
