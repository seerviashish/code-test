package topic.linkedlist;

public class P3 {
    static class Node {
        int rowPos, colPos, value;
        Node right;
        Node bottom;

        public Node(int value, int rowPos, int colPos) {
            this.right = null;
            this.bottom = null;
            this.value = value;
            this.rowPos = rowPos;
            this.colPos = colPos;
        }
    }

    static class MyLinkedList {
        Node root = null;
        int maxRow, maxCol;

        public MyLinkedList(int maxRow, int maxCol) {
            this.maxRow = maxRow;
            this.maxCol = maxCol;
        }

        public void addValue(int value, int rowPos, int colPos) throws Exception {
            if (rowPos < 0 || colPos < 0 || rowPos > this.maxRow - 1 || colPos > this.maxCol - 1) {
                throw new Exception("Not valid range index");
            }
            if ((rowPos == 0 && colPos == 0) || root == null) {
                Node node = new Node(value, rowPos, colPos);
                root = node;
            } else {
                Node rowParent = this.root;
                int p1Row = rowPos;
                int p1Col = colPos - 1;
                while (rowParent != null && rowParent.rowPos < p1Row) {
                    rowParent = rowParent.bottom;
                }
                while (rowParent != null && rowParent.colPos < p1Col) {
                    rowParent = rowParent.right;
                }
                int p2Row = rowPos - 1;
                int p2Col = colPos;

                Node colParent = this.root;
                while (colParent != null && colParent.rowPos < p2Row) {
                    colParent = colParent.bottom;
                }
                while (colParent != null && colParent.colPos < p2Col) {
                    colParent = colParent.right;
                }
                if (rowParent != null && rowParent.right != null) {
                    rowParent.right.value = value;
                }

                Node newOrUpdatedNode = rowParent != null && rowParent.right != null ? rowParent.right
                        : new Node(value, rowPos, colPos);

                if (rowParent != null) {
                    rowParent.right = newOrUpdatedNode;
                }

                if (colParent != null) {
                    colParent.bottom = newOrUpdatedNode;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MyLinkedList list = new MyLinkedList(3, 3);
        int[][] arr = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                list.addValue(arr[i][j], i, j);
            }
        }

    }
}