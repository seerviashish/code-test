import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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

    private static class WordsState {
        int spamCnt;
        int notSpamCnt;

        public WordsState(int spamCnt, int notSpamCnt) {
            this.spamCnt = spamCnt;
            this.notSpamCnt = notSpamCnt;
        }

    }

    private static void main() throws Exception {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            HashMap<String, WordsState> mp = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String st = sc.nextToken();
                int s = sc.nextInt();
                if (mp.containsKey(st)) {
                    WordsState ws = mp.get(st);
                    if (s == 0) {
                        ws.notSpamCnt++;
                    } else {
                        ws.spamCnt++;
                    }
                } else {
                    WordsState ws = s == 0 ? new WordsState(0, 1) : new WordsState(1, 0);
                    mp.put(st, ws);
                }
            }

            int ans = 0;
            for (Map.Entry<String, WordsState> entry : mp.entrySet()) {
                ans += Math.max(entry.getValue().notSpamCnt, entry.getValue().spamCnt);
            }
            System.out.println(ans);
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