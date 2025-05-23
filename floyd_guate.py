import networkx as nx

# Crear grafo dirigido
G = nx.DiGraph()

# Agregar nodos (opcional, se agregan con las aristas)
G.add_nodes_from(['A', 'B', 'C', 'D', 'E'])

# Agregar aristas con pesos
edges = [
    ('A', 'B', 3),
    ('A', 'D', 7),
    ('B', 'C', 1),
    ('B', 'E', 8),
    ('C', 'D', 2),
    ('D', 'E', 3),
    ('E', 'A', 4)
]
G.add_weighted_edges_from(edges)

# Obtener la matriz con caminos mas cortos
length = dict(nx.floyd_warshall(G))

# Mostrar matriz 
print("\nMatriz de caminos más cortos en Floyd:")
for u in G.nodes():
    for v in G.nodes():
        dist = length[u][v]
        print(f"{dist if dist != float('inf') else '∞':>3}", end="\t")
    print()

# Calcular excentricidades
eccentricities = {}
for node in G.nodes():
    distances = [length[node][v] for v in G.nodes()]
    eccentricities[node] = max(d for d in distances if d != float('inf'))

# Encontrar centro del grafo
center = min(eccentricities, key=eccentricities.get)
print("\nCentro del grafo:", center)
