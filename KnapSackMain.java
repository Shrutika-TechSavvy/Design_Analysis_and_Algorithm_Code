//Integer knapsack problem 

public class KnapSackMain {

    public static void main(String[] args) {

        //Initially hardcoded data
        int[] wt = {5, 4, 6, 3};
        int[] val = {10, 40, 30, 50};

        int weight = 10;
        int n = wt.length;

        int answ = solve(wt, val, weight, val.length -1 );
		System.out.println("The recursive answer :" + answ);

        // DP table, ofr top down approach
        int[][] t = new int[n + 1][weight + 1];

        // iterative solve
        int ans = solveIterative(wt, val, t, weight, n);
        System.out.println("Iterative DP Answer: " + ans);

        // For memoization, approach
        int[][] dp = new int[n][weight + 1];
        for (int i = 0; i < n; i++) 
        {
            for (int j = 0; j <= weight; j++) 
            {
                dp[i][j] = -1; // initializing with -1
            }
        }

        int ans2 = solveMemo(wt, val, weight, n - 1, dp);
        System.out.println("Memoization Answer: " + ans2);
    }

    //Recursive 
    private static int solve(int[] wt, int[] val, int weight, int n) {

		// TODO Auto-generated method sub

		//Base condition

		if(n< 0 || weight == 0) return 0;
		int include = 0;

		//Include and exclude methods
		if(weight - wt[n] >= 0) 
        {
			include = val[n] + solve(wt, val, weight - wt[n] , n-1);
		}
		int exclude = solve(wt, val, weight , n-1);
		return Math.max(include, exclude);
	}

    // Memoization (Top-Down)
    private static int solveMemo(int[] wt, int[] val, int W, int n, int[][] dp) 
    {
        if (n < 0 || W == 0) return 0;

        if (dp[n][W] != -1) return dp[n][W];

        int include = 0;
        if (W - wt[n] >= 0) 
        {
            include = val[n] + solveMemo(wt, val, W - wt[n], n - 1, dp);
        }
        int exclude = solveMemo(wt, val, W, n - 1, dp);

        return dp[n][W] = Math.max(include, exclude);
    }

    // Iterative DP (Bottom-Up)
    private static int solveIterative(int[] wt, int[] val, int[][] t, int W, int n) 
    {
        for (int i = 0; i <= n; i++) 
        {
            for (int j = 0; j <= W; j++) 
            {
                if (i == 0 || j == 0) 
                {
                    t[i][j] = 0;
                } 
                else if (wt[i - 1] <= j) 
                {
                    // Including 
                    t[i][j] = Math.max(
                        t[i - 1][j],
                        val[i - 1] + t[i - 1][j - wt[i - 1]]
                    );
                } 
                else 
                {
                    //Excluding
                    t[i][j] = t[i - 1][j]; // Exclude
                }
            }
        }
        return t[n][W];
    }    
}
/*
 * 
The recursive answer :90
Iterative DP Answer: 90
Memoization Answer: 90
 */
