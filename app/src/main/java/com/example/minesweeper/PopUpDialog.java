package com.example.minesweeper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import com.example.minesweeper.startScreen.PlayFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Creates a custom pop up dialog
 *
 * @author Emma-sophie Andersen
 */
public class PopUpDialog {

    private TextView textView_title;
    private TextView textView_body;
    private TextView textView_enterScore;
    private EditText editText_name;
    private TextView textView_submit;
    private TextView textView_time;
    private Dialog dialog;
    private Activity activity;
    private Timer timer;

    private FirebaseDatabase fireBaseRootNode;
    private DatabaseReference databaseReference;
    int i;
    int difficultyLevel;

    private int seconds, minutes;
    GameActivity gameActivity;

    public PopUpDialog(Activity activity, Timer timer, String titleText, String bodyText, String displayTime , String enterText, String submitText, int difficultyLevel, GameActivity gameActivity) {
        this.activity = activity;
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.win_popup);

        textView_title = dialog.findViewById(R.id.txtView_title);
        textView_body = dialog.findViewById(R.id.txtView_yourHighScore);
        textView_enterScore = dialog.findViewById(R.id.txtView_enterHighScore);
        editText_name = dialog.findViewById(R.id.edt_name);
        textView_submit = dialog.findViewById(R.id.txt_submit);
        textView_time = dialog.findViewById(R.id.time_view);
        this.timer = timer;
        this.difficultyLevel = difficultyLevel;

        setPopUpData(titleText, bodyText, enterText, submitText, displayTime);
        this.gameActivity = gameActivity;

        Log.d("popup", textView_title.toString());
    }

    public void showPopUp() {
        dialog.show();
    }

    public void dismissPopUp() {
        dialog.dismiss();
    }

    private void setPopUpData(String titleText, String bodyText, String enterText, String submitText, String displayTime) {
        textView_title.setText(titleText);
        textView_body.setText(bodyText);
        textView_enterScore.setText(enterText);
        textView_submit.setText(submitText);
        textView_time.setText(displayTime);
        //textView_time.setText(timerText);
    }

    //put score in database
    public void enterscoredatabase(Timer timer, int difficulityLevel) {
        fireBaseRootNode = FirebaseDatabase.getInstance();

        //Enter the name of the player and is score
        if (difficulityLevel == 0) {
            databaseReference = fireBaseRootNode.getReference("easy/" + editText_name.getText().toString());//database.getReference("message/java/user"+i+"/score");
            databaseReference.setValue("" +  timer.chronometer.getText());//""+timer.getTime());
        } else if (difficulityLevel == 1) {
            databaseReference = fireBaseRootNode.getReference("intermediate/" + editText_name.getText().toString());//database.getReference("message/java/user"+i+"/score");
            databaseReference.setValue("" + timer.chronometer.getText());//""+timer.getTime());
        } else if (difficulityLevel == 2) {
            databaseReference = fireBaseRootNode.getReference("hard/" + editText_name.getText().toString());//database.getReference("message/java/user"+i+"/score");
            databaseReference.setValue("" + timer.chronometer.getText());//""+timer.getTime());
        } else {
            Log.d("catch high score error", String.valueOf(timer.chronometer.getText()));
        }
    }

    public void setListeners() {
        textView_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Thanks", activity.toString());
                enterscoredatabase(timer, difficultyLevel);
                timer.ResetTimer();
                gameActivity.goBackToMainMenu();
            }
        });
    }
}
