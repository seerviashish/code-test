import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

    private static void main() throws Exception {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
            int ans = dishCountLoop(arr, 0);
            System.out.println(ans);
        }
    }

    private static int dishCountLoop(int[] arr, int index) {
        boolean prev = false;
        int[] maxType = new int[1001];
        int ans = 0;
        int type = 1;
        for (int i = 0; i < arr.length; i++) {
            if (prev) {
                if (arr[i - 1] == arr[i]) {
                    prev = false;
                } else {
                    maxType[arr[i]]++;
                    prev = true;
                }
            } else {
                maxType[arr[i]]++;
                prev = true;
            }
            if (maxType[arr[i]] >= ans) {
                type = Math.min(type, arr[i]);
            }
            ans = Math.max(ans, maxType[arr[i]]);
        }
        return type;
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