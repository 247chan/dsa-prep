package challenges.linkedlist;

// base Node class for other linked list challenges
public class Node {
    int data;
    Node next;

    public Node(int d){
        this.data = d;
    }

    public static Node buildLinkedList(int[] arr){
        if(arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node curr = head;
        for(int i = 1; i < arr.length; i++){
            Node n = new Node(arr[i]);
            curr.next = n;
            curr = curr.next;
        }
        return head;
    }
}
