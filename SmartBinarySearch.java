import java.util.*;

class UnsortedArrayException extends Exception {
    public UnsortedArrayException(String msg) {
        super(msg);
    }
}

public class Main {

    // Generic Binary Search Method
    public static <T extends Comparable<T>> void binarySearch(T[] array, T key) throws UnsortedArrayException {
        if (!isSorted(array)) {
            throw new UnsortedArrayException("The array is not sorted! Please provide a sorted array.");
        }

        boolean ascending = array[0].compareTo(array[array.length - 1]) < 0;
        int comparisons = 0;
        int low = 0, high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // ✅ Correct mid formula
            comparisons++;
            int cmp = key.compareTo(array[mid]);

            if (cmp == 0) {
                System.out.println("✅ Element found at index: " + mid);
                System.out.println("Total comparisons made: " + comparisons);
                return;
            }

            if (ascending) {
                if (cmp < 0) high = mid - 1;
                else low = mid + 1;
            } else {
                if (cmp > 0) high = mid - 1;
                else low = mid + 1;
            }
        }

        System.out.println("❌ Element not found!");
        System.out.println("Total comparisons made: " + comparisons);
    }

    // Check if the array is sorted (ascending or descending)
    public static <T extends Comparable<T>> boolean isSorted(T[] arr) {
        boolean asc = true, desc = true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[i - 1]) < 0) asc = false;
            if (arr[i].compareTo(arr[i - 1]) > 0) desc = false;
        }
        return asc || desc;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = Integer.parseInt(sc.nextLine());

        String[] inputArray = new String[n];
        System.out.println("Enter " + n + " elements (sorted order):");
        for (int i = 0; i < n; i++) inputArray[i] = sc.nextLine();

        System.out.print("Enter element to search: ");
        String keyInput = sc.nextLine();

        try {
            if (isIntegerArray(inputArray) && isInteger(keyInput)) {
                Integer[] arr = new Integer[n];
                for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(inputArray[i]);
                binarySearch(arr, Integer.parseInt(keyInput));

            } else if (isDoubleArray(inputArray) && isDouble(keyInput)) {
                Double[] arr = new Double[n];
                for (int i = 0; i < n; i++) arr[i] = Double.parseDouble(inputArray[i]);
                binarySearch(arr, Double.parseDouble(keyInput));

            } else if (isFloatArray(inputArray) && isFloat(keyInput)) {
                Float[] arr = new Float[n];
                for (int i = 0; i < n; i++) arr[i] = Float.parseFloat(inputArray[i]);
                binarySearch(arr, Float.parseFloat(keyInput));

            } else if (isCharArray(inputArray) && keyInput.length() == 1) {
                Character[] arr = new Character[n];
                for (int i = 0; i < n; i++) arr[i] = inputArray[i].charAt(0);
                binarySearch(arr, keyInput.charAt(0));

            } else {
                binarySearch(inputArray, keyInput);
            }

        } catch (UnsortedArrayException e) {
            System.out.println("⚠️ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠️ Error: " + e.getMessage());
        }

        sc.close();
    }

    // Type Detection Helpers
    private static boolean isIntegerArray(String[] arr) {
        for (String s : arr) if (!isInteger(s)) return false;
        return true;
    }

    private static boolean isFloatArray(String[] arr) {
        for (String s : arr) if (!isFloat(s)) return false;
        return true;
    }

    private static boolean isDoubleArray(String[] arr) {
        for (String s : arr) if (!isDouble(s)) return false;
        return true;
    }

    private static boolean isCharArray(String[] arr) {
        for (String s : arr) if (s.length() != 1) return false;
        return true;
    }

    private static boolean isInteger(String s) {
        try { Integer.parseInt(s); return true; } catch (Exception e) { return false; }
    }

    private static boolean isFloat(String s) {
        try { Float.parseFloat(s); return true; } catch (Exception e) { return false; }
    }

    private static boolean isDouble(String s) {
        try { Double.parseDouble(s); return true; } catch (Exception e) { return false; }
    }
}
