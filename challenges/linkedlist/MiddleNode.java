package challenges.linkedlist;

// Question
// Find the middle node of a linked list
public class MiddleNode {
    public static int findMiddle(Node head){
        if(head == null) return -1;

        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow.data;
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        Node list = Node.buildLinkedList(arr);
        System.out.println("Middle Node : " + findMiddle(list));
    }
}
