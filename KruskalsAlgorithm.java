import java.util.*;

class Edge implements Comparable<Edge>{
    int src, dest, weight;

    public Edge(int src, int dest, int weight){
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }


    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
    
}

class KruskalsAlgorithm {

    //Find function with path compression
    //The find function tells you which group an element belongs to.
    static int find(int parent[] , int v){
        if(parent[v] != v){
            parent[v] = find(parent, parent[v]);
        }
        return parent[v];
    }

    //union function (union by rank )

    //The union function joins two sets into one.
    static void union(int[] parent, int[] rank, int u, int v){
        int x = find(parent, u);
        int y = find(parent, v);

        if(x != y){
            if(rank[x] < rank[v]){
                parent[x] = y;
            }
            else if(rank[x] > rank[y]){
                parent[y] = x;
            }
            else{
                parent[y] = x;
                rank[x] ++;
            }

        }
    }

    static void kruskalMST(int V, List<Edge> edges){
        //Sort the edges by the weights;
        Collections.sort(edges);

        int[] parent = new int[V];
        int[] rank = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i; rank[i] = 0; 
            }
        List<Edge> mst = new ArrayList<>(); 
        int mstWeight = 0;

        for (Edge edge : edges) {
            int u = find(parent, edge.src);
            int v = find(parent, edge.dest);

            //If they belong to different set then include this edge
            //Merge these set now

            if(u != v){
                mst.add(edge);
                mstWeight+=edge.weight;
                union(parent, rank, u, v);
            }

        }

        // Printing the result 
        System.out.println("Edges in MST:"); 
        for (Edge e : mst) { 
            System.out.println(e.src + " -- " + e.dest + " == " + e.weight); 
        } System.out.println("Total weight of MST = " + mstWeight); 
    }

    public static void main(String[] args) { 
        int V = 4; // number of vertices 
        List<Edge> edges = new ArrayList<>(); 
        // Graph edges 
        edges.add(new Edge(0, 1, 10)); 
        edges.add(new Edge(0, 2, 6)); 
        edges.add(new Edge(0, 3, 5)); 
        edges.add(new Edge(1, 3, 15)); 
        edges.add(new Edge(2, 3, 4)); 
        kruskalMST(V, edges); 
    }
    
    
}
