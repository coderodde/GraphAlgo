package net.coderodde.graph.algo.pathfinding.support;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.coderodde.graph.DirectedGraph;
import net.coderodde.graph.algo.misc.TopologicalSort;
import net.coderodde.graph.algo.pathfinding.AbstractPathFinder;
import net.coderodde.graph.algo.pathfinding.Path;
import net.coderodde.graph.algo.traversal.DepthFirstSearch;
import net.coderodde.graph.algo.traversal.DepthFirstSearchResult;

/**
 * This class implements a shortest path finder in directed acyclic graphs.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jan 15, 2016)
 */
public class DagShortestPathFinder extends AbstractPathFinder {

    private List<Integer> nodeList; 
    private final DirectedGraph graph;
    private final Map<Integer, Integer> map = new LinkedHashMap<>();
    
    public DagShortestPathFinder(DirectedGraph graph) {
        Objects.requireNonNull(graph, "The input graph is null.");
        this.graph = graph;
        preprocess();
    }
    
    @Override
    public Path search(Integer source, Integer target) {
        Objects.requireNonNull(source, "The source node is null.");
        Objects.requireNonNull(target, "The target node is null.");
        
        Map<Integer, Double> g       = new HashMap<>();
        Map<Integer, Integer> parents = new HashMap<>();
        
        g.put(source, 0.0);
        parents.put(source, null);
        
        int last = map.get(target);
        
        for (int i = map.get(source); i <= last; ++i) {
            Integer current = nodeList.get(i);
            
            if (!g.containsKey(current)) {
                continue;
            }
            
            if (current.equals(target)) {
                return tracebackPath(target, parents, graph);
            }
            
            for (Integer child : graph.getChildrenOf(current)) {
                if (!g.containsKey(child) 
                        || g.get(child) > g.get(current) + 
                                          graph.getEdgeWeight(current, child)) {
                    g.put(child, g.get(current) + graph.getEdgeWeight(current, 
                                                                      child));
                    parents.put(child, current);
                }
            }
        }
        
        return Path.emptyPath();
    }
    
    private void preprocess() {
        nodeList = new TopologicalSort().sort(graph);
        int nodes = nodeList.size();
        
        for (int i = 0; i < nodes; ++i) {
            map.put(nodeList.get(i), i);
        }
    }
}
