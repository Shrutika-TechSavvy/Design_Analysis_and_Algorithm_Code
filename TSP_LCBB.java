import java.util.*;

public class TSP_LCBB {

    /*
     * INF represents a very large number indicating NO EDGE / Very High Cost.
     * Instead of using Integer.MAX_VALUE (risk of overflow), we use 999999.
     */
    private final int INF = 999999;

    private int n;                      // Total number of cities
    private int[][] cost;               // Cost adjacency matrix
    private int finalCost;              // Best minimum tour cost found so far
    private ArrayList<Integer> finalPath; // Stores best path in order

    // Constructor to initialize variables
    public TSP_LCBB(int[][] cost) {
        this.cost = cost;
        this.n = cost.length;
        this.finalCost = INF;
        this.finalPath = new ArrayList<>();
    }

    /*
     * Finds the minimum cost edge leaving from city i.
     * Example: if edges are [0, 10, 15, 20], then min = 10 (smallest outgoing cost).
     */
    private int firstMin(int[][] cost, int i) {
        int min = INF;
        for (int j = 0; j < n; j++)
            if (i != j && cost[i][j] < min)
                min = cost[i][j];
        return min;
    }

    /*
     * Finds the second minimum outgoing edge from city i.
     * Example: edges [0, 10, 15, 20] → firstMin = 10, secondMin = 15
     * This helps in calculating a better lower bound.
     */
    private int secondMin(int[][] cost, int i) {
        int first = INF, second = INF;
        for (int j = 0; j < n; j++) {
            if (i == j) continue;

            if (cost[i][j] <= first) {
                second = first;
                first = cost[i][j];
            }
            else if (cost[i][j] < second && cost[i][j] != first) {
                second = cost[i][j];
            }
        }
        return second;
    }

    /*
     * Recursive Branch and Bound Function
     * currBound → Current lower bound (best possible future value)
     * currWeight → Cost travelled till now
     * level → depth in the search tree (number of visited cities)
     * currPath → sequence of cities visited so far
     * visited[] → marks cities already visited
     *
     * Goal → Try all possible Hamiltonian paths and prune those
     * which cannot lead to a better solution.
     */
    private void tspRec(int[][] cost, double currBound, double currWeight,
                        int level, ArrayList<Integer> currPath, boolean[] visited) {

        // ✅ BASE CONDITION: All cities visited
        if (level == n) {
            // Must return to city 0 → Complete cycle check
            if (cost[currPath.get(level - 1)][0] != 0) {

                // Calculate final tour cost
                double currRes = currWeight + cost[currPath.get(level - 1)][0];

                // Check if better than current best
                if (currRes < finalCost) {
                    finalCost = (int) currRes;
                    finalPath = new ArrayList<>(currPath);
                    finalPath.add(0); // Return to starting city
                }
            }
            return;
        }

        // Try next unvisited cities one by one
        for (int i = 0; i < n; i++) {

            // Proceed only if path exists and city is not visited
            if (cost[currPath.get(level - 1)][i] != 0 && !visited[i]) {

                double temp = currBound; // Store old bound to restore later

                currWeight += cost[currPath.get(level - 1)][i];

                // Lower bound update logic:
                if (level == 1) {
                    // For the first city, use firstMin of both cities
                    currBound -= (firstMin(cost, currPath.get(level - 1)) +
                                  firstMin(cost, i)) / 2.0;
                } else {
                    // For others: secondMin of previous + firstMin of next
                    currBound -= (secondMin(cost, currPath.get(level - 1)) +
                                  firstMin(cost, i)) / 2.0;
                }

                /*
                 * Pruning:
                 * If currBound + currWeight is already > finalCost → No need to explore that branch
                 */
                if (currBound + currWeight < finalCost) {
                    currPath.add(i);
                    visited[i] = true;

                    tspRec(cost, currBound, currWeight, level + 1, currPath, visited);
                }

                // Undo changes (Backtracking)
                currWeight -= cost[currPath.get(level - 1)][i];
                currBound = temp;
                visited[i] = false;

                if (currPath.size() > level)
                    currPath.remove(level);
            }
        }
    }

    // Function to start the TSP solving process
    public void solve() {
        ArrayList<Integer> currPath = new ArrayList<>();
        boolean[] visited = new boolean[n];
        double currBound = 0;

        /*
         * Initial Lower Bound Calculation:
         * Sum of (firstMin + secondMin) for each city divided by 2
         */
        for (int i = 0; i < n; i++)
            currBound += firstMin(cost, i) + secondMin(cost, i);

        currBound = Math.ceil(currBound / 2.0);

        // Start from city 0
        visited[0] = true;
        currPath.add(0);

        // Begin branch & bound search
        tspRec(cost, currBound, 0, 1, currPath, visited);
    }

    // Display final answer
    public void displayResult() {
        System.out.println("\n========= TSP using Branch and Bound =========");
        if (finalCost == INF) {
            System.out.println("No valid Hamiltonian cycle exists!");
            return;
        }
        System.out.println("Minimum Tour Cost = " + finalCost);
        System.out.println("Path Taken = " + finalPath);
    }

    // MAIN METHOD
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        int n = sc.nextInt();

        int[][] cost = new int[n][n];
        System.out.println("Enter cost matrix (0 means NO direct path):");

        // Taking input cost matrix
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                cost[i][j] = sc.nextInt();

        // ✅ Edge Case 1: Single City — Trivial Answer
        if (n == 1) {
            System.out.println("Only one city exists → No travel needed!");
            System.out.println("Cost = 0, Path = [0]");
            return;
        }

        // ✅ Edge Case 2: Check if graph is disconnected
        boolean disconnected = false;
        for (int i = 0; i < n; i++) {
            boolean pathExists = false;
            for (int j = 0; j < n; j++) {
                if (i != j && cost[i][j] != 0) pathExists = true;
            }
            if (!pathExists) disconnected = true;
        }

        if (disconnected) {
            System.out.println("Graph is disconnected → No valid tour possible!");
            return;
        }

        // ✅ Normal Case Execution
        TSP_LCBB tsp = new TSP_LCBB(cost);
        tsp.solve();
        tsp.displayResult();

        sc.close();
    }
}
