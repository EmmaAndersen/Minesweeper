package com.example.minesweeper;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

    public PopUpDialog(Activity activity){
        this.activity = activity;
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.win_popup);

        textView_title = dialog.findViewById(R.id.txtView_title);
        textView_body = dialog.findViewById(R.id.txtView_yourHighScore);
        textView_enterScore = dialog.findViewById(R.id.txtView_enterHighScore);
        editText_name = dialog.findViewById(R.id.edt_name);
        textView_submit = dialog.findViewById(R.id.txt_submit);
    }

    public void showPopUp(){
        dialog.show();
    }

    public void dismissPopUp(){
        dialog.dismiss();
    }

    public void setListerners(){
        textView_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Thanks", activity.toString());
            }
        });
    }


}
