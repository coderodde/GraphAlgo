package net.coderodde.graph.algo.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.coderodde.graph.AbstractGraph;

/**
 * This class defines the API for pathfinding algorithms.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jan 15, 2016)
 */
public abstract class AbstractPathFinder {
    
    /**
     * Searches for a shortest path from {@code source} to {@code target} in 
     * the graph {@code graph}.
     * 
     * @param source the source node.
     * @param target the target node.
     * @return the shortest path.
     */
    public abstract Path search(Integer source, Integer target);
    
    protected Path tracebackPath(Integer target, 
                                 Map<Integer, Integer> parentMap, 
                                 AbstractGraph graph) {
        List<Integer> nodeList = new ArrayList<>();
        Integer current = target;
        
        while (current != null) {
            nodeList.add(current);
            current = parentMap.get(current);
        }
        
        Collections.<Integer>reverse(nodeList);
        return new Path(nodeList, graph);
    }
    
    protected void check(Integer source, Integer target) {
        Objects.requireNonNull(source, "The source node is null.");
        Objects.requireNonNull(target, "The target node is null.");
    }
}
