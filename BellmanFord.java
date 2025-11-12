import java.util.*;

class Solution1 {
    static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        int[] dist = new int[V];
        for (int i = 0; i < V; i++) dist[i] = (int)(1e8);
        dist[S] = 0;

        // Relax edges V-1 times
        for (int i = 0; i < V - 1; i++) {
            for (ArrayList<Integer> it : edges) {
                int u = it.get(0);
                int v = it.get(1);
                int wt = it.get(2);
                if (dist[u] != 1e8 && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        // Check for negative weight cycles
        for (ArrayList<Integer> it : edges) {
            int u = it.get(0);
            int v = it.get(1);
            int wt = it.get(2);
            if (dist[u] != 1e8 && dist[u] + wt < dist[v]) {
                return new int[]{-1}; // Negative cycle detected
            }
        }
        return dist;
    }
}

public class BellmanFord{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

        System.out.println("Enter each edge as: <source> <destination> <weight>");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges.add(new ArrayList<>(Arrays.asList(u, v, w)));
        }

        System.out.print("Enter source vertex: ");
        int S = sc.nextInt();

        int[] dist = Solution1.bellman_ford(V, edges, S);

        if (dist.length == 1 && dist[0] == -1) {
            System.out.println("Graph contains a negative weight cycle");
        } else {
            System.out.println("Shortest distances from source vertex " + S + ":");
            for (int i = 0; i < V; i++) {
                System.out.println("Vertex " + i + " : " + dist[i]);
            }
        }

        sc.close();
    }
}
