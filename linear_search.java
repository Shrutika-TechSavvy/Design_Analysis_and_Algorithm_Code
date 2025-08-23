public class linear_search{

    static void searching(int arr[],int key)
    {
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==key)
            {
                System.out.println("Element found at index ");
                break;
            }
            
        }
    }

    public static void main(String a[])
    {
        int arr[]={9,5,2,07,30};
        int key=2;
        searching(arr,key);
    }
}