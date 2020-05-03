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
            int maxSum = findMaxSubArraySum(arr, n);
            int minSum = findMinSubArraySum(arr, n);
            System.out.println("Max Sum :=> " + maxSum);
            System.out.println("Min Sum :=> " + minSum);
        }
    }

    private static int findMaxSubArraySum(int[] arr, int size) {
        int max = Integer.MIN_VALUE;
        int last = 0;
        int last_index = 0;
        int first_index = 0;
        int temp_first = 0;
        for (int i = 0; i < size; i++) {
            last = last + arr[i];
            if (last > max) {
                max = last;
                first_index = temp_first + 1;
                last_index = i;
            }
            if (last < 0) {
                last = 0;
                temp_first = i;
            }
        }
        System.out.println("[Max] first_inde = " + first_index + "last_index = " + last_index);
        return max;
    }

    private static int findMinSubArraySum(int[] arr, int size) {
        int min = Integer.MAX_VALUE;
        int last = 0;
        int first_index = 0;
        int last_index = 0;
        int temp_first = 0;
        for (int i = 0; i < size; i++) {
            last = last + arr[i];
            if (last < min) {
                min = last;
                first_index = temp_first + 1;
                last_index = i;
            }
            if (last > 0) {
                last = 0;
                temp_first = i;
            }
        }
        System.out.println("[Min] first_inde = " + first_index + "last_index = " + last_index);
        return min;
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