package com.example.minesweeper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.minesweeper.startScreen.PlayFragment;
import com.example.minesweeper.startScreen.StartFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * <h1>Creates the user interface based on the board created at the start of the game</h1>
 *
 * @author Erik Broman
 * @since 2021-05-13
 */
public class GameActivity extends Activity {
    protected Board board;
    protected ConstraintLayout constraintLayout;
    private MediaPlayer mediaPlayer;
    protected TextView dialogTextView;
    //GameThread gameThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        constraintLayout = findViewById(R.id.boardView);
        //gameThread = new GameThread(this);
        //gameThread.start();
        board = new Board(4, 4, 3);
        //populateNodeList();
        myGameThread();
        dialogTextView = new TextView(getBaseContext());
    }


    private ViewRequest viewRequest = new ViewRequest() {

        public View requestViewByID(int id) {
            return findViewById(id);
        }
    };

    private void myGameThread(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{

                populateNodeList();

                } catch (final Exception e){
                    Log.i("runnable test", "exception in thread");
                }
            }
        });
    }

    /**
     * <h1>Creates ImageButtons and sets their attributes according to the created board </h1>
     *
     * @author Erik Broman
     * @since 2021-05-13, Edited 2021-05-26
     */
    protected void populateNodeList() {
        //Create a ImageButton for every node
        for (int i = 0; i < board.nodes.length; i++) {
            Node node = board.nodes[i];
            ImageButton button = new ImageButton(this);
            button.setImageResource(R.drawable.hidden);

            //Gives the button a unique id that is equivalent to the element of the node the button represents in the board.nodes array.
            button.setId(i);

            //removePadding to get the desired grid shape
            button.setPadding(0, 0, 0, 0);

            //prevents image from being croped
            button.setScaleType(ImageView.ScaleType.FIT_XY);

            //Max resolution for the image
            button.setMaxWidth(128);
            button.setMaxHeight(128);

            //sets the position using the top left corner
            button.setLeft(128 * node.posX);
            button.setTop(128 * node.posY);

            //sets the width and height for the button
            button.setRight(button.getLeft() + 128);
            button.setBottom(button.getTop() + 128);

            //set LayoutParams to WRAP_CONTENT as this does not stretch the image
            button.setLayoutParams(new ViewGroup.LayoutParams(256, 256));

            //when a hidden node is clicked reveal the node and set it to NOT clickable, if hidden node is a bomb trigger Game Over
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (node.RevealNode(button, viewRequest, board.gridY, node)) {
                        GameOver();
                    }
                }
            });

            //when a hidden node is longClicked then toggle the flag attribute, on->off, off->on
            button.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    node.ToggleFlag(button);

                    //return true to not carry this event further!
                    return true;
                }
            });

            //add the button to the constraintLayout
            constraintLayout.addView(button);

            //Used to manipulate the button in the constraint layout
            ConstraintSet set = new ConstraintSet();
            set.clone(constraintLayout);

            //left side of this square is connected to the right side of the constraint layout
            if (board.nodes[i].posX == 0) {
                set.connect(button.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
            }
            //left side of this square is connected to the right side of the square to the right of this square
            else {
                set.connect(button.getId(), ConstraintSet.LEFT, findViewById(i - 1).getId(), ConstraintSet.RIGHT);
            }

            //top side of this square is connected to the bottom side of the constraint layout
            if (board.nodes[i].posY == 0) {
                set.connect(button.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
            }
            //top side of this square is connected to the bottom side of the square above this square
            else {
                set.connect(button.getId(), ConstraintSet.TOP, findViewById(i - board.gridY).getId(), ConstraintSet.BOTTOM);
            }

            //Updates the constraint layout with the constraints for the new button
            set.applyTo(constraintLayout);
        }
    }

    /**
     * <h2>Shows a pop up alert</h2>
     *
     * @author Emma-sophie Andersen
     */
    private void GameOverAlertShow() {
        AlertDialog.Builder gameOverAlert = new AlertDialog.Builder(this);
        gameOverAlert.setTitle("GAME OVER");
        gameOverAlert.setCustomTitle(dialogTextView);
        gameOverAlert.setCancelable(false);
        gameOverAlert.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent retryIntent = getIntent();
                finish();
                startActivity(retryIntent);
                dialog.cancel();
                //Toast.makeText(getApplicationContext(), "Yes clicked", Toast.LENGTH_SHORT).show();
            }
        });

        gameOverAlert.setNegativeButton("Return to Main menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mainIntent = new Intent(GameActivity.this, MainActivity.class);
                //finish();
                startActivity(mainIntent);
                dialog.cancel();
                //Toast.makeText(getApplicationContext(), "No clicked", Toast.LENGTH_SHORT).show();
            }
        });
        gameOverAlert.show();
    }

    /**
     * <h1>Disables all buttons, plays sfx, and tries to show a joke </h1>
     * sfx + jokes done by unknown
     *
     * @author Erik Broman
     * @since Edited 2021-05-26
     */
    private void GameOver() {
        Log.d("GameOver", "GameOver");//Game OVER

        //disables all buttons, and reveals them
        for (int i = 0; i < board.nodes.length; i++) {
            findViewById(i).setClickable(false);
            findViewById(i).setLongClickable(false);
            GraphicsHandler.RevealNodeTextureUpdate(findViewById(i), board.nodes[i]);
        }

        GameOverAlertShow();

        /**
         * @author Cassandra
         * */
        //display the boom sound
        boomsound();
        //display the joke
        try {
            ConnectionAPI();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Cassandra
     */
    //function to display the boom sound
    public void boomsound() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.boom);
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
    protected void onStop() {
        super.onStop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    /**
     * @author Cassandra
     */
    //function to obtain the joke
    public void ConnectionAPI() throws IOException {

        OkHttpClient client = new OkHttpClient();
        String url = "https://dad-jokes.p.rapidapi.com/random/joke";//"https://reqres.in/api/users?page=2";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key", "598a6219d8msh7db487f981018bdp1f92a5jsna6c74dc55e2d")
                .addHeader("x-rapidapi-host", "dad-jokes.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
            }


            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    String rep = myResponse.substring(myResponse.indexOf("setup") + 8, myResponse.indexOf("punchline") - 3)
                            + "\n" + myResponse.substring(myResponse.indexOf("punchline") + 12, myResponse.length() - 4);

                    GameActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            dialogTextView.setText("GAME OVER!\n\n" + rep);
                            /*Toast.makeText(getApplicationContext(), rep,
                                    Toast.LENGTH_LONG).show();*/
                        }
                    });

                }
            }


        });
    }
}
