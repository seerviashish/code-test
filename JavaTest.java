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

    static class Envelope {
        int w;
        int h;
        int i;

        public Envelope(int w, int h, int i) {
            this.w = w;
            this.h = h;
            this.i = i;
        }

    }

    private static void main() throws Exception {
        int n = sc.nextInt();
        int w = sc.nextInt();
        int h = sc.nextInt();
        ArrayList<Envelope> arr = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int wi = sc.nextInt();
            int hi = sc.nextInt();
            if (wi > w && hi > h) {
                arr.add(new Envelope(wi, hi, i));
                cnt++;
            }
        }
        if (cnt == 0) {
            System.out.println(0);
            return;
        }

        arr.sort((e1, e2) -> {
            int res = e1.h - e2.h;
            if (res == 0) {
                res = e1.w - e2.w;
            }
            return res;
        });

        int[] dp = new int[cnt];
        int[] next = new int[cnt];
        Arrays.fill(dp, 1);
        Arrays.fill(next, -1);

        for (int i = cnt - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                Envelope ai = arr.get(i);
                Envelope aj = arr.get(j);
                if (ai.w > aj.w && ai.h > aj.h) {
                    if (dp[j] < dp[i] + 1) {
                        dp[j] = dp[i] + 1;
                        next[j] = i;
                    }
                }
            }
        }

        int res = 1;
        int head = 0;

        for (int i = 0; i < cnt; i++) {
            if (dp[i] > res) {
                res = dp[i];
                head = i;
            }
        }

        System.out.println(res);
        while (head != -1) {
            System.out.println(arr.get(head).i + 1 + " ");
            head = next[head];
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