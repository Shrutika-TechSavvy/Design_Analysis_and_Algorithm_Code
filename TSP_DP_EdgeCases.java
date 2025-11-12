
import java.util.Arrays;

/*
public class TSP_DP_EdgeCases {

    // INF means no path exists between two cities
    static final long INF = Long.MAX_VALUE / 2;

    public static long tsp(long[][] cost, boolean[] visited, long[][] dp,
                           int current, int count, long costSoFar, int start, int n) {

        //  If all cities visited → try to return to start
        if (count == n) {
            if (cost[current][start] == INF)
                return INF; // cannot return → invalid tour
            return costSoFar + cost[current][start]; // complete cycle
        }

        //  If result already computed for this state
        if (dp[current][count] != -1)
            return dp[current][count];

        long ans = INF;

        //  Try to go to an unvisited city
        for (int next = 0; next < n; next++) {

            if (!visited[next] && cost[current][next] != INF) {
                visited[next] = true;
                long newCost = tsp(cost, visited, dp, next,
                                   count + 1, costSoFar + cost[current][next], start, n);
                ans = Math.min(ans, newCost);
                visited[next] = false; //  backtrack
            }
        }

        return dp[current][count] = ans;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        int n = sc.nextInt();

        //  Edge Case: Invalid city count
        if (n <= 1) {
            System.out.println("TSP not possible! Need at least 2 cities.");
            return;
        }

        long[][] cost = new long[n][n];

        System.out.println("Enter cost matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long c = sc.nextLong();

                //  Handle negative cost edge warning (allowed but risky)
                if (c < 0) {
                    System.out.println("Warning: Negative edges may cause incorrect TSP results.");
                }

                //  Keep diagonal 0 (city to itself)
                if (i == j) {
                    cost[i][j] = INF; // Avoid staying in same city
                } else if (c == 0) {
                    cost[i][j] = INF; // Treat as NO PATH
                } else {
                    cost[i][j] = c;
                }
            }
        }

        boolean[] visited = new boolean[n];
        long[][] dp = new long[n][n];

        //  Initialize DP with -1 (meaning not computed)
        for (long[] row : dp) Arrays.fill(row, -1);

        // Start from city 0
        visited[0] = true;

        //  Start recursion
        long answer = tsp(cost, visited, dp, 0, 1, 0, 0, n);

        //  Final result check
        if (answer >= INF) {
            System.out.println("No Valid Hamiltonian Cycle exists!");
        } else {
            System.out.println("Minimum TSP Cost: " + answer);
        }

        sc.close();
    }
}
 */
//Time Complexity → O(n³)
//Space Complexity → O(n²) (for DP + visited)

/*
=====================================================================
 TEST CASES FOR TSP (Dynamic Programming + Memoization)
=====================================================================

----------------------------------------------------
 Basic Valid Case (Symmetric Graph)
----------------------------------------------------
Input:
4
0 10 15 20
10 0 35 25
15 35 0 30
20 25 30 0

Output:
Minimum TSP Cost: 80


----------------------------------------------------
 Single City (Invalid)
----------------------------------------------------
Input:
1
0

Output:
TSP not possible! Need at least 2 cities.


----------------------------------------------------
 Two Cities (Only one path possible)
----------------------------------------------------
Input:
2
0 10
10 0

Output:
Minimum TSP Cost: 20


----------------------------------------------------
 Unreachable Path in Graph (0 means no edge)
----------------------------------------------------
Input:
4
0 10 0 20
10 0 35 0
0 35 0 30
20 0 30 0

Output:
No Valid Hamiltonian Cycle exists!


----------------------------------------------------
 Zero cost edge (should be handled as no edge)
----------------------------------------------------
Input:
3
0 10 0
10 0 20
0 20 0

Output:
No Valid Hamiltonian Cycle exists!


----------------------------------------------------
Asymmetric Graph (Directed edges)
----------------------------------------------------
Input:
4
0 10 15 20
5 0 9 10
6 13 0 12
8 8 9 0

Output:
Minimum TSP Cost: 33
(Exact may vary depending on path logic)


----------------------------------------------------
Negative Edge Weights (Warning + result)
----------------------------------------------------
Input:
4
0 10 -5 20
10 0 35 25
-5 35 0 30
20 25 30 0

Output:
Warning: Negative edges may cause incorrect TSP results.
Minimum TSP Cost: <some value> (varies due to negatives)


----------------------------------------------------
 Large Values (Check overflow safety)
----------------------------------------------------
Input:
4
0 999999999 888888888 777777777
999999999 0 555555555 666666666
888888888 555555555 0 444444444
777777777 666666666 444444444 0

Output:
Minimum TSP Cost: (fits in long, no overflow)


----------------------------------------------------
 No return path to start city
----------------------------------------------------
Input:
4
0 10 15 20
10 0 35 25
15 35 0 30
0 25 30 0

Output:
No Valid Hamiltonian Cycle exists!


----------------------------------------------------
Repeated equal minimum paths
----------------------------------------------------
Input:
4
0 10 20 10
10 0 10 20
20 10 0 10
10 20 10 0

Output:
Minimum TSP Cost: 40
(Note: multiple valid minimum tours)


----------------------------------------------------
 Extra: Large Input — Performance Check
----------------------------------------------------
Input:
10
<Valid matrix with large values>

Output:
Minimum TSP Cost: <numeric>
(Performance may reduce but should work)

=====================================================================
✔ Copy and keep these for future reference
✔ Use one test case at a time while testing
=====================================================================
 */
class TSP_DP_EdgeCases {

    static int INF = 99999;

    static int tsp(int[][] cost, int[][] dp, int current, int count, int costSoFar, int start, int n, boolean[] visited) {

        //Base case
        int ans = INF;

        if (count == n) {
            if (cost[current][start] != 0) {
                return costSoFar + cost[current][start];
            }
            return INF;
        }

        if (dp[current][count] != -1) {
            return dp[current][count];
        }

        //check everything
        for (int i = 0; i < n; i++) {

            if (!visited[i] && cost[current][i] != 0) {
                visited[i] = true;
                int newCost = tsp(cost, dp, i, count + 1, costSoFar + cost[current][i], start, n, visited);
                ans = Math.min(newCost, ans);
                visited[i] = false;
            }
        }

        return dp[current][count] = ans;
    }

    public static void main(String a[]) {
        //Hardcoded data first

        int[][] cost = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        //Initialize th Dp array
        int[][] dp = new int[4][4];
        boolean[] visited = new boolean[4];

        for (int i = 0; i < 4; i++) {
            Arrays.fill(dp[i], -1);
        }
        visited[0] = true;
        //Start the recursion
        int Resultcost = tsp(cost, dp, 0, 1, 0, 0, 4, visited);

        //Print the result
        System.out.println("Answer : " + Resultcost);
    }
}
