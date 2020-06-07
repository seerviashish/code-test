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
            int n = sc.nextInt(), h = sc.nextInt(), y1 = sc.nextInt(), y2 = sc.nextInt(), l = sc.nextInt();
            int cnt = 0;
            int duck = h - y1;
            int jump = y2;
            boolean couldPass = true;
            for (int i = 0; i < n; i++) {
                boolean pass = false;
                int ti = sc.nextInt();
                int br = sc.nextInt();
                if (couldPass) {
                    if (ti == 1 && duck <= br) {
                        cnt++;
                        pass = true;
                    }
                    if (ti == 2 && jump >= br) {
                        cnt++;
                        pass = true;
                    }
                    if (l > 1 && !pass) {
                        cnt++;
                        l--;
                        pass = true;
                    }
                    if (!pass) {
                        couldPass = false;
                    }
                }
            }
            System.out.println(cnt);
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