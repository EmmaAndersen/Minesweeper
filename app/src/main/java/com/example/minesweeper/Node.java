package com.example.minesweeper;

import android.util.Log;
import android.widget.ImageButton;

/**
 * <h1>A specific position in the map, holds an array of connected Edges</h1>
 *
 * @author Erik Broman
 * @version 1.0
 * @since 2021-05-06
 */
public class Node {
    public Edge[] edges;

    /**
     * <h1>Holds all possible hidden states</h1>
     * Ordering of enums allows numbering of non-bomb nodes
     * to bo done by incrementing the enum value for each
     * adjacent bomb.
     *
     * @author Erik Broman
     * @since 2021-05-06
     */
    public enum NodeContains {
        EMPTY,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        BOMB {
            @Override
            public NodeContains Next() {
                return NodeContains.BOMB;
            }
        };

        /**
         * <h1>Set the node to contain the next enum</h1>
         * If the current enum is BOMB then it returns BOMB
         *
         * @return NodeContains This returns the next enum.
         * @author Erik Broman
         * @since 2021-05-06
         */
        public NodeContains Next() {
            return NodeContains.values()[ordinal() + 1];
        }
    }

    public NodeContains nodeContains = NodeContains.EMPTY;

    public final int posX;
    public final int posY;
    public boolean isHidden = true;
    public boolean isFlagged = false;

    public Node(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * <h1>Initialise the edges array with the size of valid connections</h1>
     *
     * @author Erik Broman
     * @since 2021-05-06
     */
    public void InitializeNodesConnectionArray(int size) {
        edges = new Edge[size];
    }

    /**
     * <h1>Connects this edge with another edge</h1>
     *
     * @author Erik Broman
     * @since 2021-05-06
     */
    public void ConnectEdge(int edgeIndex, Node other) {
        edges[edgeIndex] = new Edge(this, other);
    }

    /**
     * <h1>Get the content of the node</h1>
     *
     * @return NodeContains This returns the enum of the node.
     * @author Erik Broman
     * @since 2021-05-06
     */
    public NodeContains GetNodeContent() {
        return nodeContains;
    }

    /**
     * <h1>Reveals the selected node</h1>
     * Reveals connected nodes if selected node is empty.
     * Requests graphical updates from the GraphicsHandler
     *
     * @return boolean This returns true if we revealed a mine, GAME OVER.
     * @author Erik Broman
     * @since 2021-05-06
     */
    public boolean RevealNode(ImageButton button, ViewRequest viewRequest)//1 is dead
    {
        //we don't allow clicks to reveal flagged nodes
        if (isFlagged)
            return false;

        //reveal the node
        isHidden = false; //TODO check if actually used don't think it is

        //reveal the bomb and returns that the user has lost
        if (nodeContains == NodeContains.BOMB) {
            GraphicsHandler.RevealNodeTextureUpdate(button, this);
            return true;
        }

        //Reveal this node and all adjacent nodes, disables the click function of the button
        if (nodeContains == NodeContains.EMPTY)
            for (Edge edge : edges) {
                if (button.isClickable()) {
                    Log.d("ID of button", String.valueOf((ImageButton) viewRequest.requestViewByID(edge.toNode.posX * edge.toNode.posY)));
                    button.setClickable(false);
                    edge.toNode.RevealNode((ImageButton) viewRequest.requestViewByID(edge.toNode.posX * edge.toNode.posY), viewRequest);
                }
            }
        GraphicsHandler.RevealNodeTextureUpdate(button, this);
        return false;
    }
}
