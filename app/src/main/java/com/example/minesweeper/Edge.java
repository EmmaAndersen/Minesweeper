package com.example.minesweeper;

/**
 * <h1>This class represents a connection between two adjacent nodes</h1>
 *
 * @author Erik Broman
 * @version 1.0
 * @since 2021-05-06
 */
public class Edge {
    public final Node fromNode;
    public final Node toNode;

    /**
     * <h1>Constructs the edge between two nodes</h1>
     *
     * @author Erik Broman
     * @since 2021-05-06
     */
    public Edge(Node from, Node to) {
        fromNode = from;
        toNode = to;
    }

}
