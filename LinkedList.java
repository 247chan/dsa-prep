class Node {
    int data;
    Node next;

    public Node(int data){
        this.data = data;
        this.next = null;
    }
}

public class LinkedList {
    Node head;

    public LinkedList(){
        head = null;
    }

    public void addLast(int data){
        Node newNode = new Node(data);

        if(head == null){
            head = newNode;
            return;
        }
        Node curr = head;
        while(curr.next != null){
            curr = curr.next;
        }
        curr.next = newNode;
    }

    public void addFirst(int data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public void printList(){
        Node curr = head;
        while(curr != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    public boolean contains_data(int data){
        Node curr = head;
        while (curr != null){
            if(curr.data == data){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    // delete a value (first occurrence)
    public void delete(int data){
        System.out.println("Deleting first occurrence of " + data);
        if(head == null) return;
        if(head.data == data){
            head = head.next;
            return;
        }

        Node curr = head;
        while(curr.next != null && curr.next.data != data){
            curr = curr.next;
        }
        if(curr.next != null && curr.next.data == data){
            curr.next = curr.next.next;
        }
    }

    public void reverse(){
        System.out.println("Reversing linked list");
        Node prev = null;
        Node curr = head;
        while(curr != null){
            Node nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        head = prev;
    }
    
    public static void main(String[] args) {
        System.out.println("Linked List");
        System.out.println("=======================");
        LinkedList list = new LinkedList();
        list.addLast(1);
        list.printList();
        list.addLast(3);
        list.addLast(2);
        list.addLast(3);
        list.addFirst(5);
        list.printList();
        list.delete(3);
        list.printList();
        list.reverse();
        list.printList();
    }
}
