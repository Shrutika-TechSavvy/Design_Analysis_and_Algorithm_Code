import java.util.*;

class Edge implements Comparable<Edge> {

    int src, dest;
    long weight;

    public Edge(int src, int dest, long weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Long.compare(this.weight, o.weight);
    }
}

public class KruskalsDynamicInput {

    static int find(int[] parent, int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = find(parent, parent[vertex]);
        }
        return parent[vertex];
    }

    static void union(int[] parent, int[] rank, int u, int v) {
        int rootU = find(parent, u);
        int rootV = find(parent, v);

        if (rootU != rootV) {
            if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else if (rank[rootV] < rank[rootU]) {
                parent[rootV] = rootU;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
        }
    }

    static void kruskalMST(int V, List<Edge> edges) {

        System.out.println("\n=== Kruskal's MST Algorithm ===");

        //  Edge case: Invalid vertices
        if (V <= 0) {
            System.out.println(" Invalid graph! Number of vertices must be ≥ 1");
            return;
        }

        // Edge case: No edges
        if (edges.size() == 0) {
            System.out.println("No edges → MST weight = 0");
            return;
        }

        //  Remove self-loops (always create cycles)
        edges.removeIf(e -> e.src == e.dest);

        Collections.sort(edges); // Greedy sorting

        int[] parent = new int[V];
        int[] rank = new int[V];
        List<Edge> mst = new ArrayList<>();
        long mstWeight = 0;

        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        for (Edge edge : edges) {

            //  Validate edge vertices
            if (edge.src < 0 || edge.src >= V || edge.dest < 0 || edge.dest >= V) {
                System.out.println("Skipping invalid edge: "
                        + edge.src + " -- " + edge.dest + " (Invalid range)");
                continue;
            }

            int rootU = find(parent, edge.src);
            int rootV = find(parent, edge.dest);

            if (rootU != rootV) {
                mst.add(edge);
                mstWeight += edge.weight;
                union(parent, rank, rootU, rootV);
            }
        }

        // Check if MST is complete
        if (mst.size() != V - 1) {
            System.out.println("\n Graph is DISCONNECTED → MST cannot be formed!");
            System.out.println("Only " + mst.size() + " edges added out of required " + (V - 1));
            return;
        }

        System.out.println("\n MST Edges:");
        for (Edge e : mst) {
            System.out.println(e.src + " -- " + e.dest + " == " + e.weight);
        }

        System.out.println(" Minimum Cost of MST = " + mstWeight);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        //  Validation: E should not exceed maximum possible edges in simple graph
        long maxEdges = (long) V * (V - 1) / 2;
        if (E > maxEdges) {
            System.out.println("⚠ Warning: Too many edges for a simple graph (" + maxEdges + " max)");
            System.out.println("   Duplicate edges might be present. Proceeding...");
        }

        List<Edge> edges = new ArrayList<>();

        System.out.println("\nEnter edges (src dest weight):");
        for (int i = 0; i < E; i++) {
            int s = sc.nextInt();
            int d = sc.nextInt();
            long w = sc.nextLong();

            //  Prevent self loops at input stage
            if (s == d) {
                System.out.println("Skipping self-loop edge: " + s + " -- " + d);
                continue;
            }

            //  Prevent invalid vertices
            if (s < 0 || s >= V || d < 0 || d >= V) {
                System.out.println("Skipping invalid edge: " + s + " -- " + d + " (invalid vertex index)");
                continue;
            }

            edges.add(new Edge(s, d, w));
        }

        kruskalMST(V, edges);

        sc.close();
    }
}
