package topic.linkedlist;

public class P2 {

    static class MyLinkedList {
        Node root = null;

        public void addValue(int value) {
            Node node = new Node(value);
            node.node = this.root;
            this.root = node;
        }

        public void deleteNode(Node node) {
            Node temp = node.node;
            node.value = temp.value;
            node.node = temp.node;
        }
    }

    static class Node {
        int value;
        Node node;

        public Node(int value) {
            this.value = value;
            this.node = null;
        }
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        int[] arr = { 1, 2, 3, 4, 5, 6 };
        for (int i = 0; i < arr.length; i++) {
            list.addValue(arr[i]);
        }
        int ind = 0;
        Node root = list.root;
        while (ind < 2) {
            ind++;
            root = root.node;
        }
        list.deleteNode(root);
        Node node = list.root;
        while (node != null) {
            System.out.println(node.value);
            node = node.node;
        }
    }

}