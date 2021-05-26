package com.example.minesweeper;

import android.view.View;

/**
 * <h1>Used to find views outside of activity classes</h1>
 *
 * @author Erik Broman
 * @since 2021-05-13, Edited 2021-05-26
 */
public interface ViewRequest {
    public View requestViewByID (int id);
}
