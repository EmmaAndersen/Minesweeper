package com.example.minesweeper;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * <h1>Represents the game board. A specific sized area composed of connected nodes</h1>
 *
 * @author Erik Broman
 * @version 1.0
 * @since 2021-05-06
 */
public class Board {

    public Node[] nodes;
    private Node[] bombNodes;
    //Size of the game board
    public final int gridX;
    public final int gridY;
    //amount of bombs on the game board
    private final int bombCount;
    //used to randomize location of bombs on the game board
    private final Random random;
    //Keeps track of our win condition
    public int emptyNodesLeft;
    //keeps track of how many nodes have been flagged
    public int flaggedNodes =0;


    public Board(int countX, int countY, int countBomb) {
        gridX = countX;
        gridY = countY;
        bombCount = countBomb;
        flaggedNodes = bombCount;
        random = new Random();
        bombNodes = new Node[bombCount];
        emptyNodesLeft = (gridX * gridY) - bombCount;
        PopulateBoard();
        PopulateBombs();
        PopulateNumbers();

    }

    /**
     * <h1>Creates the Nodes that make up the game board.</h1>
     *
     * @author Erik Broman
     * @since 2021-05-06, Edited 2021-05-26
     */
    void PopulateBoard() {
        nodes = new Node[gridX * gridY];
        int counter = 0;

        //builds the grid left to right, top to bottom
        for (int y = 0; y < gridX; y++) {
            for (int x = 0; x < gridY; x++) {
                AddNewNodeToBoard(counter++, x, y);
            }
        }

        for (int currentNodeIndex = 0; currentNodeIndex < nodes.length; currentNodeIndex++) {
            SetConnections(
                    PruneConnections(
                            IdentifyNeighboursGrid(currentNodeIndex), currentNodeIndex),
                    currentNodeIndex);
        }
    }

    /**
     * <h1>Adds a new Node to the board</h1>
     *
     * @author Erik Broman
     * @since 2021-05-06
     */
    void AddNewNodeToBoard(int index, int x, int y) {
        nodes[index] = new Node(x, y);
    }

    /**
     * <h1>Identifies what adjacent Nodes This Node can connect to.</h1>
     *
     * @param currentNodeIndex Node index
     * @return LinkedHashMap This returns a LinkedHashMap with id, and boolean, where true represents the existence of a neighbouring grid.
     * @author Erik Broman
     * @since 2021-05-06
     */
    private LinkedHashMap<Integer, Boolean> IdentifyNeighboursGrid(int currentNodeIndex) {
        LinkedHashMap<Integer, Boolean> keyValuePairs = new LinkedHashMap<>();

        keyValuePairs.put(currentNodeIndex - 1 - gridX, ValueWithinRange(currentNodeIndex, currentNodeIndex - 1 - gridX)); //topLeft
        keyValuePairs.put(currentNodeIndex - gridX, ValueWithinRange(currentNodeIndex, currentNodeIndex - gridX));         //top
        keyValuePairs.put(currentNodeIndex + 1 - gridX, ValueWithinRange(currentNodeIndex, currentNodeIndex + 1 - gridX)); //topRight

        keyValuePairs.put(currentNodeIndex - 1, ValueWithinRange(currentNodeIndex, currentNodeIndex - 1));                 //left
        keyValuePairs.put(currentNodeIndex + 1, ValueWithinRange(currentNodeIndex, currentNodeIndex + 1));                 //right

        keyValuePairs.put(currentNodeIndex - 1 + gridX, ValueWithinRange(currentNodeIndex, currentNodeIndex - 1 + gridX)); //bottomLeft
        keyValuePairs.put(currentNodeIndex + gridX, ValueWithinRange(currentNodeIndex, currentNodeIndex + gridX));         //bottom
        keyValuePairs.put(currentNodeIndex + 1 + gridX, ValueWithinRange(currentNodeIndex, currentNodeIndex + 1 + gridX)); //bottomRight

        return keyValuePairs;
    }

