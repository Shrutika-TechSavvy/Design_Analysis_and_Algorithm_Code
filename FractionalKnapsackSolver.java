import java.util.*;

class FractionalKnapsackSolver {

    static class Item {
        int value;
        int weight;
        double ratio;

        Item(int v, int w) {
            this.value = v;
            this.weight = w;

            if (w == 0) {
                this.ratio = (v > 0) ? Double.POSITIVE_INFINITY : 0.0;
            } else {
                this.ratio = (double) v / w;
            }
        }
    }

    static double fractionalKnapsack(int[] val, int[] wt, int capacity) {
        int n = val.length;

        // Edge case: No items
        if (n == 0) {
            System.out.println("No items available!");
            return 0.0;
        }

        //  Edge case: Zero or Negative capacity
        if (capacity <= 0) {
            System.out.println(" Capacity must be positive. Max value = 0.0");
            return 0.0;
        }

        List<Item> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            //  Edge case: Negative values/weights
            if (val[i] < 0 || wt[i] < 0) {
                System.out.println(" Negative weights or values are invalid!");
                return 0.0;
            }
            items.add(new Item(val[i], wt[i]));
        }

        //  Sort items by ratio (descending order)
        items.sort((a, b) -> Double.compare(b.ratio, a.ratio));

        double maxValue = 0.0;
        int currCapacity = capacity;

        for (Item item : items) {
            if (currCapacity == 0) break;

            if (item.weight == 0 && item.value > 0) {
                System.out.println(" Zero-weight item (Value: " + item.value + ") added fully!");
                maxValue += item.value;
                continue;
            }

            if (item.weight <= currCapacity) {
                maxValue += item.value;
                currCapacity -= item.weight;
            } else {
                //  Fraction case: capacity < item weight
                System.out.println(" Taking fraction of item with ratio: " + item.ratio);
                maxValue += item.ratio * currCapacity;
                currCapacity = 0;
            }
        }

        return maxValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Invalid number of items!");
            return;
        }

        int[] val = new int[n];
        int[] wt = new int[n];

        System.out.println("Enter item values:");
        for (int i = 0; i < n; i++) val[i] = sc.nextInt();

        System.out.println("Enter item weights:");
        for (int i = 0; i < n; i++) wt[i] = sc.nextInt();

        System.out.print("Enter knapsack capacity: ");
        int cap = sc.nextInt();

        double result = fractionalKnapsack(val, wt, cap);
        System.out.println("✅ Maximum possible knapsack value = " + result);

        sc.close();
    }
}
