package com.example.minesweeper;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

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

    public PopUpDialog(Activity _activity){
        this.activity = _activity;
        textView_title = dialog.findViewById(R.id.txtview_title);
        textView_body = dialog.findViewById(R.id.txtview_yourHighScore);
        textView_enterScore = dialog.findViewById(R.id.txtview_enterHighScore);
        editText_name = dialog.findViewById(R.id.edt_name);
        textView_submit = dialog.findViewById(R.id.txt_submit);

        initPopUp();
    }

    public void initPopUp(){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.win_popup);
    }

    public void showPopUp(){
        dialog.show();
    }

    public void dismissPopUp(){
        dialog.dismiss();
    }


}
