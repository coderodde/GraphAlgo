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
        
        Map<Integer, Integer> g       = new HashMap<>();
        Map<Integer, Integer> parents = new HashMap<>();
        
        g.put(source, 0);
        parents.put(source, null);
        
        return null;
    }
    
    private void preprocess() {
        DepthFirstSearchResult data = 
                new DepthFirstSearch().traverseGraph(graph);
        List<Integer> topologicalOrder = 
                new TopologicalSort().sort((DirectedGraph) graph);
        
    }
}
