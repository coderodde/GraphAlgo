package net.coderodde.graph.algo.pathfinding.support;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import net.coderodde.graph.AbstractGraph;
import net.coderodde.graph.algo.pathfinding.AbstractPathFinder;
import net.coderodde.graph.algo.pathfinding.Path;
import net.coderodde.util.DaryHeap;
import net.coderodde.util.PriorityQueue;

/**
 * This class implements the Dijkstra's shortest path algorithm.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jan 15, 2016)
 */
public class DijkstraPathFinder extends AbstractPathFinder {

    private final AbstractGraph graph; 
    private PriorityQueue<Integer> heap = new DaryHeap<>();
    
    public DijkstraPathFinder(AbstractGraph graph) {
        this.graph = Objects.requireNonNull(graph, "The input  graph is null.");
    }
    
    public void setPriorityQueue(PriorityQueue<Integer> heap) {
        if (heap != null) {
            this.heap = heap;
        }
    }
    
    @Override
    public Path search(Integer source, Integer target) {
        int size = graph.size();
        PriorityQueue<Integer> OPEN = heap.spawn();
        Set<Integer> CLOSED = new HashSet<>(size);
        Map<Integer, Integer> PARENTS = new HashMap<>(size);
        Map<Integer, Double> DISTANCE = new HashMap<>(size);
        
        OPEN.add(source, 0.0);
        PARENTS.put(source, null);
        DISTANCE.put(source, 0.0);
        
        while (!OPEN.isEmpty()) {
            Integer current = OPEN.extractMinimum();
            
            if (current.equals(target)) {
                return tracebackPath(target, PARENTS, graph);
            }
            
            for (Integer child : graph.getChildrenOf(current)) {
                if (CLOSED.contains(child)) {
                    continue;
                }
                
                double tentativeWeight = DISTANCE.get(current) 
                                       + graph.getEdgeWeight(current, child);
                
                if (!DISTANCE.containsKey(child)
                        || DISTANCE.get(child) > tentativeWeight) {
                    OPEN.add(child, tentativeWeight);
                    DISTANCE.put(child, tentativeWeight);
                    PARENTS.put(child, current);
                }
            }
        }
        
        return Path.emptyPath();
    }
}
