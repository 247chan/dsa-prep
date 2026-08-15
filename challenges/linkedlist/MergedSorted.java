package challenges.linkedlist;

// Question
// Merge two sorted array using stack
public class MergedSorted {
    public static Node mergeSortedList(Node l1, Node l2){
        Node dummy = new Node(0);
        Node curr = dummy;

        while(l1 != null && l2 != null){
            if(l1.data <= l2.data){
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
    public static void main(String[] args) {
        int[] arr1 = {1,3,5,7,9};
        int[] arr2 = {2,4,6,8,10};
        Node list1 = Node.buildLinkedList(arr1);
        Node list2 = Node.buildLinkedList(arr2);

        Node resultList = mergeSortedList(list1, list2);

        // printing merged sorted linked list
        while(resultList != null){
            System.out.print(resultList.data + " -> ");
            resultList = resultList.next;
        }
        System.out.println("null");
    }
}
