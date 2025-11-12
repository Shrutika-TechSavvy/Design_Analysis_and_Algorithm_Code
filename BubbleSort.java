import java.util.*;

public class BubbleSort {

    // Creating the Custom exception for invalid array size
    public static class ArrayNegativeSizeException extends Exception {
        ArrayNegativeSizeException(String message) {
            super(message);
        }
    }

    // Display menu and get user's choice
    private static int menu(Scanner sc) {
        System.out.println("\nBubble sort - Choose sorting order:");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        System.out.println("3. Exit");
        System.out.print("Enter the choice to sort the array: ");
        return sc.nextInt();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;

        // Input array size with exception handling
        while (true) {
            System.out.print("Enter the number of elements in array: ");
            n = sc.nextInt();
            try {
                if (n <= 0) {
                    throw new ArrayNegativeSizeException("Array size must be positive!");
                }
                break;
            } catch (ArrayNegativeSizeException e) {
                System.out.println(e.getMessage());
            }
        }

        // Input array elements
        int[] arr = new int[n];
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Main menu loop
        int decision;
        do {
            decision = menu(sc);
            switch (decision) {
                case 1:
                    bubbleSort(arr.clone(), n, 1); // Sort a copy to preserve original array
                    break;
                case 2:
                    bubbleSort(arr.clone(), n, 2);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (decision != 3);

        sc.close();
    }

    // Utility to display array
    private static void display(int[] arr) {
        for (int value : arr) {
            System.out.print(value + "\t");
        }
        System.out.println();
    }

    // Bubble sort implementation with step-wise display
    private static void bubbleSort(int[] arr, int n, int order) {
        int comparisons = 0, totalSwaps = 0;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            System.out.println("\nIteration " + (i + 1) + ":");
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                boolean condition = (order == 1) ? (arr[j] > arr[j + 1]) : (arr[j] < arr[j + 1]);
                if (condition) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    totalSwaps++;
                    swapped = true;
                }
                display(arr);
            }
            if (!swapped) break; // Array already sorted
        }

        if (order == 1) {
            System.out.println("\nSorted in Ascending order:");
        } else {
            System.out.println("\nSorted in Descending order:");
        }
        display(arr);
        System.out.println("Total comparisons: " + comparisons);
        System.out.println("Total swaps: " + totalSwaps);
    }
}
