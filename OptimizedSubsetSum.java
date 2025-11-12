import java.util.*;

public class OptimizedSubsetSum {

    // Recursive helper function
    public static void findSubsets(int[] arr, int index, int target, List<Integer> current, List<List<Integer>> result) 
    {
        if (target == 0) 
        {  // we have found valid subset
            result.add(new ArrayList<>(current));
            return;
        }

        if (index >= arr.length || target < 0) 
            return;

        // Include the current element
        current.add(arr[index]);
        findSubsets(arr, index + 1, target - arr[index], current, result);

        //Important statement :Doinfg  Backtracking
        current.remove(current.size() - 1);

        // Exclude the current element
        findSubsets(arr, index + 1, target, current, result);
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        if (n <= 0) 
        {
            System.out.println("Array must have at least one element.");
            return;
        }

        int[] arr = new int[n];

        System.out.println("Enter " + n + " positive integers:");
        for (int i = 0; i < n; i++) 
        {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter target sum: ");
        int target = sc.nextInt();

        //Doing Input Validation

        // Case - Empty or invalid input
        if (arr.length == 0) 
        {
            System.out.println(" No valid positive numbers to process.");
            return;
        }

        /* 
        // Case - Remove duplicates
        arr = Arrays.stream(arr).distinct().toArray();
        */

        // Case - Target is negative
        if (target < 0) 
        {
            System.out.println(" Target cannot be negative for this version.");
            return;
        }
        // Case - Target == 0
        if (target == 0) 
        {
            System.out.println(" Target is 0 — subset is: []");
            return;
        }

        // Case - Total sum checks
        //int totalSum = Arrays.stream(arr).sum();
        int totalSum=0;
        for(int i : arr)
        {
            totalSum += i;
        }
        if (totalSum < target) 
        {
            System.out.println("Total sum of array < target — no subsets possible.");
            return;
        }
        
        if (totalSum == target) 
        {
            System.out.println(" The entire array sums to the target: " + Arrays.toString(arr));
            return;
        }

        // Find Subsets 
        List<List<Integer>> result = new ArrayList<>();
        findSubsets(arr, 0, target, new ArrayList<>(), result);

        //  Output 
        if (result.isEmpty()) 
        {
            System.out.println(" No subsets found with sum = " + target);
        } 
        else 
        {
            System.out.println("\n Subsets with sum = " + target + ":");
            for (List<Integer> subset : result) {
                System.out.println(subset);
            }
        }

        sc.close();
    }
}