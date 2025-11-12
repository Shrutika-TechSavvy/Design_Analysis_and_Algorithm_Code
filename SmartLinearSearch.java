import java.util.Scanner;

public class SmartLinearSearch {

    // Generic linear search method
    public static <T> void linearSearch(T[] array, T key) {
        int comparisons = 0;
        for (int i = 0; i < array.length; i++) {
            comparisons++;
            if (array[i].equals(key)) {
                System.out.println("Element found at index: " + i);
                System.out.println("Total comparisons made: " + comparisons);
                return;
            }
        }
        System.out.println("Element not found!");
        System.out.println("Total comparisons made: " + comparisons);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = Integer.parseInt(sc.nextLine());

        String[] inputArray = new String[n];
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            inputArray[i] = sc.nextLine();
        }

        System.out.print("Enter element to search: ");
        String keyInput = sc.nextLine();

        // Try to detect type (Integer, Double, Float, Character, or String)
        if (isIntegerArray(inputArray) && isInteger(keyInput)) {
            Integer[] arr = new Integer[n];
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(inputArray[i]);
            linearSearch(arr, Integer.parseInt(keyInput));

        } else if (isDoubleArray(inputArray) && isDouble(keyInput)) {
            Double[] arr = new Double[n];
            for (int i = 0; i < n; i++) arr[i] = Double.parseDouble(inputArray[i]);
            linearSearch(arr, Double.parseDouble(keyInput));

        } else if (isFloatArray(inputArray) && isFloat(keyInput)) {
            Float[] arr = new Float[n];
            for (int i = 0; i < n; i++) arr[i] = Float.parseFloat(inputArray[i]);
            linearSearch(arr, Float.parseFloat(keyInput));

        } else if (isCharArray(inputArray) && keyInput.length() == 1) {
            Character[] arr = new Character[n];
            for (int i = 0; i < n; i++) arr[i] = inputArray[i].charAt(0);
            linearSearch(arr, keyInput.charAt(0));

        } else {
            // Default to String array
            linearSearch(inputArray, keyInput);
        }

        sc.close();
    }

    // Helper methods for type detection
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
