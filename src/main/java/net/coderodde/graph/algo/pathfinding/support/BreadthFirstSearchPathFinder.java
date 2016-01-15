package net.coderodde.graph.algo.pathfinding.support;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import net.coderodde.graph.AbstractGraph;
import net.coderodde.graph.algo.pathfinding.AbstractPathFinder;
import net.coderodde.graph.algo.pathfinding.Path;

/**
 * This class implements the breadth-first search algorithm.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jan 15, 2016)
 */
public class BreadthFirstSearchPathFinder extends AbstractPathFinder {

    private final AbstractGraph graph;
    
    public BreadthFirstSearchPathFinder(AbstractGraph graph) {
        this.graph = Objects.requireNonNull(graph, "The input graph is null.");
    }
    
    @Override
    public Path search(Integer source, Integer target) {
        check(source, target);
        
        Deque<Integer> queue = new ArrayDeque<>(graph.size());
        Map<Integer, Integer> parents = new HashMap<>(graph.size());
        
        queue.addLast(source);
        parents.put(source, null);
        
        while (!queue.isEmpty()) {
            Integer current = queue.removeFirst();
            
            if (current.equals(target)) {
                return tracebackPath(target, parents, graph);
            }
            
            for (Integer child : graph.getChildrenOf(current)) {
                if (!parents.containsKey(child)) {
                    parents.put(child, current);
                    queue.addLast(child);
                }
            }
        }
        
        return Path.emptyPath();
    }
}
