/*
package com.example.minesweeper;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class GameThread extends Thread {

    private boolean running = false;
    private GameActivity gameActivity;

    public GameThread(GameActivity _gameActivity) {
        this.gameActivity = _gameActivity;
    }

    public void startThread(boolean isRunning) {
        running = isRunning;
        Log.d("threading", gameActivity.board.toString());
    }

    private void HandleLogic() {
        gameActivity.board = new Board(4, 4, 3);
        gameActivity.populateNodeList();
    }

    @Override
    public void run() {
        HandleLogic();
    }
}
*/
