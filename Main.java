public class Main {
    public static void main(String[] args) {
        String[] cities = {"Ciudad de Guatemala", "Zacapa", "Chiquimula", "Quetzaltenango", "Cobán"};
        Graph graph = new Graph(5, cities);

        // Agregar aristas según la hoja de trabajo
        graph.addEdge(0, 1, 3); // A > B
        graph.addEdge(0, 3, 7); // A > D
        graph.addEdge(1, 2, 1); // B > C
        graph.addEdge(1, 4, 8); // B > E
        graph.addEdge(2, 3, 2); // C > D
        graph.addEdge(3, 4, 3); // D > E
        graph.addEdge(4, 0, 4); // E > A

        // Imprimir matriz (adjacencia)
        graph.printAdjMatrix();

        // Ejecutar Floyd
        int[][] shortestPaths = graph.floydWarshall();

        // Imprimir matriz de caminos más cortos
        System.out.println("\nMatriz de caminos cortos (FloydWarshall):");
        for (int i = 0; i < shortestPaths.length; i++) {
            for (int j = 0; j < shortestPaths[i].length; j++) {
                if (shortestPaths[i][j] == Integer.MAX_VALUE / 2) {
                    System.out.print("∞\t");
                } else {
                    System.out.print(shortestPaths[i][j] + "\t");
                }
            }
            System.out.println();
        }

        // Mostrar el centro del grafo
        int center = graph.getGraphCenter(shortestPaths);
        System.out.println("\nCentro del grafo: " + graph.getCity(center));
    }
}
