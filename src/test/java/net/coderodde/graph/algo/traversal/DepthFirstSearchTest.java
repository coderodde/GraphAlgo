package net.coderodde.graph.algo.traversal;

import java.util.Map;
import net.coderodde.graph.DirectedGraph;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class DepthFirstSearchTest {
    
    @Test
    public void testTraverseGraph() {
        DirectedGraph graph = new DirectedGraph();
        
        int u = 0; 
        int v = 1;
        int w = 2;
        int x = 3;
        int y = 4;
        int z = 5;
        
        graph.addEdge(u, v);
        graph.addEdge(u, x);
        graph.addEdge(x, v);
        graph.addEdge(v, y);
        
        graph.addEdge(y, x);
        graph.addEdge(w, y);
        graph.addEdge(w, z);
        graph.addEdge(z, z);
        
        DepthFirstSearchResult result = new DepthFirstSearch()
                .traverseGraph(graph);
        
        Map<Integer, Integer> start   = result.getStartingTimeMap();
        Map<Integer, Integer> end     = result.getFinishingTimeMap();
        Map<Integer, Integer> parents = result.getParentMap();
        
        assertEquals(1,  (int) start.get(u));
        assertEquals(2,  (int) start.get(v));
        assertEquals(3,  (int) start.get(y));
        assertEquals(4,  (int) start.get(x));
        assertEquals(9,  (int) start.get(w));
        assertEquals(10, (int) start.get(z));
        
        assertEquals(8,  (int) end.get(u));
        assertEquals(7,  (int) end.get(v));
        assertEquals(6,  (int) end.get(y));
        assertEquals(5,  (int) end.get(x));
        assertEquals(12, (int) end.get(w));
        assertEquals(11, (int) end.get(z));
        
        assertEquals(null, parents.get(u));
        assertEquals(u, (int) parents.get(v));
        assertEquals(v, (int) parents.get(y));
        assertEquals(y, (int) parents.get(x));
        
        assertEquals(null, parents.get(w));
        assertEquals(w, (int) parents.get(z));
        
        assertFalse(result.isAcyclic());
        
        graph.clear();
        graph.addEdge(0, 1);
        
        result = new DepthFirstSearch().traverseGraph(graph);
        
        assertTrue(result.isAcyclic());
        
        graph.addEdge(1, 0);
        
        result = new DepthFirstSearch().traverseGraph(graph);
        
        assertFalse(result.isAcyclic());
        
        graph.clear();
        result = new DepthFirstSearch().traverseGraph(graph);
        
        assertTrue(result.isAcyclic());
        
        graph.addNode(0);
        result = new DepthFirstSearch().traverseGraph(graph);
        
        assertTrue(result.isAcyclic());
        
        graph.addEdge(0, 0);
        result = new DepthFirstSearch().traverseGraph(graph);
        
        assertFalse(result.isAcyclic());
    }
    
}
