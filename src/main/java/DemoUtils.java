import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.coderodde.graph.DirectedGraph;

public final class DemoUtils {

    public static <T> T choose(List<T> list, Random random) {
        return list.get(random.nextInt(list.size()));
    }
    
    public static DirectedGraph createRandomDag(int arcs, 
                                                int layers,
                                                int maxLayerSize,
                                                Random random) {
        DirectedGraph graph = new DirectedGraph();
        
        List<List<Integer>> layersList = new ArrayList<>(layers);
        int nodeId = 0;
        
        // Create nodes.
        for (int layerIndex = 0; layerIndex < layers; ++layerIndex) {
            int layerSize = random.nextInt(maxLayerSize) + 1;
            List<Integer> layer = new ArrayList<>(layerSize);
            
            for (int i = 0; i < layerSize; ++i) {
                graph.addNode(nodeId);
                layer.add(nodeId++);
            }
            
            layersList.add(layer);
        }
        
        // Create arcs.
        while (arcs > 0) {
            int sourceLayerIndex = random.nextInt(layers);
            int targetLayerIndex = random.nextInt(layers);
//            
//            if (sourceLayerIndex < targetLayerIndex) {
//                Integer tail = choose(layersList.get(sourceLayerIndex), random);
//                Integer head = choose(layersList.get(targetLayerIndex), random);
//                graph.addEdge(tail, head);
//                --arcs;
//            }
            if (targetLayerIndex < sourceLayerIndex) {
                continue;
            } else if (sourceLayerIndex == targetLayerIndex) {
                int sourceNode = choose(layersList.get(sourceLayerIndex), random);
                int targetNode = choose(layersList.get(sourceLayerIndex), random);
                
                if (sourceNode >= targetNode) {
                    continue;
                }
                
                graph.addEdge(sourceNode, targetNode);
            } else {
               Integer tail = choose(layersList.get(sourceLayerIndex), random);
               Integer head = choose(layersList.get(targetLayerIndex), random);
               graph.addEdge(tail, head);
            }
            
            --arcs;
        }
        
        return graph;
    }
}
