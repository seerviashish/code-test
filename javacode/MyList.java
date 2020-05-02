package javacode;

public class MyList {
    public int size = 0;
    public Node head;
    public Node last;

    public void push(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    public int findMiddle() {
        Node fast = head;
        Node slow = head;
        if (head != null) {
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
        }
        return slow.item;
    }

    public void print() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.item + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}