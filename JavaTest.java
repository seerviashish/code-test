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
        System.out.println(myFun("123123", 0, 5));
    }

    private static boolean sumOfTwo(String str1, String str2) {
        int s1 = 0, s2 = 0;
        for (int i = 0; i < str1.length(); i++) {
            s1 += Integer.parseInt(str1.charAt(i) + "");
        }
        for (int i = 0; i < str2.length(); i++) {
            s2 += Integer.parseInt(str2.charAt(i) + "");
        }
        return s1 == s2;

    }

    private static int myFun(String st, int start, int end) {
        if (start > end) {
            return 0;
        }
        int mid = (start + end) / 2;
        if (sumOfTwo(st.substring(start, mid + 1), st.substring(mid + 1, end + 1))) {
            return end - start + 1;
        }
        return Math.max(myFun(st, start, end - 2), Math.max(myFun(st, start + 2, end), myFun(st, start + 1, end - 1)));
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