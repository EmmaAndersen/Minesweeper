package com.example.minesweeper;

import android.util.Log;
import android.widget.ImageButton;

import java.util.List;

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
     * @since 2021-05-06, Edited 2021-05-26
     */
    public boolean RevealNode(ImageButton button, ViewRequest viewRequest, int sizeOfBoardX, int sizeOfBoardY, Node currentNode)//1 is dead
    {

        //we don't allow clicks to reveal flagged nodes
        if (currentNode.isFlagged)
            return false;

        //reveal the node
        currentNode.isHidden = false; //TODO check if actually used don't think it is

        //disable the button, we only want buttons to be clickable once
        button.setClickable(false);

        //change graphics of the button to what is hidden beneath
        GraphicsHandler.RevealNodeTextureUpdate(button, currentNode);

        //returns that the user has lost as the reveal node was a bomb
        if (currentNode.nodeContains == NodeContains.BOMB) {
            return true;
        }

        //Reveal all adjacent nodes, as there are no bombs adjacent to this node
        if(currentNode.nodeContains == NodeContains.EMPTY)
        {
            for (int i = 0; i < currentNode.edges.length; i++)
            {
                ImageButton temp =  (ImageButton) viewRequest.requestViewByID(currentNode.edges[i].toNode.posX + currentNode.edges[i].toNode.posY * sizeOfBoardY);
                if(temp.isClickable()){
                    RevealNode(temp, viewRequest, sizeOfBoardX, sizeOfBoardY,currentNode.edges[i].toNode);
                }
            }
        }
        return false;
    }
}