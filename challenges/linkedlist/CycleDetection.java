package challenges.linkedlist;

public class CycleDetection {
    public static boolean hasCycle(Node head){
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        Node list = Node.buildLinkedList(arr);
        System.out.println("has Cycle : " + hasCycle(list));
    }
}
