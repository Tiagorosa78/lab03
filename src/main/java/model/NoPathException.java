package model;

import com.brunomnsilva.smartgraph.graph.Vertex;

public class NoPathException extends RuntimeException {
    public NoPathException(String vertexId) {
        super("There is no path to vertex " + vertexId);
    }
}
