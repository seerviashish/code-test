package javacode;

import java.util.Stack;

public class MyTree {
    public Node root;
    public int size = 0;
    public int level = 0;
    public Stack<Node> st1 = new Stack<>();
    public Stack<Node> st2 = new Stack<>();

    public void add(int value) {
        if (root == null) {
            root = new Node(value);
            st1.add(root);
            level = 1 - level;
        } else {
            Node temp;
            if (level == 0) {
                temp = st2.peek();
            } else {
                temp = st1.peek();
            }
            Node newNode = new Node(value);
            if (level == 0) {
                if (temp.right == null) {
                    temp.right = newNode;
                } else if (temp.left == null) {
                    temp.left = newNode;
                    st2.pop();
                }
                st1.add(newNode);
            } else {
                if (temp.left == null) {
                    temp.left = newNode;
                } else if (temp.right == null) {
                    temp.right = newNode;
                    st1.pop();
                }
                st2.add(newNode);
            }

            if (level == 0 && st2.isEmpty()) {
                level = 1 - level;
            }
            if (level == 1 && st1.isEmpty()) {
                level = 1 - level;
            }
        }
        size++;
    }

    public void preOrder() {
        preOrderUtil(root, null);
    }

    public void postOrder() {
        postOrderUtil(root, null);
    }

    public void inOrder() {
        inOrderUtil(root, null);
    }

    private void preOrderUtil(Node root, Node parent) {
        if (root == null) {
            return;
        }
        System.out.println(root.value);
        preOrderUtil(root.left, root);
        preOrderUtil(root.right, root);
    }

    private void postOrderUtil(Node root, Node parent) {
        if (root == null) {
            return;
        }
        preOrderUtil(root.left, root);
        preOrderUtil(root.right, root);
        System.out.println(root.value);
    }

    private void inOrderUtil(Node root, Node parent) {
        if (root == null) {
            return;
        }
        preOrderUtil(root.left, root);
        System.out.println(root.value);
        preOrderUtil(root.right, root);
    }
}