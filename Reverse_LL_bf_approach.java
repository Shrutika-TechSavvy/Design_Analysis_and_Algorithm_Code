import java.util.Stack;

// //defining the node
 class Node{
     int data;
     Node next;
     Node(int data,Node next){
         this.data=data;
         this.next=next;
     }
     Node(int data){
         this.data=data;
         this.next=null;
     }
 }
 public class Reverse_LL_bf_approach {
     public static Node reverseLinkedList(Node head)
    {
         Stack<Integer> stack=new Stack<>();
         Node temp=head;
    
        
        while(temp!=null)
        {
            stack.push(temp.data);
            temp=temp.next;
        }
        temp=head;
        while(!stack.isEmpty())
        {
            temp.data=stack.pop();
            temp=temp.next;
        }     
        return head;
    }
   
    public static void printList(Node head)
    {
        Node temp=head;
        while(temp!=null)
        {
            System.out.println(temp.data);
            temp=temp.next;
        }
    }
    public static void main(String[] a)
    {
        Node head=new Node(1);
        head.next=new Node(2);
        head.next.next=new Node(3);
        head.next.next.next=new Node(4);

        System.out.println("Original linked list:");
        printList(head);
        Node reversed_list=reverseLinkedList(head);
        System.out.println("Reversed linked list:");
        printList(reversed_list)  ;   
     
}
}