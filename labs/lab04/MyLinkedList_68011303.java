package labs.lab04;

public class MyLinkedList_68011303 {
    static public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    private ListNode head;
    private int _size;
    public MyLinkedList_68011303() {
        head = null;
    }    
    public MyLinkedList_68011303(int d) {
        head = new ListNode(d);
        _size++;
    }
    public int size() {
        return _size;
    }
    public int getAt(int i) {
        int p = 0;
        ListNode cur = head;
        while (cur != null && p < i) {
            p++;
            cur = cur.next;
        }
        return cur == null ? -1 : cur.val;
    }
    public void setAt(int i, int d) {
        int p = 0;
        ListNode cur = head;
        while (cur != null && p < i) {
            p++;
            cur = cur.next;
        }
        if (cur != null)  cur.val = d;
    } 
    public void addFirst(int d) {
        ListNode n = new ListNode(d);
        n.next = head;
        head = n;
        _size++;
    }
    public void addLast(int d) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode cur = dummy;
        while (cur.next != null) {
            cur = cur.next;
        }

        cur.next = new ListNode(d);
        head = dummy.next;
        _size++;
    }
    public void addLast_direct(int d) {
        ListNode n = new ListNode(d); 
        if (head == null) {
            head = n;   
        } else {
            ListNode cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = n;
        }
        _size++;
    }
    public void add(int i, int d) {
        if (i < 0 || i > _size) return;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode curr = dummy;
        for (int p = 0; p < i; p++) {
            curr = curr.next;
        }

        ListNode n = new ListNode(d);
        n.next =  curr.next;
        curr.next = n;

        head = dummy.next;
        _size++;
    }
    public void insertSort(int d) {
        ListNode dummie = new ListNode(-1);
        dummie.next = head;
        
        ListNode curr = dummie;
        while (curr.next != null && curr.next.val < d) {
            curr = curr.next;
        }
        ListNode n = new ListNode(d);
        n.next = curr.next;
        curr.next = n;

        head = dummie.next;
        _size++;
    }
    public int find(int d) {
        ListNode n = head;
        int index = 0;

        while(n != null){
            if(n.val == d) return index;
            n = n.next;
            index++;
        }
        return -1;
    }
    public boolean isFound(int d) { // does this make sense?
        return find(d) > -1;
    }
    public void delete(int d) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode n = dummy;

        while(n.next != null && n.next.val != d){
            n = n.next;
        }

        if(n.next != null){
            n.next = n.next.next;
            _size--;
        }
        head = dummy.next;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("head");
        ListNode cur = head;

        while (cur != null) {
            sb.append("->(").append(cur.val).append(")");
            cur = cur.next;
        }

        sb.append("->null");
        return sb.toString();
    }

    static void expect(String name, Object actual, Object expected) {
        if (!java.util.Objects.equals(actual, expected)) {
            throw new AssertionError(
                name + " expected: " + expected + " but got: " + actual
            );
        }
        System.out.println("PASS " + name);
    }

    static void test() {
        MyLinkedList_68011303 a = new MyLinkedList_68011303();
        expect("empty", a.toString(), "head->null");
        expect("empty size", a.size(), 0);
        expect("get empty", a.getAt(0), -1);

        a.addFirst(20);
        a.addFirst(10);
        expect("addFirst", a.toString(), "head->(10)->(20)->null");
        expect("addFirst size", a.size(), 2);

        a.addLast(30);
        expect("addLast", a.toString(), "head->(10)->(20)->(30)->null");
        expect("addLast size", a.size(), 3);


        a.add(0, 5);
        expect("add index 0", a.toString(), "head->(5)->(10)->(20)->(30)->null");

        a.add(2, 15);
        expect("add middle", a.toString(), "head->(5)->(10)->(15)->(20)->(30)->null");

        a.add(a.size(), 40);
        expect("add at size", a.toString(), "head->(5)->(10)->(15)->(20)->(30)->(40)->null");

        expect("getAt", a.getAt(2), 15);
        a.setAt(2, 17);
        expect("setAt", a.toString(), "head->(5)->(10)->(17)->(20)->(30)->(40)->null");

        expect("find existing", a.find(20), 3);
        expect("find missing", a.find(99), -1);
        expect("isFound true", a.isFound(30), true);
        expect("isFound false", a.isFound(99), false);

        a.delete(5);
        expect("delete head", a.toString(), "head->(10)->(17)->(20)->(30)->(40)->null");

        a.delete(40);
        expect("delete tail", a.toString(), "head->(10)->(17)->(20)->(30)->null");

        a.delete(17);
        expect("delete middle", a.toString(), "head->(10)->(20)->(30)->null");

        a.delete(99);
        expect("delete missing", a.toString(), "head->(10)->(20)->(30)->null");
        expect("delete size", a.size(), 3);

        MyLinkedList_68011303 b = new MyLinkedList_68011303();
        b.insertSort(30);
        b.insertSort(10);
        b.insertSort(20);
        b.insertSort(5);
        b.insertSort(40);
        expect("insertSort", b.toString(), "head->(5)->(10)->(20)->(30)->(40)->null");
        expect("insertSort size", b.size(), 5);

        MyLinkedList_68011303 c = new MyLinkedList_68011303();
        c.addLast(1);
        expect("addLast empty list", c.toString(), "head->(1)->null");
    }
    public static void main(String[] args) {
        test();
    }
}
// PASS empty
// PASS empty size
// PASS get empty
// PASS addFirst
// PASS addFirst size
// PASS addLast
// PASS addLast size
// PASS add index 0
// PASS add middle
// PASS add at size
// PASS getAt
// PASS setAt
// PASS find existing
// PASS find missing
// PASS isFound true
// PASS isFound false
// PASS delete head
// PASS delete tail
// PASS delete middle
// PASS delete missing
// PASS delete size
// PASS insertSort
// PASS insertSort size
// PASS addLast empty list