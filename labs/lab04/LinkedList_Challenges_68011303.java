package labs.lab04;

public class LinkedList_Challenges_68011303 {
    static public class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int val) { this.val = val; }
        public ListNode(int val, ListNode n) {this.val = val; this.next = n;}
    }    

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode n = dummy;

        while(n.next != null){
            if(n.next.val == val){
                n.next = n.next.next;
            } else {
                n = n.next;
            }
        }
        
        head = dummy.next;
        return head;
    }
    public ListNode deepCopyRightHalf(ListNode head) { 
        if (head == null) return null;

        int size = 0;
        int start_index = 0;
        ListNode cur = head;

        while(cur != null){
            size++;
            cur = cur.next;
        }

        start_index = size / 2;

        cur = head;
        for(int i = 0; i < start_index; i++){
            cur = cur.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode copyHead = dummy;

        while(cur != null){
            copyHead.next = new ListNode(cur.val);
            copyHead = copyHead.next;
            cur = cur.next;
        }

        copyHead = dummy.next;
        
        return copyHead;
    }
    public ListNode reverseList(ListNode head) {
        ListNode cur = head, prev = null, next = null;

        while(cur != null){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        head = prev;
        
        ListNode n = head;
       
        return n;
    }
}