    /**
     * <h1>Check that the evaluatedNode actually exists</h1>
     *
     * @author Erik Broman
     * @since 2021-05-06
     */
    private boolean ValueWithinRange(int currentNodeIndex, int evaluateNodeIndex) {
        if (evaluateNodeIndex < 0 || evaluateNodeIndex >= nodes.length) {
            return false;
        }
        return ModuloCheck(currentNodeIndex, evaluateNodeIndex);
    }

    /**
     * <h1>Check for overflow/underflow</h1>
     * i%gridSide == baseline
     * i-1%gridSide == baseline-1
     * i+1%gridSide == baseline+1
     *
     * @author Erik Broman
     * @since 2021-05-06
     */
    private boolean ModuloCheck(int currentNodeIndex, int evaluateNodeIndex) {
        int baseline = currentNodeIndex % gridX;
        int moduloValue = evaluateNodeIndex % gridX;
        return moduloValue == baseline + 1 || moduloValue == baseline - 1 || moduloValue == baseline;
    }

    /**
     * <h1>Removes edges that don't lead to Nodes</h1>
     *
     * @author Erik Broman
     * @since 2021-05-06
     */
    private LinkedHashMap<Integer, Boolean> PruneConnections(LinkedHashMap<Integer, Boolean> keyValuePairs, int currentNode) {
        Stack<Integer> stack = new Stack();

        //TODO Replace LinkedHashMap with something that allows the use of: GetElementAt(index);, Check out Custom Vector class

        //get iterator for collection
        for (java.util.Map.Entry<Integer, Boolean> integerBooleanEntry : keyValuePairs.entrySet()) {
            stack.push(integerBooleanEntry.getKey());//stores the iterators in reverse order
        }

        while (!stack.empty()) {
            int evalKey = stack.pop();
            //noinspection ConstantConditions
            if (!keyValuePairs.get(evalKey))//cleans LinkedHashMap of invalid connections
            {
                keyValuePairs.remove(evalKey);
            }
        }
        nodes[currentNode].InitializeNodesConnectionArray(keyValuePairs.size());//initialise the size for the Edge array in the current node
        return keyValuePairs;
    }

    /**
     * <h1>Iterates over the valid connections and sets the connections for the current node</h1>
     *
     * @author Erik Broman
     * @since 2021-05-06
     */
    private void SetConnections(LinkedHashMap<Integer, Boolean> keyValuePairs, int currentNode) {
        //TODO Replace LinkedHashMap with something that allows the use of: GetElementAt(index);
        Iterator<LinkedHashMap.Entry<Integer, Boolean>> iterator = keyValuePairs.entrySet().iterator();//get iterator for collection
        int edgeIterator = 0;
        while (iterator.hasNext()) {
            nodes[currentNode].ConnectEdge(edgeIterator++, nodes[iterator.next().getKey()]);
        }
    }

    /**
     * <h1>Randomises the location of the bombs and updates the Nodes collection with the bombs location</h1>
     *
     * @author Erik Broman
     * @since 2021-05-06
     */
    private void PopulateBombs() {
        Log.d("size", String.valueOf(nodes.length));
        List<Integer> availableSquares = new ArrayList<>(nodes.length);//initialise list of available nodes
        for (int i = 0; i < nodes.length; i++) //populates the list
        {
            availableSquares.add(i);
        }

        for (int bombsLeft = 0; bombsLeft < bombCount; bombsLeft++) {//randomises position of all the bombs in one pass
            int indexInAvailableSquares = random.nextInt(availableSquares.size());
            nodes[availableSquares.get(indexInAvailableSquares)].nodeContains = Node.NodeContains.BOMB;
            bombNodes[bombsLeft] = nodes[availableSquares.get(indexInAvailableSquares)]; //add node to list of bombs
            availableSquares.remove(indexInAvailableSquares);
        }
    }

    /**
     * <h1>Loops through all bombs and increments the number in connected nodes</h1>
     *
     * @author Erik Broman
     * @since 2021-05-06
     */
    private void PopulateNumbers() {
        for (Node node : bombNodes) {
            for (Edge edge : node.edges) {
                edge.toNode.nodeContains = edge.toNode.nodeContains.Next();
            }
        }
    }
}
