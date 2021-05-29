package com.example.minesweeper;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

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
    private Dialog dialog;
    private Activity activity;
    private Timer timer;

    private FirebaseDatabase fireBaseRootNode;
    private DatabaseReference databaseReference;
    int i;
    int difficultyLevel;

    public PopUpDialog(Activity activity, Timer timer, String titleText, String bodyText, String enterText, String submitText, int difficultyLevel) {
        this.activity = activity;
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.win_popup);

        textView_title = dialog.findViewById(R.id.txtView_title);
        textView_body = dialog.findViewById(R.id.txtView_yourHighScore);
        textView_enterScore = dialog.findViewById(R.id.txtView_enterHighScore);
        editText_name = dialog.findViewById(R.id.edt_name);
        textView_submit = dialog.findViewById(R.id.txt_submit);
        this.timer = timer;
        this.difficultyLevel = difficultyLevel;

        setPopUpData(titleText, bodyText, enterText, submitText);

        Log.d("popup", textView_title.toString());
    }

    public void showPopUp() {
        dialog.show();
    }

    public void dismissPopUp() {
        dialog.dismiss();
    }

    private void setPopUpData(String titleText, String bodyText, String enterText, String submitText) {
        textView_title.setText(titleText);
        textView_body.setText(bodyText);
        textView_enterScore.setText(enterText);
        textView_submit.setText(submitText);
    }

    //put score in database
    public void enterscoredatabase(Timer timer, int difficulityLevel) {
        fireBaseRootNode = FirebaseDatabase.getInstance();
        databaseReference = fireBaseRootNode.getReference("Numberofuser");//database.getReference("Numberofuser");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                i = Integer.parseInt(value);
                Log.d("TAG", "Value of user is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });


        //Enter the name of the player and is score
        if (difficulityLevel == 0) {
            databaseReference = fireBaseRootNode.getReference("easy/" + editText_name.getText().toString());//database.getReference("message/java/user"+i+"/score");
            databaseReference.setValue("" + timer.getTime());//""+timer.getTime());
        }
        else if (difficulityLevel == 1) {
            databaseReference = fireBaseRootNode.getReference("intermediate/" + editText_name.getText().toString());//database.getReference("message/java/user"+i+"/score");
            databaseReference.setValue("" + timer.getTime());//""+timer.getTime());
        }
        else if (difficulityLevel == 2) {
            databaseReference = fireBaseRootNode.getReference("hard/" + editText_name.getText().toString());//database.getReference("message/java/user"+i+"/score");
            databaseReference.setValue("" + timer.getTime());//""+timer.getTime());
        }
        else{
            Log.d("catch high score error", String.valueOf(timer.getTime()));
        }
        //We add the new player to the total of player
        //databaseReference = fireBaseRootNode.getReference("Numberofuser");//database.getReference("Numberofuser");
        //databaseReference.setValue("" + (i + 1));
    }

    public void setListeners() {
        textView_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Thanks", activity.toString());
                timer.ResetTimer();
                enterscoredatabase(timer, difficultyLevel);
            }
        });
    }
}
