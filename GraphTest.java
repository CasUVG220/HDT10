import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphTest {
    private Graph graph;

    @BeforeEach
    public void setup() {
        String[] cities = {"A", "B", "C", "D", "E"};
        graph = new Graph(5, cities);
    }

    @Test
    public void testAddEdgeAndRemoveEdge() {
        graph.addEdge(0, 1, 5); // A -> B
        assertEquals(5, graph.getAdjMatrix()[0][1]);

        graph.removeEdge(0, 1);
        assertEquals(Integer.MAX_VALUE / 2, graph.getAdjMatrix()[0][1]);
    }

    @Test
    public void testFloydWarshallMatrix() {
        graph.addEdge(0, 1, 3); // A -> B
        graph.addEdge(1, 2, 1); // B -> C
        graph.addEdge(2, 3, 2); // C -> D
        graph.addEdge(3, 4, 3); // D -> E

        int[][] result = graph.floydWarshall();
        assertEquals(9, result[0][4]); // A -> B -> C -> D -> E
    }

    @Test
    public void testGraphCenter() {
        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 3, 7);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 4, 8);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 3);
        graph.addEdge(4, 0, 4);

        int[][] result = graph.floydWarshall();
        int center = graph.getGraphCenter(result);

        assertEquals(0, center); // Ciudad A (índice 0) debe ser el centro
    }
}
