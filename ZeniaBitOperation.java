import java.util.*;

public class ZeniaBitOperation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int N = 1 << n; // 2^n
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        for (int q = 0; q < m; q++) {
            int p = sc.nextInt() - 1; // 0-based index
            int b = sc.nextInt();
            arr[p] = b;

            // Copy original array
            int[] temp = Arrays.copyOf(arr, N);
            boolean useOr = (n % 2 == 1); // true if OR, false if XOR
            int size = N;

            // Iterative reduction
            while (size > 1) {
                for (int i = 0; i < size / 2; i++) {
                    if (useOr) {
                        temp[i] = temp[2 * i] | temp[2 * i + 1];
                    } else {
                        temp[i] = temp[2 * i] ^ temp[2 * i + 1];
                    }
                }
                size /= 2;
                useOr = !useOr;
            }
            System.out.println(temp[0]); // Print the result after reduction
        }
    }
}
