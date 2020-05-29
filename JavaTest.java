import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
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
        String st = sc.nextToken();
        Stack<Integer> stk = new Stack<>();
        int maxLen = 0, index = 1;
        for (int i = 0; i < st.length(); i++) {
            if (st.charAt(i) == '(') {
                stk.push(i);
            } else {
                if (!stk.empty() && st.charAt(stk.peek()) == '(') {
                    int tempLen = i - stk.peek() + 1;
                    if (tempLen > maxLen) {
                        maxLen = tempLen;
                        index = stk.peek() + 1;
                    }
                    stk.pop();
                } else {
                    stk.push(i);
                }
            }
        }
        System.out.println(maxLen + " " + index);
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