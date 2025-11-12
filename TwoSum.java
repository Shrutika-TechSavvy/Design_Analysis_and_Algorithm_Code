
import java.util.*;

public class TwoSum {

    public static void main(String r[]) {
        int arr[] = {2, 7, 11, 15};
        int target = 10;
        boolean found = false;

        //Basic two sum, where we need to print true or false such that the pair exists
        //Sum and complement method

        /*HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            int diff = target - arr[i];
            if( !map.containsKey(diff)){
                map.put(arr[i] , i);
            }
            else {
                System.out.println("True");
                found = true;
                break;
            }
            

        }
        if(!found) System.out.println("FAlse");

         */
        //Now the no. of pairs and sorted according to 1st. If 1st equals then sort by 2nd
        //Duplicates are not allowed , it should be unique
        TreeSet<int[]> pairs = new TreeSet<>((p1, p2) -> {
            if (p1[0] != p2[0]) {
                return Integer.compare(p1[0], p2[0]); // compare first
            }return Integer.compare(p1[1], p2[1]); // compare second
        });

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int diff = target - arr[i];

            if (map.containsKey(diff)) {
                int a = Math.min(diff, arr[i]);
                int b = Math.max(diff, arr[i]);
                pairs.add(new int[]{a, b});
            } else {
                map.put(arr[i], i);
            }
        }

        for (int[] p : pairs) {
            System.out.println(p[0] + " " + p[1]);
        }

    }

}
