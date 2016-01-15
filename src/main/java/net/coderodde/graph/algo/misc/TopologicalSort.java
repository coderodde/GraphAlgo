package net.coderodde.graph.algo.misc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.coderodde.graph.DirectedGraph;
import net.coderodde.graph.algo.traversal.DepthFirstSearch;
import net.coderodde.graph.algo.traversal.DepthFirstSearchResult;

/**
 * This class implements the topological sort.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jan 15, 2016)
 */
public class TopologicalSort {
   
    /**
     * Computes a topological sort of a directed graph. The order might not be
     * unique.
     * 
     * @param graph the graph to topologically sort.
     * @return the list of nodes in some topological order.
     * @throws GraphIsNotAcyclicException if the input graph is not acyclic.
     */
    public List<Integer> sort(DirectedGraph graph) {
        Objects.requireNonNull(graph, "The input graph is null.");
        DepthFirstSearchResult data = 
                new DepthFirstSearch().traverseGraph(graph);
        
        if (!data.isAcyclic()) {
            throw new GraphIsNotAcyclicException();
        }
        
        List<Integer> ret = new ArrayList<>(graph.getAllNodes());
        Map<Integer, Integer> map = data.getFinishingTimeMap();
        
        Collections.sort(ret, (Integer a, Integer b) -> {
            return Integer.compare(map.get(b), map.get(a));
        });
        
        return ret;
    }
}
