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
        String s = sc.nextToken();
        String t = sc.nextToken();
        solve(s, t);
    }

    private static void solve(String s, String t) {
        char sx = s.charAt(0);
        char tx = t.charAt(0);
        char sy = s.charAt(1);
        char ty = t.charAt(1);

        int dx = sx - tx;
        int dy = sy - ty;

        out.println(Math.max(Math.abs(dx), Math.abs(dy)));

        while (dx != 0 || dy != 0) {
            if (dx != 0) {
                if (dx < 0) {
                    out.print('R');
                    sx++;
                } else {
                    out.print('L');
                    sx--;
                }
            }
            if (dy != 0) {
                if (dy < 0) {
                    out.print('U');
                    sy++;
                } else {
                    out.print('D');
                    sy--;
                }
            }
            out.println("");
            dx = sx - tx;
            dy = sy - ty;
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