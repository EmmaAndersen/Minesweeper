package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        board = new Board(4,4,3);
        GameActivity gameActivity = new GameActivity();
        populateNodeList();
    }




    private ViewRequest viewRequest = new ViewRequest() {

        public View requestViewByID(int id) {
            return findViewById(id);
        }
    };

    /**
     * <h1>Creates ImageButtons and sets their attributes according to the created board </h1>
     *
     * @author Erik Broman
     * @since 2021-05-13, Edited 2021-05-26
     */
    private void populateNodeList() {

        final ConstraintLayout constraintLayout = findViewById(R.id.boardView);

        //Create a ImageButton for every node
        for (int i = 0; i < board.nodes.length; i++) {
            Node node = board.nodes[i];
            ImageButton button = new ImageButton(this);
            button.setImageResource(R.drawable.hidden);

            //Gives the button a unique id that is equivalent to the element of the node the button represents in the board.nodes array.
            button.setId(i);

            //removePadding to get the desired grid shape
            button.setPadding(0,0,0,0);

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
                    if(node.RevealNode(button,viewRequest,board.gridX,board.gridY, node))
                    {
                        Log.d("GameOver","GameOver");//Game OVER
                    }
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
}