public class binary_search {
    public static void searching(int[] arr, int key)
    {
        int low=0,high=arr.length-1;
        int mid=0,flag=0;

        while(low<=high)
        {
            mid=(low+high)/2;
            if(arr[mid]==key)
            {
                System.out.println("Element found at index "+(mid+1));
                flag=1;
            }
            if(key<arr[mid])
            {
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        if(flag==0) System.out.println("No element found!!");
    }
    public static void main(String ar[])
    {
        int a[]={10,20,30,40,50,60};
        int key=100;
        searching(a,key);
    }
}
