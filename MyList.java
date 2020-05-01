
public class MyList {
    int size = 0;
    Node head;
    Node last;

    void link(int e) {
        final Node newNode = new Node(e, null);
        if (head == null) {
            head = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    public void add(int item) {
        link(item);
    }
}