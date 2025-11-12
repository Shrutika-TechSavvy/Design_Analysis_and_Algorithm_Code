import java.util.*;

public class BinarySearch {
    // Optimized binary search method
    public static int binarySearch(int[] arr, int key) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2; // prevents overflow
            if (arr[mid] == key) {
                return mid; // index found
            } else if (key < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1; // not found
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input array size
        System.out.print("Enter the number of elements in the array (sorted): ");
        int n = sc.nextInt();

        // Input array elements
        int[] arr = new int[n];
        System.out.println("Enter " + n + " sorted elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Input key to search
        System.out.print("Enter the element to search: ");
        int key = sc.nextInt();

        // Perform binary search
        int result = binarySearch(arr, key);

        if (result == -1) {
            System.out.println("No element found!");
        } else {
            System.out.println("Element found at index " + result);
        }
        sc.close();
    }
}
