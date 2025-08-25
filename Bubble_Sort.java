
import java.util.*;

public class Bubble_Sort {

    public static class ArrayNegativeSizeExeption extends Exception {

        ArrayNegativeSizeExeption(String message) {

            super(message);

        }

    }

    private static int menu() {

        Scanner sc = new Scanner(System.in);

        int decision;

        System.out.println("\n\n\nBubble sort , Following are the ways:");

        System.out.println("1. Ascending \n2. Descending \n3. Exit");

        System.out.println("Enter the choice to sort the array \n");

        decision = sc.nextInt();

        return decision;

    }

    public static void main(String[] args) {
        //int arr[] = {0, 55, 3, 82, 1};   //Array

        Scanner sc = new Scanner(System.in);

        int arr[] = {};

        System.out.println("Enter the no. of elements in array :");

        int n = sc.nextInt();

        try {

            if (n <= 0) {
                throw new ArrayNegativeSizeExeption("Array size can't be negative !!");
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        arr = new int[n];

        for (int i = 0; i < n; i++) {

            arr[i] = sc.nextInt();

        }

        int decision;

        do {

            decision = menu();

            switch (decision) {

                case 1:
                    bubbleSort(arr, n, 1);

                    break;

                case 2:
                    bubbleSort(arr, n, 2);

                    break;

                case 3:
                    break;

                default:

                    System.out.println("This is not the case.");

                    if (decision < 0) {

                        System.out.println("Basically, no. of elements can't be negative");

                        System.out.println("Want to again input the no. of elements: 1(yes) or 2(no) :");

                        int againOrNot = sc.nextInt();

                        if (againOrNot == 1) {
                            decision = 0;
                        } else {
                            decision = 3;
                        }

                    }

            }

        } while (decision != 3);

        sc.close();

    }

    private static void display(int[] arr, int n) {

        for (int i : arr) {

            System.out.print(i + "\t");

        }

    }

    private static void bubbleSort(int[] arr, int n, int ch) {

        int comparisons = 0, swaps = 0;

        boolean isSwap = false;

        int sum = 0;

        for (int i = 0; i < n; i++) {

            swaps = 0;

            System.out.println();

            if (i < n - 1) {
                System.out.println(i + 1 + " iteration");
            }

            for (int j = 0; j < n - i - 1; j++) {

                System.out.println();

                //If the choice of user is ascending order
                if (ch == 1) {

                    comparisons++;

                    if (arr[j] > arr[j + 1]) {

                        int temp = arr[j];

                        arr[j] = arr[j + 1];

                        arr[j + 1] = temp;

                        swaps++;

                        //System.out.println(temp);     //For debugging purpose
                    }

                }//Else if user wants to sort in descending order
                else if (ch == 2) {

                    comparisons++;

                    if (arr[j] < arr[j + 1]) {

                        int temp = arr[j];

                        arr[j] = arr[j + 1];

                        arr[j + 1] = temp;

                        swaps++;

                        //System.out.println(temp);       //For debugging purpose
                    }

                }

                display(arr, n);

            }//j end

            if (swaps == 0) {

                i = n;

            } else {
                sum += swaps;
            }

            System.out.println();

        }

        if (ch == 1) {

            System.out.println("Ascending order  :");
            display(arr, n);
            System.out.println();
            System.out.println("No of comaprisons made: " + comparisons);
            System.out.println("No. of swaps made ; " + sum);

        } else if (ch == 2) {

            System.out.println("Descending order  :");
            display(arr, n);
            System.out.println();
            System.out.println("No of comparisons made: " + comparisons);
            System.out.println("No. of swaps made ; " + sum);

        }

    }

}
