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
            int n = sc.nextInt(), a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt(), d = sc.nextInt(),
                    p = sc.nextInt(), q = sc.nextInt(), y = sc.nextInt();
            int[] pos = new int[n];
            for (int i = 0; i < n; i++)
                pos[i] = sc.nextInt();
            int timeWalk = Math.abs((pos[b - 1] - pos[a - 1]) * p);

            int timeAC = Math.abs((pos[a - 1] - pos[c - 1]) * p);
            int timeCD = Math.abs((pos[c - 1] - pos[d - 1]) * q);
            int timeDB = Math.abs((pos[d - 1] - pos[b - 1]) * p);

            int timeTrain = y + timeCD + timeDB;

            if (timeAC <= y) {
                System.out.println(Math.min(timeWalk, timeTrain));
            } else {
                System.out.println(timeWalk);
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