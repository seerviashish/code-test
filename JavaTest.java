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
        int n = sc.nextInt();
        while (n-- > 0) {
            String str = sc.nextToken();
            if (str.matches("R[0-9]+C[0-9]+")) {

                String[] nums = str.split("R|C");
                int x = Integer.parseInt(nums[2]);
                String st = "";
                while (x != 0) {
                    if (x % 26 != 0) {
                        st = (char) ('A' + (x % 26) - 1) + st;
                    } else {
                        st = 'Z' + st;
                        x = x - 26;
                    }
                    x = x / 26;
                }

                System.out.println(st + nums[1]);

            } else {
                String num = str.replaceAll("[0-9]", "");
                int x = 0;
                int len = num.length();
                for (int i = len - 1; i >= 0; --i) {
                    x += ((int) (num.charAt(i) - 'A') + 1) * Math.pow(26, (len - i - 1));
                }

                System.out.println("R" + str.replaceAll("[A-Z]", "") + "C" + x);

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