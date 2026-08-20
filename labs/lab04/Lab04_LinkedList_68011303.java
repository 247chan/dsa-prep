package labs.lab04;

public class Lab04_LinkedList_68011303 {
    static void expect(String name, Object actual, Object expected) {
        if (!java.util.Objects.equals(actual, expected)) {
            throw new AssertionError(
                name + " expected: " + expected + " but got: " + actual
            );
        }
        System.out.println("PASS " + name);
    }

    static void task_1() {
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

    static void printList(String name, LinkedList_Challenges_68011303.ListNode head) {
        System.out.print(name + ": ");
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println(" -> null");
    }
    static void task_2() {
        LinkedList_Challenges_68011303 sol = new LinkedList_Challenges_68011303();
        LinkedList_Challenges_68011303.ListNode head =
            new LinkedList_Challenges_68011303.ListNode(1,
            new LinkedList_Challenges_68011303.ListNode(2,
            new LinkedList_Challenges_68011303.ListNode(6,
            new LinkedList_Challenges_68011303.ListNode(3,
            new LinkedList_Challenges_68011303.ListNode(6)))));

        printList("before remove 6", head);
        head = sol.removeElements(head, 6); 
        printList("after remove 6", head);
        // before remove 6: 1 -> 2 -> 6 -> 3 -> 6 -> null
        // after remove 6: 1 -> 2 -> 3 -> null
    }
    static void task_3() {
        LinkedList_Challenges_68011303 sol = new LinkedList_Challenges_68011303();

        LinkedList_Challenges_68011303.ListNode head =
            new LinkedList_Challenges_68011303.ListNode(1,
            new LinkedList_Challenges_68011303.ListNode(2,
            new LinkedList_Challenges_68011303.ListNode(3,
            new LinkedList_Challenges_68011303.ListNode(4,
            new LinkedList_Challenges_68011303.ListNode(5)))));

        printList("original", head);

        LinkedList_Challenges_68011303.ListNode copy = sol.deepCopyRightHalf(head);
        printList("copy right half", copy);

        copy.val = 99;
        printList("after changing copy", copy);
        printList("original should not change", head);
        // original: 1 -> 2 -> 3 -> 4 -> 5 -> null
        // copy right half: 3 -> 4 -> 5 -> null
        // after changing copy: 99 -> 4 -> 5 -> null
        // original should not change: 1 -> 2 -> 3 -> 4 -> 5 -> null
    }
    static void task_4() {
        LinkedList_Challenges_68011303 sol = new LinkedList_Challenges_68011303();

        LinkedList_Challenges_68011303.ListNode head =
            new LinkedList_Challenges_68011303.ListNode(1,
            new LinkedList_Challenges_68011303.ListNode(2,
            new LinkedList_Challenges_68011303.ListNode(3)));

        printList("before reverse", head);
        head = sol.reverseList(head);
        printList("after reverse", head);
        // before reverse: 1 -> 2 -> 3 -> null
        // after reverse: 3 -> 2 -> 1 -> null
    }
    public static void main(String[] args) {
        task_1();
        task_2();
        task_3();
        task_4();
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
