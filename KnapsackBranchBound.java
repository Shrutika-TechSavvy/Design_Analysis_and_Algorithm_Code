import java.util.*;

class Item {
    int value, weight, index;

    Item(int value, int weight, int index) {
        this.value = value;
        this.weight = weight;
        this.index = index;
    }
}
// Node represents a state: which items taken so far
class Node {
    int level;
    int profit;
    int weight;
    double bound;
    List<Integer> takenItems = new ArrayList<>();
}

public class KnapsackBranchBound {

    // Calculate upper bound (best future possible profit)
    static double bound(Node u, int n, int W, Item[] arr) {
        // If already overweight → cannot add anything
        if (u.weight >= W) return 0;

        double profitBound = u.profit;
        int j = u.level + 1;
        int totalWeight = u.weight;

        // Add full items while possible
        while (j < n && totalWeight + arr[j].weight <= W) {
            totalWeight += arr[j].weight;
            profitBound += arr[j].value;
            j++;
        }

        // Add fraction of next item → estimation
        if (j < n) {
            profitBound += (W - totalWeight) * ((double)arr[j].value / arr[j].weight);
        }

        return profitBound;
    }

    static void knapsack(int W, Item[] arr, int n) {

        // Sort by best value/weight ratio -> greedy optimistic bound
        Arrays.sort(arr, (a, b) -> Double.compare(
                (double)b.value / b.weight,
                (double)a.value / a.weight
        ));

        Queue<Node> Q = new LinkedList<>();
        Node u, v = new Node();

        // Start node (no items chosen yet)
        v.level = -1;
        v.profit = 0;
        v.weight = 0;
        v.bound = bound(v, n, W, arr);
        Q.add(v);

        int maxProfit = 0;
        List<Integer> bestItems = new ArrayList<>();

        // BFS traversal of decision tree
        while (!Q.isEmpty()) {
            v = Q.poll();

            // If we reached leaf node → stop exploring this path
            if (v.level == n - 1) continue;

            u = new Node();
            u.level = v.level + 1;

            // ✅ Choice 1: Take item
            u.weight = v.weight + arr[u.level].weight;
            u.profit = v.profit + arr[u.level].value;
            u.takenItems = new ArrayList<>(v.takenItems);
            u.takenItems.add(arr[u.level].index);

            if (u.weight <= W && u.profit > maxProfit) {
                maxProfit = u.profit;
                bestItems = new ArrayList<>(u.takenItems);
            }

            u.bound = bound(u, n, W, arr);
            if (u.bound > maxProfit) Q.add(u);

            // ❌ Choice 2: Skip item
            Node u2 = new Node();
            u2.level = v.level + 1;
            u2.weight = v.weight;
            u2.profit = v.profit;
            u2.takenItems = new ArrayList<>(v.takenItems);
            u2.bound = bound(u2, n, W, arr);

            if (u2.bound > maxProfit) Q.add(u2);
        }

        // ✅ Print result
        System.out.println("\n✅ Maximum Profit = " + maxProfit);
        System.out.println("Items included: " + bestItems);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter knapsack capacity (W): ");
        int W = sc.nextInt();
        System.out.print("Enter number of items (n): ");
        int n = sc.nextInt();

        Item[] arr = new Item[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter value and weight for item " + (i + 1) + ": ");
            arr[i] = new Item(sc.nextInt(), sc.nextInt(), i + 1);
        }

        knapsack(W, arr, n);
        sc.close();
    }
}
