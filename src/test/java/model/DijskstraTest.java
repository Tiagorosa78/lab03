package model;

import com.brunomnsilva.smartgraph.graph.Edge;
import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graph.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static model.Dijkstra.dijkstra;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Os testes unitários deverão, no método setUp, carregar o grafo apresentado no main (copie, as instruções que estão no main para o método setUp).

class DijskstraTest {
    private Graph<String, Weight> graph;

    @BeforeEach
    void setUp() {
        graph = new GraphEdgeList<>();

        Vertex<String> a = graph.insertVertex("A");
        Vertex<String> b = graph.insertVertex("B");
        Vertex<String> c = graph.insertVertex("C");
        Vertex<String> d = graph.insertVertex("D");
        Vertex<String> e = graph.insertVertex("E");
        Vertex<String> f = graph.insertVertex("F");

        graph.insertEdge(a, b, new Weight("A-B",4));
        graph.insertEdge(a, c, new Weight("A-C",5));
        graph.insertEdge(b, c, new Weight("B-C",11));
        graph.insertEdge(b, e, new Weight("B-E",7));
        graph.insertEdge(c, e, new Weight("C-E",3));
        graph.insertEdge(b, d, new Weight("B-D",9));
        graph.insertEdge(f, d, new Weight("F-D",2));
        graph.insertEdge(e, d, new Weight("E-D",13));
        graph.insertEdge(e, f, new Weight("E-F",6));
    }

    @Test
    void getMinimumCostPathBetweenVericesIsOK(){
        Vertex<String> origin = findVertex("C");

        DijkstraResult<String, Weight> result = Dijkstra.dijkstra(graph, origin);

        Vertex<String> destination = findVertex("D");

        Collection<Vertex<String>> path = result.getMinimumCostPathTo(destination);

        assertEquals(4, path.size());

        Object[] pathArray = path.toArray();
        assertEquals("C", ((Vertex<String>) pathArray[0]).element());
        assertEquals("E", ((Vertex<String>) pathArray[1]).element());
        assertEquals("F", ((Vertex<String>) pathArray[2]).element());
        assertEquals("D", ((Vertex<String>) pathArray[3]).element());
    }

    @Test
    public void getMinimumCostPathToSameVerticeThrowsException(){
        Vertex<String> v = findVertex("A");

        DijkstraResult<String, Weight> result = Dijkstra.dijkstra(graph, v);
        assertThrows(NoPathException.class, () -> result.getMinimumCostPathTo(v));
    }

    @Test
    void getMinimumCostIsEqualOnAnyDirection() {
        DijkstraResult<String, Weight> resultAtoF = Dijkstra.dijkstra(graph, findVertex("A"));

        DijkstraResult<String, Weight> resultFtoA = Dijkstra.dijkstra(graph, findVertex("F"));

        double costAtoF = resultAtoF.getMinimumCostTo(findVertex("F"));

        double costFtoA = resultFtoA.getMinimumCostTo(findVertex("A"));

        assertEquals(costAtoF, costFtoA);
    }

    @Test
    void getMinimumCostThrowsExceptionOnIsolatedVertice() {
        Vertex<String> z = graph.insertVertex("Z");

        for (Vertex<String> vertex : graph.vertices()){
            if(!vertex.element().equals("Z")){
                DijkstraResult<String, Weight> result = Dijkstra.dijkstra(graph, vertex);
                assertThrows(NoPathException.class, () -> result.getMinimumCostPathTo(z));
            }
        }
    }

    @Test
    void getShortestPathCostUsingGetMinimumCostToIsOK() {
        DijkstraResult<String, Weight> result = Dijkstra.dijkstra(graph, findVertex("C"), TypeOfCost.SHORTEST_PATH);
        Collection<Vertex<String>> shortestPath = result.getMinimumCostPathTo(findVertex("D"));

        assertEquals(2, shortestPath.size() - 1);
    }

    @Test
    void getMinimumCostPathEdgesTo() {
        graph.insertEdge(findVertex("B"), findVertex("C"), new Weight("B-C other", 1));
        graph.insertEdge(findVertex("E"), findVertex("D"), new Weight("E-D other", 2));

        DijkstraResult<String, Weight> result = Dijkstra.dijkstraEdges(graph, findVertex("C"));

        Vertex<String> destination = findVertex("D");

        Collection<Edge<Weight, String>> pathEdges = result.getMinimumCostPathEdgesTo(destination);

        assertEquals(2, pathEdges.size());
        assertEquals(5, result.getMinimumCostTo(findVertex("D")));
    }

    Vertex<String> findVertex(String element){
        Vertex<String> vertex = graph.vertices()
                .stream()
                .filter(v -> v.element().equals(element))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Vertex not found: " + element));
        return vertex;
    }
}