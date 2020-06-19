import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Temp {

    private static void read() throws Exception {
        FileInputStream fi = new FileInputStream("in.txt");

        BufferedInputStream bin = new BufferedInputStream(fi != null ? System.in : fi);
        System.out.println("Number of remaining bytes :: " + bin.available());

        boolean b = bin.markSupported();
        if (b) {
            bin.mark(bin.available());
        }
        bin.skip(4);
        System.out.println("FileContents :");

        int ch;
        while ((ch = bin.read()) != -1)
            System.out.print((char) ch);
    }

    private static void write() throws Exception {
        FileOutputStream fo = new FileOutputStream("fo.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fo);
        for (int i = 65; i < 80; i++) {
            bos.write(i);
        }
        bos.flush();
        bos.close();
    }

    private static void fileWrite() {
        FileWriter fw;
        try {
            fw = new FileWriter("fo.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write("Ashish Chaudhary");
            bw.flush();
            bw.close();
        } catch (Exception e) {
        }
    }

    private static void myInputReader() {
        try {
            InputStream is = new FileInputStream("fo.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is != null ? is : System.in));
            System.out.println(br.readLine());

        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws Exception {
        int arr[][] = new int[][] { { 1, 2, -1, -4, -20 }, { -8, -3, 4, 2, 1 }, { 3, 8, 10, 1, 3 },
                { -4, -1, 1, 7, -6 } };
        maxRectangle(arr);
    }

    private static void maxRectangle(int mat[][]) {
        int m = mat.length;
        int n = mat[0].length;
        int preSum[][] = new int[m + 1][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i + 1][j] = preSum[i][j] + mat[i][j];
            }
        }
        int maxSum = -1;
        int minSum = Integer.MIN_VALUE;
        int negRow = 0, negCol = 0;
        int rStart = 0, rEnd = 0, cStart = 0, cEnd = 0;
        for (int rowStart = 0; rowStart < m; rowStart++) {
            for (int row = rowStart; row < m; row++) {
                int sum = 0;
                int curColStart = 0;
                for (int col = 0; col < n; col++) {
                    sum += preSum[row + 1][col] - preSum[rowStart][col];
                    if (sum < 0) {
                        if (minSum < sum) {
                            minSum = sum;
                            negRow = row;
                            negCol = col;
                        }
                        sum = 0;
                        curColStart = col + 1;
                    } else if (maxSum < sum) {
                        maxSum = sum;
                        rStart = rowStart;
                        rEnd = row;
                        cStart = curColStart;
                        cEnd = col;
                    }
                }
            }
        }
        if (maxSum == -1) {
            System.out.println("from row - " + negRow + " to row - " + negRow);
            System.out.println("from col - " + negCol + " to col - " + negCol);
        } else {
            System.out.println("from row - " + rStart + " to row - " + rEnd);
            System.out.println("from col - " + cStart + " to col - " + cEnd);
        }
        System.out.println("Max SUM:== " + (maxSum == -1 ? minSum : maxSum));
    }

    class Node {
        int key, height;
        Node left, right;

        Node(int d) {
            key = d;
            height = 1;
        }
    }

    class AVLTree {
        Node root;

        int height(Node N) {
            if (N == null) {
                return 0;
            }
            return N.height;
        }

        int max(int a, int b) {
            return a > b ? a : b;
        }

        Node rightRotate(Node y) {
            Node x = y.left;
            Node t = x.right;
            x.right = y;
            y.left = t;
            y.height = max(height(y.left), height(y.right)) + 1;
            x.height = max(height(x.left), height(x.right)) + 1;
            return x;
        }

        Node leftRotate(Node x) {
            Node y = x.right;
            Node t = y.left;
            y.left = y;
            y.right = t;
            y.height = max(height(y.left), height(y.right)) + 1;
            x.height = max(height(x.left), height(x.right)) + 1;
            return y;
        }

        int getBalance(Node n) {
            if (n == null) {
                return 0;

            }
            return height(n.left) - height(n.right);
        }

        Node insert(Node node, int key) {
            if (node == null) {
                return new Node(key);
            }

            if (key < node.key) {
                node.left = insert(node.left, key);
            } else if (key > node.key) {
                node.right = insert(node.right, key);
            } else {
                return node;
            }

            node.height = 1 + max(height(node.left), height(node.right));
            int balance = getBalance(node);

            if (balance > 1 && key < node.left.key)
                return rightRotate(node);

            // Right Right Case
            if (balance < -1 && key > node.right.key)
                return leftRotate(node);

            // Left Right Case
            if (balance > 1 && key > node.left.key) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // Right Left Case
            if (balance < -1 && key < node.right.key) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            /* return the (unchanged) node pointer */
            return node;
        }
    }

    static void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
}