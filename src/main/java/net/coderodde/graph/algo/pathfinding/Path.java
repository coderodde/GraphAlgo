package net.coderodde.graph.algo.pathfinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.coderodde.graph.AbstractGraph;
import net.coderodde.graph.DirectedGraph;

/**
 * This class implements a graph path.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jan 15, 2016)
 */
public class Path {
   
    private static final AbstractGraph EMPTY_GRAPH = new DirectedGraph();
    private static final Path EMPTY_PATH = new Path(new ArrayList<>(), 
                                                    EMPTY_GRAPH);
    
    private final AbstractGraph graph;
    private final List<Integer> nodeList;
    private final double weight;
    
    public Path(List<Integer> nodeList, AbstractGraph graph) {
        this.nodeList = new ArrayList<>(nodeList);
        this.graph = graph;
        
        double w = 0.0;
        
        for (int i = 0; i < nodeList.size() - 1; ++i) {
            w += graph.getEdgeWeight(nodeList.get(i), 
                                     nodeList.get(i + 1));
        }
        
        this.weight = w;
    }
    
    public int size() {
        return nodeList.size();
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Path)) {
            return false;
        }
        
        return nodeList.equals(((Path) o).nodeList);
    }
    
    public static Path emptyPath() {
        return EMPTY_PATH;
    }
    
    public AbstractGraph getGraph() {
        return graph;
    }
    
    public Integer getNode(int index) {
        return nodeList.get(index);
    }
    
    public boolean isEmpty() {
        return nodeList.isEmpty();
    }
    
    public double getWeight() {
        return weight;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int size = nodeList.size();
        
        for (int i = 0; i < size; ++i) {
            sb.append(nodeList.get(i));
            
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        
        return sb.append(']').toString();
    }
}
