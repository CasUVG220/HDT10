public class Graph {
    public static final int INF = Integer.MAX_VALUE / 2; // Valor seguro para evitar overflow
    private int[][] adjMatrix;
    private int size;
    private String[] cities;

    public Graph(int size, String[] cities) {
        this.size = size;
        this.cities = cities;
        adjMatrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                adjMatrix[i][j] = (i == j) ? 0 : INF;
            }
        }
    }

    public void addEdge(int from, int to, int weight) {
        if (from >= 0 && from < size && to >= 0 && to < size) {
            adjMatrix[from][to] = weight;
        }
    }

    public void removeEdge(int from, int to) {
        if (from >= 0 && from < size && to >= 0 && to < size) {
            adjMatrix[from][to] = INF;
        }
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    public void printAdjMatrix() {
        System.out.println("\nMatriz de adyacencia:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print((adjMatrix[i][j] == INF ? "∞" : adjMatrix[i][j]) + "\t");
            }
            System.out.println();
        }
    }

    public String getCity(int index) {
        return (index >= 0 && index < cities.length) ? cities[index] : "Inválido";
    }

    public int getCityIndex(String name) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].equalsIgnoreCase(name)) return i;
        }
        return -1;
    }

    public int[][] floydWarshall() {
        int[][] dist = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                dist[i][j] = adjMatrix[i][j];
            }
        }

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF
                        && dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    public int getGraphCenter(int[][] distMatrix) {
        int minEccentricity = INF;
        int centerIndex = -1;

        for (int i = 0; i < size; i++) {
            int eccentricity = 0;
            for (int j = 0; j < size; j++) {
                if (distMatrix[i][j] != INF) {
                    eccentricity = Math.max(eccentricity, distMatrix[i][j]);
                }
            }
            if (eccentricity < minEccentricity) {
                minEccentricity = eccentricity;
                centerIndex = i;
            }
        }

        return centerIndex;
    }
}
