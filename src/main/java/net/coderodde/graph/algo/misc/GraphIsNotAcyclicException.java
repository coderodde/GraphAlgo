package net.coderodde.graph.algo.misc;

/**
 * This class implements an exception thrown whenever an acyclic graph is
 * expected and none is available.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jan 15, 2016)
 */
public class GraphIsNotAcyclicException extends RuntimeException {
    
    public GraphIsNotAcyclicException() {
        super("The graph is not acyclic.");
    }
}
