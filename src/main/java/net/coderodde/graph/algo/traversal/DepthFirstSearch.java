package net.coderodde.graph.algo.traversal;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import net.coderodde.graph.AbstractGraph;

/**
 * This class implements the depth-first search.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jan 13, 2016)
 */
public class DepthFirstSearch {
   
    public DepthFirstSearch() {
        
    }
    
    
    private DepthFirstSearch(AbstractGraph graph) {
        int size      = graph.size();
        this.graph    = graph;
        colors        = new LinkedHashMap<>(size);
        startTimes    = new LinkedHashMap<>(size);
        endTimes      = new LinkedHashMap<>(size);
        parents       = new LinkedHashMap<>(size);
        nodeStack     = new ArrayDeque<>(size);
        iteratorStack = new ArrayDeque<>(size);
        isAcyclic     = true;
    }
    
    private int                      time;
    private boolean                  isAcyclic;
    private Map<Integer, NodeColor>  colors;
    private Map<Integer, Integer>    startTimes;
    private Map<Integer, Integer>    endTimes;
    private Map<Integer, Integer>    parents;
    private AbstractGraph            graph;
    private Deque<Integer>           nodeStack;
    private Deque<Iterator<Integer>> iteratorStack;
    
    public DepthFirstSearchResult traverseGraph(AbstractGraph graph) {
        Objects.requireNonNull(graph, "The input graph is nul.");
        DepthFirstSearch state = new DepthFirstSearch(graph);
        return state.traverseGraph();
    }
    
    private DepthFirstSearchResult traverseGraph() {
        // Preprocess the graph.
        for (Integer nodeId : graph.getAllNodes()) {
            colors.put(nodeId, NodeColor.WHITE);
            parents.put(nodeId, null);
        }
        
        // Make sure every node is visited, i.e., there is no nodes left
        // with white color.
        for (Integer nodeId : graph.getAllNodes()) {
            if (colors.get(nodeId).equals(NodeColor.WHITE)) {
                nodeStack.addLast(nodeId);
                iteratorStack.addLast(graph.getChildrenOf(nodeId).iterator());
                visit();
            }
        }
        
        return new DepthFirstSearchResult(isAcyclic,
                                          colors,
                                          startTimes,
                                          endTimes,
                                          parents);
    }
    
    private void visit() {
        outer:
        while (!nodeStack.isEmpty()) {
            Integer currentNodeId = nodeStack.getLast();
            Iterator<Integer> currentNodeChildIterator = iteratorStack.getLast();
            
            if (!startTimes.containsKey(currentNodeId)) {
                startTimes.put(currentNodeId, ++time);
            }
                
            colors.put(currentNodeId, NodeColor.GRAY);
            
            while (currentNodeChildIterator.hasNext()) {
                Integer childNodeId = currentNodeChildIterator.next();
                
                if (colors.get(childNodeId).equals(NodeColor.WHITE)) {
                    nodeStack.addLast(childNodeId);
                    iteratorStack.addLast(graph.getChildrenOf(childNodeId).iterator());
                    parents.put(childNodeId, currentNodeId);
                    continue outer;
                } else if (colors.get(childNodeId).equals(NodeColor.GRAY)) {
                    isAcyclic = false;
                }
            }
            
            while (!iteratorStack.isEmpty() && !iteratorStack.getLast().hasNext()) {
                iteratorStack.removeLast();
                Integer nodeId = nodeStack.removeLast();
                endTimes.put(nodeId, ++time);
                colors.put(nodeId, NodeColor.BLACK);
            }
        }
    }
}
