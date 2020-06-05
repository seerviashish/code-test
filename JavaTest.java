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
        int a = sc.nextInt(), vmax = sc.nextInt();
        int l = sc.nextInt(), d = sc.nextInt(), w = Math.min(sc.nextInt(), vmax);

        double d2 = w * w / 2.0 / a;
        if (d2 < d) {
            double x = d - d2;
            double v = Math.min(vmax, Math.sqrt(w * w + x * a));
            double d3 = x - (v * v - w * w) / a;
            double t1 = 1.0 * w / a + (v - w) / a * 2 + d3 / v;
            double v2 = Math.min(vmax, Math.sqrt(w * w + 2 * a * (l - d)));
            double t2 = (v2 - w) / a + (l - d - (v2 * v2 - w * w) / 2 / a) / v2;
            System.out.println(t1 + t2);
        } else {
            double v = Math.min(vmax, Math.sqrt(2 * a * l));
            System.out.println(v / a + (l - v * v / 2 / a) / v);
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