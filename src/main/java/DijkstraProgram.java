import com.brunomnsilva.smartgraph.graph.Edge;
import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graph.Vertex;
import model.DijkstraResult;
import model.Weight;

import java.util.*;

public class DijkstraProgram {
    public static void main(String[] args) {
        Graph<String, Weight> graph = new GraphEdgeList<>();

        Vertex<String> a = graph.insertVertex("A");
        Vertex<String> b = graph.insertVertex("B");
        Vertex<String> c = graph.insertVertex("C");
        Vertex<String> d = graph.insertVertex("D");
        Vertex<String> e = graph.insertVertex("E");
        Vertex<String> f = graph.insertVertex("F");

        graph.insertEdge(a, b, new Weight(4));
        graph.insertEdge(a, c, new Weight(5));
        graph.insertEdge(b, c, new Weight(11));
        graph.insertEdge(b, e, new Weight(7));
        graph.insertEdge(c, e, new Weight(3));
        graph.insertEdge(b, d, new Weight(9));
        graph.insertEdge(f, d, new Weight(2));
        graph.insertEdge(e, d, new Weight(13));
        graph.insertEdge(e, f, new Weight(6));

      System.out.println(graph);
      /*

       DijkstraResult<String, Weight> result = dijkstra(graph, c);
       System.out.println(result);
       System.out.println();
       System.out.printf("Minimum cost path (C -> D): %s with cost %.2f \n",
               result.getMinimumCostPathTo(d),
               result.getMinimumCostTo(d));

      */
    }

    private static DijkstraResult<String, Weight> dijkstra(Graph<String, Weight> g, Vertex<String> origin) {
        List<Vertex<String>> unvisited = new ArrayList<>();
        Map<Vertex<String>, Double> costs = new HashMap<>();
        Map<Vertex<String>, Vertex<String>> predecessors = new HashMap<>();

        // TODO: implement algorithm. Note: Init costs with Double.MAX_VALUE

        return new DijkstraResult<>(origin, costs, predecessors);
    }

    private static Vertex<String> findMinimumCostVertex(Map<Vertex<String>, Double> costs, List<Vertex<String>> unvisited) {
        // If there are any isolated vertices, at some point the vertex that must be chosen
        // is isolated. We safeguard this with (Double.MAX_VALUE < Double.POSITIVE_INFINITY)
        Vertex<String> minCostVertex = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (Vertex<String> u : unvisited) {
            double distance = costs.get(u);
            if(distance < minDistance) {
                minDistance = distance;
                minCostVertex = u;
            }
        }
        return minCostVertex;
    }

}
