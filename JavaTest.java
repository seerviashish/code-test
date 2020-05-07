import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
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

    private static class Friend {
        long money;
        long fact;

        Friend(long money, long fact) {
            this.money = money;
            this.fact = fact;
        }

        @Override
        public String toString() {
            return "Money -> " + this.money + "  Fact -> " + this.fact;
        }

    }

    private static void main() throws Exception {
        int n = sc.nextInt();
        long d = sc.nextLong();
        ArrayList<Friend> friends = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            long money = sc.nextLong();
            long fact = sc.nextLong();
            friends.add(new Friend(money, fact));
        }
        friends.sort(new Comparator<Friend>() {
            public int compare(Friend f1, Friend f2) {
                if (f2.money > f1.money) {
                    return -1;
                } else if (f2.money < f1.money) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        long maxFact = 0;
        long cnt = 0;
        int i = 0, j = 0;
        while (j < n) {
            Friend fi = friends.get(i);
            Friend fj = friends.get(j);
            if (fj.money - fi.money >= d) {
                i++;
                cnt -= fi.fact;
            } else {
                j++;
                cnt += fj.fact;
                maxFact = Math.max(cnt, maxFact);
            }
        }
        System.out.println(maxFact);
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