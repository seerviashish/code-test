import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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

    private static int[] arr = new int[3];
    private static int[] dp = new int[4001];

    private static void main() throws Exception {
        int n = sc.nextInt();
        for (int i = 0; i < 3; i++)
            arr[i] = sc.nextInt();
        Arrays.fill(dp, -1);
        System.out.println(cutRibbon(n));
    }

    private static int cutRibbon(int n) {
        if (n < 0) {
            return Integer.MIN_VALUE;
        }
        if (n == 0) {
            return 0;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        return dp[n] = Math.max(Math.max(cutRibbon(n - arr[0]), cutRibbon(n - arr[1])), cutRibbon(n - arr[2])) + 1;
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