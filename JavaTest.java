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
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            System.out.println(canServeAll(arr, n) ? "YES" : "NO");
        }
    }

    private static boolean canServeAll(int[] arr, int n) {
        int w5 = 0, w10 = 0;
        boolean ans = true;
        for (int i = 0; i < n; i++) {
            if (arr[i] >= 5) {
                if (arr[i] == 5) {
                    w5++;
                }
                if (arr[i] == 10) {
                    w10++;
                }
                if (arr[i] - 5 == 0) {
                } else if (arr[i] - 5 == 5 && w5 > 0) {
                    w5--;
                } else if (arr[i] - 5 == 10 && (w10 > 0 || w5 >= 2)) {
                    if (w10 > 0) {
                        w10--;
                    } else if (w5 >= 2) {
                        w5 -= 2;
                    }
                } else {
                    ans = false;
                    break;
                }
            } else {
                ans = false;
                break;
            }
        }
        return ans;
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