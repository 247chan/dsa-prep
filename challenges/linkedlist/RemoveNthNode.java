package challenges.linkedlist;

// Question
// Remove the nth node from the end
// input: 1 2 3 4 5 6 7
// remove 3rd node from the end
// output: 1 2 3 4 6 7
public class RemoveNthNode {
    public static Node removeNthNodeFromEnd(Node head, int n){
        Node dummy = new Node(0);
        dummy.next = head;
        Node curr = dummy;
        
        for(int i = 0; i < n+1; i++){
            curr = curr.next;
        }

        if(curr != null){
            curr.next = curr.next.next;
        }
        return dummy.next;
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};

        Node list = Node.buildLinkedList(arr);
        
        System.out.println("Before removing Node");
        Node curr = list;
        while(curr != null){
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null\n");

        Node resultList = removeNthNodeFromEnd(list, 3);
        // Expected output = 1 2 3 4 6 7 null

        // printing list
        System.out.println("After removing Node 3 from end");
        while(resultList != null){
            System.out.print(resultList.data + " -> ");
            resultList = resultList.next;
        }
        System.out.println("null");
    }
    
}
