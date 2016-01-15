import java.util.Random;
import net.coderodde.graph.DirectedGraph;
import net.coderodde.graph.algo.pathfinding.AbstractPathFinder;
import net.coderodde.graph.algo.pathfinding.Path;
import net.coderodde.graph.algo.pathfinding.support.BreadthFirstSearchPathFinder;
import net.coderodde.graph.algo.pathfinding.support.DagShortestPathFinder;

public class PerformanceDemo {

    public static void main(String[] args) {
        profileUnweightedDagShortestPath();
    }
    
    public static void profileUnweightedDagShortestPath() {
        final int ARCS = 1_500_000;
        final int LAYERS = 5_000;
        final int MAX_LAYER_SIZE = 10;
        final long seed = System.nanoTime();
        
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
        Path path1 = finder.search(source, target);
        long endTime = System.nanoTime();
        
        System.out.printf("Time for %s: %.2f milliseconds.\n", 
                           finder.getClass().getSimpleName(),
                          (endTime - startTime) / 1e6);
        System.out.println("Path weight: " + path1.getWeight());
        
        finder = new BreadthFirstSearchPathFinder(dag);
        
        startTime = System.nanoTime();
        Path path2 = finder.search(source, target);
        endTime = System.nanoTime();
        
        System.out.printf("Time for %s: %.2f milliseconds.\n", 
                          finder.getClass().getSimpleName(),
                          (endTime - startTime) / 1e6);
        System.out.println("Path weight: " + path2.getWeight());
        
        System.out.println(path1);
        System.out.println(path2);
        
        System.out.println("Paths of the same length: " +
                           (path1.size() == path2.size()));
    }
}
