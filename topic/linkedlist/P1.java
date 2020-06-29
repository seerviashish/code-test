package topic.linkedlist;

public class P1 {

    static class MyLinkedList {
        Node root = null;

        public MyLinkedList() {
        }

        public int size() {
            if (this.root != null) {
                return this.root.index + 1;
            }
            return 0;
        }

        public void addValue(int value) {
            int index = this.root == null ? 0 : this.root.index + 1;
            Node node = new Node(value, index);
            node.node = this.root;
            this.root = node;
        }

        public void addValue(int value, int index) throws Exception {
            if (index > this.root.index) {
                throw new Exception("Out Of Range");
            }
            if (index < 0) {
                throw new Exception("Negative index");
            }

            Node node = new Node(value, index);
            putValue(node, index);
        }

        public void delete(int index) throws Exception {
            if (index > this.root.index) {
                throw new Exception("Out Of Range");
            }
            if (index < 0) {
                throw new Exception("Negative index");
            }
            deleteValue(index);
        }

        private void putValue(Node node, int index) {
            Node root = this.root;
            while (root != null) {
                if (root.index == index) {
                    Node temp = root;
                    Node nextNode = root.node;
                    node.node = nextNode;
                    temp.index += 1;
                    temp.node = node;
                    break;
                }
                root.index += 1;
                root = root.node;
            }
        }

        private void deleteValue(int index) {
            Node root = this.root;
            Node lastNode = null;
            while (root != null) {
                if (root.index == index) {
                    Node nextNode = root.node;
                    if (lastNode == null) {
                        this.root = nextNode;
                    } else {
                        lastNode.node = nextNode;
                    }
                    break;
                }
                root.index -= 1;
                lastNode = root;
                root = root.node;
            }
        }

        public void print() {
            int[] arr = new int[this.root.index + 1];
            int ind = 0;
            Node root = this.root;
            while (root != null) {
                arr[ind] = root.value;
                root = root.node;
                ind++;
            }
            for (int i = arr.length - 1; i >= 0; i--) {
                System.out.print(arr[i]);
                if (i != 0) {
                    System.out.print(" ");
                }
            }
        }

    }

    static class Node {
        int value;
        int index;
        Node node;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
            this.node = null;
        }
    }

    public static void main(String[] args) throws Exception {
        MyLinkedList list = new MyLinkedList();
        int[] arr = { 14, 54, 13, 62, 64, 75 };
        for (int i = 0; i < arr.length; i++) {
            list.addValue(arr[i]);
        }
        System.out.println(list.size());
    }
}