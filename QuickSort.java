import java.util.*;

class QuickSort {

    static int partition(List<Integer> arr, int low, int high){
        int pivot = arr.get(low);  // Pivot = first element
        int i = low, j = high;

        while(i < j){
            // Move i forward until we find element > pivot
            while(arr.get(i) <= pivot && i <= high - 1) i++;

            // Move j backward until we find element <= pivot
            while(arr.get(j) > pivot && j >= low + 1) j--;

            if(i < j){
                // Swap arr[i] and arr[j]
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }

        // Swap pivot with arr[j]
        int temp = arr.get(low);
        arr.set(low, arr.get(j));
        arr.set(j, temp);

        return j; // Return pivot index
    }

    static void qs(List<Integer> arr, int low, int high){
        if(low < high){
            int pIndex = partition(arr, low, high);
            
            // Print the array after each partition (iteration)
            System.out.println("Pivot placed at index " + pIndex + ": " + arr);

            // Recursively sort left and right parts
            qs(arr, low , pIndex - 1);
            qs(arr, pIndex + 1, high);
        }
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        // Edge case: No elements
        if(n <= 0){
            System.out.println("Array is empty. Nothing to sort!");
            return;
        }

        List<Integer> arr = new ArrayList<>();
        System.out.println("Enter " + n + " elements:");
        for(int i = 0; i < n; i++){
            arr.add(sc.nextInt());
        }

        System.out.println("Original Array: " + arr);
        qs(arr, 0, n-1);
        System.out.println("Sorted Array: " + arr);
    }
}
