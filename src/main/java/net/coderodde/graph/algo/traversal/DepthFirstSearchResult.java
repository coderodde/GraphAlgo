package net.coderodde.graph.algo.traversal;

import java.util.Map;

/**
 * This class holds all result data computed by a depth-first search traversal.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jan 13, 2016)
 */
public class DepthFirstSearchResult {
   
    private final boolean isAcyclic;
    private final Map<Integer, NodeColor> nodeColorMap;
    private final Map<Integer, Integer> startingTimeMap;
    private final Map<Integer, Integer> finishingTimeMap;
    private final Map<Integer, Integer> parentMap;
    
    DepthFirstSearchResult(boolean isAcyclic,
                           Map<Integer, NodeColor> nodeColorMap,
                           Map<Integer, Integer> startingTimeMap,
                           Map<Integer, Integer> finishingTimeMap,
                           Map<Integer, Integer> parentMap) {
        this.isAcyclic        = isAcyclic;
        this.nodeColorMap     = nodeColorMap;
        this.startingTimeMap  = startingTimeMap;
        this.finishingTimeMap = finishingTimeMap;
        this.parentMap        = parentMap;
    }
    
    public boolean isAcyclic() {
        return isAcyclic;
    }
    
    public Map<Integer, NodeColor> getNodeColorMap() {
        return nodeColorMap;
    }
    
    public Map<Integer, Integer> getStartingTimeMap() {
        return startingTimeMap;
    }
    
    public Map<Integer, Integer> getFinishingTimeMap() {
        return finishingTimeMap;
    }
    
    public Map<Integer, Integer> getParentMap() {
        return parentMap;
    }
}
