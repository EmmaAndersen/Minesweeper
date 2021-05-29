package com.example.minesweeper;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Creates a custom pop up dialog
 * @author Emma-sophie Andersen
 * */
public class PopUpDialog {

    private TextView textView_title;
    private TextView textView_body;
    private TextView textView_enterScore;
    private EditText editText_name;
    private TextView textView_submit;
    private Dialog dialog;
    private Activity activity;
    private Timer timer;

    public PopUpDialog(Activity activity, Timer timer, String titleText, String bodyText, String enterText, String submitText){
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

        setPopUpData(titleText, bodyText, enterText, submitText);

        Log.d("popup", textView_title.toString());
    }

    public void showPopUp(){
        dialog.show();
    }

    public void dismissPopUp(){
        dialog.dismiss();
    }

    private void setPopUpData(String titleText, String bodyText, String enterText, String submitText){
        textView_title.setText(titleText);
        textView_body.setText(bodyText);
        textView_enterScore.setText(enterText);
        textView_submit.setText(submitText);
    }

    public void setListeners(){
        textView_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Thanks", activity.toString());
                timer.ResetTimer();
            }
        });
    }
}
