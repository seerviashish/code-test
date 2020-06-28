import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaTest {
    private static PrintWriter out = new PrintWriter(System.out);
    private static Scanner sc;

    public static void main(String args[]) throws Exception {
        InputStream is = JavaTest.class.getResourceAsStream("in.txt");
        boolean testMode = is != null;
        sc = new Scanner(testMode ? is : System.in);

        long start = System.currentTimeMillis();
        main();

        if (testMode) {
            out.println();
            out.print(System.currentTimeMillis() - start + " ms");
        }

        out.close();
    }

    static class Tree {
        List<Integer> tree[];

        public Tree(int n) {
            this.tree = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                tree[i] = new ArrayList<Integer>();
            }
        }

        public void addEdge(int x, int y) {
            tree[x].add(y);
        }
    }

    private static List<Integer> hotels = new ArrayList<>();
    private static boolean isCat[];
    private static Tree tree;
    private static int m;

    private static void main() throws Exception {

        int n = sc.nextInt();
        m = sc.nextInt();
        isCat = new boolean[n];
        for (int i = 0; i < n; i++) {
            isCat[i] = sc.nextInt() == 1;
        }
        tree = new Tree(n);

        for (int i = 0; i < n - 1; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            x--;
            y--;
            tree.addEdge(x, y);
            tree.addEdge(y, x);
        }

        dfs(0, 0, 0);
        System.out.println(hotels.size());
    }

    private static void dfs(int root, int cat, int parent) {
        if (isCat[root]) {
            cat++;
        } else {
            if (cat <= m) {
                cat = 0;
            } else {
                return;
            }
        }
        if (cat > m) {
            return;
        }
        for (int i = 0; i < tree.tree[root].size(); i++) {
            int child = tree.tree[root].get(i);
            if (child == parent) {
                continue;
            } else {
                dfs(child, cat, root);
            }
        }
        if (tree.tree[root].size() == 1 && root != 0) {
            hotels.add(root);
        }
    }

}