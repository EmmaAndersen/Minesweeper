package com.example.minesweeper;

import android.graphics.drawable.Drawable;
import android.widget.ImageButton;

/**
 * <h1>Handles game graphics, we only ever want one handler</h1>
 *
 * @author Erik Broman
 * @since 2021-05-13, Edited 2021-05-26
 */
final class GraphicsHandler {

    private GraphicsHandler() {

    }

    /**
     * <h1>Changes texture to flag, for selected button</h1>
     *
     * @author Erik Broman
     * @since 2021-05-13, Edited 2021-05-26
     */
    public static void SetTextureToFlag(ImageButton button, Node node) {
        button.setImageResource(R.drawable.flag);
    }

    /**
     * <h1>Changes texture to hidden, for selected button</h1>
     *
     * @author Erik Broman
     * @since 2021-05-13, Edited 2021-05-26
     */
    public static void SetTextureToHidden(ImageButton button, Node node) {
        button.setImageResource(R.drawable.hidden);
    }

    /**
     * <h1>Updates texture to show content of the selected node</h1>
     *
     * @author Erik Broman
     * @since 2021-05-13, Edited 2021-05-26
     */
    public static void RevealNodeTextureUpdate(ImageButton button, Node node) {
        switch (node.nodeContains) {
            case EMPTY:
                button.setImageResource(R.drawable.empty);
                break;
            case ONE:
                button.setImageResource(R.drawable.one);
                break;
            case TWO:
                button.setImageResource(R.drawable.two);
                break;
            case THREE:
                button.setImageResource(R.drawable.three);
                break;
            case FOUR:
                button.setImageResource(R.drawable.four);
                break;
            case FIVE:
                button.setImageResource(R.drawable.five);
                break;
            case SIX:
                button.setImageResource(R.drawable.six);
                break;
            case SEVEN:
                button.setImageResource(R.drawable.seven);
                break;
            case EIGHT:
                button.setImageResource(R.drawable.eight);
                break;
            case BOMB:
                button.setImageResource(R.drawable.bomb);
                break;
        }
    }
}