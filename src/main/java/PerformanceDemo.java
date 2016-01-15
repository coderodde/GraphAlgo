import java.util.Random;
import net.coderodde.graph.DirectedGraph;
import net.coderodde.graph.algo.pathfinding.AbstractPathFinder;
import net.coderodde.graph.algo.pathfinding.Path;
import net.coderodde.graph.algo.pathfinding.support.DagShortestPathFinder;

public class PerformanceDemo {

    public static void main(String[] args) {
        profileDagShortestPath();
    }
    
    public static void profileDagShortestPath() {
        final int ARCS = 10;
        final int LAYERS = 3;
        final int MAX_LAYER_SIZE = 4;
        final long seed = 81833393367913L; System.nanoTime();
        Random random = new Random(seed);
        
        System.out.println("Seed = " + seed);
        
        DirectedGraph dag = 
                DemoUtils.createRandomDag(ARCS, 
                                          LAYERS, 
                                          MAX_LAYER_SIZE, 
                                          random);

        AbstractPathFinder finder = new DagShortestPathFinder(dag);
        
        Integer source = random.nextInt(dag.size());
        Integer target = random.nextInt(dag.size());
        
        System.out.println("Source: " + source);
        System.out.println("Target: " + target);
        
        long startTime = System.nanoTime();
        Path path = finder.search(source, target);
        long endTime = System.nanoTime();
        
        System.out.printf("Time elapsed: %.2f milliseconds.\n", 
                          (endTime - startTime) / 1e6);
        System.out.println("Path weight: " + path.getWeight());
    }
}
