import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JavaTest {
    private static PrintWriter out = new PrintWriter(System.out);
    private static FastScanner sc;

    public static void main(String args[]) throws Exception {
        InputStream is = JavaTest.class.getResourceAsStream("in.txt");
        boolean testMode = is != null;
        sc = new FastScanner(testMode ? is : System.in);

        long start = System.currentTimeMillis();
        main();

        if (testMode) {
            out.println();
            out.print(System.currentTimeMillis() - start + " ms");
        }

        out.close();
    }

    private static int ans = 0;
    private static int m;
    private static int[] arr;
    private static ArrayList<Integer>[] tree;

    private static void main() throws Exception {
        int n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n + 1];
        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            tree[x].add(y);
            tree[y].add(x);
        }
        dfs(1, arr[1], 0);
        System.out.println(ans);
    }

    private static void dfs(int child, int cat, int parent) {
        if (cat > m) {
            return;
        }
        if (tree[child].size() == 1 && child != 1) {
            ans++;
            return;
        }
        for (int i = 0; i < tree[child].size(); i++) {
            int childOfChild = tree[child].get(i);
            if (childOfChild == parent)
                continue;
            if (arr[childOfChild] != 0) {
                dfs(childOfChild, cat + 1, child);
            } else {
                dfs(childOfChild, 0, child);
            }
        }
    }

    private static class FastScanner {
        private BufferedReader br;
        private StringTokenizer st;

        public FastScanner(InputStream stream) {
            br = new BufferedReader(new InputStreamReader(stream));
        }

        public String nextToken() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(nextToken());
        }

        public long nextLong() {
            return Long.parseLong(nextToken());
        }

        public double nextDouble() {
            return Double.parseDouble(nextToken());
        }
    }
}