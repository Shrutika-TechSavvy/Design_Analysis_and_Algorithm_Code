import java.util.*;



class MainPrims {

    // Pair structure (weight, node)
    static class Pair {
        int weight, node;
        Pair(int w, int n) {
            weight = w;
            node = n;
        }
    }

    public static void prims(int V, List<List<int[]>> adj) {
        boolean[] isMST = new boolean[V];

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        pq.add(new Pair(0, 0)); // start from node 0

        int cost = 0;

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int wt = cur.weight;
            int node = cur.node;

            if (!isMST[node]) {
                isMST[node] = true;
                cost += wt;

                // explore neighbours
                for (int[] edge : adj.get(node)) {
                    int adjNode = edge[0];
                    int edgeWt = edge[1];

                    if (!isMST[adjNode]) {
                        pq.add(new Pair(edgeWt, adjNode));
                    }
                }
            }
        }

        System.out.println("Total cost of MST = " + cost);
    }

    public static void main(String[] args) {
        int V = 9; // 9 vertices (0 to 8)
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // adding edges (undirected)
        adj.get(0).add(new int[]{1, 4});
        adj.get(1).add(new int[]{0, 4});

        adj.get(0).add(new int[]{7, 8});
        adj.get(7).add(new int[]{0, 8});

        adj.get(1).add(new int[]{2, 8});
        adj.get(2).add(new int[]{1, 8});

        adj.get(1).add(new int[]{7, 11});
        adj.get(7).add(new int[]{1, 11});

        adj.get(2).add(new int[]{3, 7});
        adj.get(3).add(new int[]{2, 7});

        adj.get(2).add(new int[]{8, 2});
        adj.get(8).add(new int[]{2, 2});

        adj.get(2).add(new int[]{5, 4});
        adj.get(5).add(new int[]{2, 4});

        adj.get(3).add(new int[]{4, 9});
        adj.get(4).add(new int[]{3, 9});

        adj.get(3).add(new int[]{5, 14});
        adj.get(5).add(new int[]{3, 14});

        adj.get(4).add(new int[]{5, 10});
        adj.get(5).add(new int[]{4, 10});

        adj.get(5).add(new int[]{6, 2});
        adj.get(6).add(new int[]{5, 2});

        adj.get(6).add(new int[]{7, 1});
        adj.get(7).add(new int[]{6, 1});

        adj.get(6).add(new int[]{8, 6});
        adj.get(8).add(new int[]{6, 6});

        adj.get(7).add(new int[]{8, 7});
        adj.get(8).add(new int[]{7, 7});

        prims(V, adj);
    }
}