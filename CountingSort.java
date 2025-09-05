
class CountingSort{
    
    public static void main(String[] args)
    {
        //Hardcoded data initially
        int[] array = {9, 7, 2, 8, 5, 2, 9, 0};
        array = sortingAlgorithm(array);
        display(array);
       
    }

    private static int[] sortingAlgorithm(int[] arr) {

        /*It sorts integers by counting how many times each value occurs, then writing values back in order of their numeric value (not by comparing elements). */
        //Step 1:find the max elemnt from the array
        int max = arr[0];
        for(int i : arr){
            max = Math.max(max, i);
        }

        //Step 2: Create the counting array
        int countArray[]  = new int[max + 1]; 
        /*Why max + 1?
        Arrays are 0-indexed. If the maximum value is 9, you must be able to store the count at countArray[9]. That means the array needs indices 0..9 → length 10 → max + 1. */
        
        //Step 3: Count the occcurences of each no. in given array ie. Each index i will hold how many times did value i appear?

        for(int i = 0; i< arr.length; i++){
            int num = arr[i];
            countArray[num]+=1;
        }
        display(countArray);

        //Step 4: Put the counts in the array
        int result[]= new int[arr.length];
        int count=0;
        for(int i = 0; i<max+1 ; i++){
            int num = countArray[i];
            if(num == 0) continue;
            while(num > 0){
                result[count++] = i;
                num--;
            }
        }
        return result;
    }

    private static void display(int[] array) {
        System.out.println(" ");
        for(int i : array){
            System.out.print("\t"+i);
        }

        
    }

}

/*
 * Let n = number of elements, k = max + 1
 * Find max: O(n)
 * Count occurrences: O(n)
 * Reconstruct:
 * -Outer loop over values: O(k)
 * -Inner while runs once per element overall: O(n)
 * 
 * Total time: O(n + k) (best/avg/worst all the same).
 * Extra space: O(k) for the counting array + O(n) for the result array (counting sort typically uses an auxiliary output array).
 */