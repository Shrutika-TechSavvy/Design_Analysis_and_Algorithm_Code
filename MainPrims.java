
import java.util.*;

class MainPrims {

    // Pair class -> stores (weight, node)
    static class Pair {

        int weight, node;

        Pair(int w, int n) {
            weight = w;
            node = n;
        }
    }

    // Prim’s Algorithm function
    public static void prims(int V, List<List<int[]>> adj) {

        boolean[] inMST = new boolean[V]; // Track nodes that need ti be included in MST
        int[] parent = new int[V];        // Track MST parent of each node
        int[] key = new int[V];           // Minimum weight edge for each node

        Arrays.fill(key, Integer.MAX_VALUE); // Initialize all weights as infinity
        Arrays.fill(parent, -1); // No parent initially

        // Priority Queue to always picks smallest edge weight
        PriorityQueue<Pair> pq
                = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));

        // Start from node 0
        key[0] = 0;
        pq.add(new Pair(0, 0));

        int count = 0; // Count of nodes included in MST

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int node = current.node;

            // If already included or visited, skip
            if (inMST[node]) {
                continue;
            }

            inMST[node] = true;
            count++; // One more node added to our MST

            // Explore neighbours
            for (int[] edge : adj.get(node)) {
                int adjNode = edge[0];
                int weight = edge[1];

                // If not yet in MST and found a smaller edge
                if (!inMST[adjNode] && weight < key[adjNode]) {
                    parent[adjNode] = node;
                    key[adjNode] = weight;
                    pq.add(new Pair(weight, adjNode));
                }
            }
        }

        // If not all vertices are included -> graph is disconnected
        if (count != V) {
            System.out.println("\nGraph is DISCONNECTED!");
            System.out.println("MST cannot be formed for all vertices.");
            return;
        }

        //  Print MST edges
        int totalCost = 0;
        System.out.println("\nEdges in the Minimum Spanning Tree:");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + " (Weight: " + key[i] + ")");
            totalCost += key[i];
        }

        System.out.println("Total cost of MST = " + totalCost);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //  Validating number of vertices ie. of the no of vertices are 0 or less than zero, no graph can exists
        int V;
        while (true) {
            System.out.print("Enter number of vertices (minimum 3): ");
            V = sc.nextInt();
            if (V >= 3) {
                break;
            }
            System.out.println("Error: Graph must contain at least 3 vertices!");
        }

        // Validate number of edges
        int E;
        while (true) {
            System.out.print("Enter number of edges: ");
            E = sc.nextInt();

            int maxEdges = V * (V - 1) / 2; // Maximum edges in simple graph

            if (E < 0) {
                System.out.println("Edges must be >= 0!");
            } else if (E > maxEdges) {
                System.out.println("Too many edges! For " + V + " vertices, max allowed = " + maxEdges);
            } else {
                break; // valid
            }
        }

        // Create adjacency list for graph
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        System.out.println("Enter edges in format: u v w");
        System.out.println("(Where u and v are vertices [0 to " + (V - 1) + "] and w is weight)");

        //  Read edges the edges from the user dynamically
        for (int i = 0; i < E; i++) {
            int u, v, w;

            while (true) {
                u = sc.nextInt();
                v = sc.nextInt();
                w = sc.nextInt();

                if (u < 0 || v < 0 || u >= V || v >= V) {
                    System.out.println("Invalid vertices! Must be between 0 and " + (V - 1));
                    continue;
                }
                if (u == v) {
                    System.out.println("Self-loops are not allowed. Enter again:");
                    continue;
                }
                break;
            }

            // Adding undirected edge because the MST is applied to the undirected graphs so edge from both the sides of entered vertice
            adj.get(u).add(new int[]{v, w});
            adj.get(v).add(new int[]{u, w});
        }

        //  Executing the Prim’s Algorithm
        prims(V, adj);

        sc.close();
    }
}
