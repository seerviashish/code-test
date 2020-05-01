package javacode;

public class MyList {
    public int size = 0;
    public Node head;
    Node last;

    public void push(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        this.size++;
    }

    public void reverse() {
        Node node = head;
        Node result = null;
        Node next = null;
        while (node != null) {
            next = node.next;
            if (result == null) {
                node.next = null;
            } else {
                node.next = result;
            }
            result = node;
            node = next;
        }
        head = result;
    }

    public void print() {
        Node node = head;
        while (node != null) {
            System.out.print(node.value + " -> ");
            node = node.next;
        }
        System.out.println("null");
    }
}