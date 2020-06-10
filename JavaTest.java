import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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

    private static void calculate(HashSet<String> temp, HashSet<String> njoin, HashSet<String> chrunUp,
            HashSet<String> resurct) {
        Iterator<String> it = temp.iterator();
        while (it.hasNext()) {
            String ut = it.next();
            if (!njoin.contains(ut) && !(chrunUp.contains(ut) || resurct.contains(ut))) {
                njoin.add(ut);
            } else if (chrunUp.contains(ut)) {
                chrunUp.remove(ut);
                resurct.add(ut);
            }
        }
        Iterator<String> it2 = njoin.iterator();
        while (it2.hasNext()) {
            String ut2 = it2.next();
            if (!temp.contains(ut2)) {
                chrunUp.add(ut2);
                it2.remove();
            }
        }
    }

    private static void main() throws Exception {
        int n = sc.nextInt();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        HashSet<String> temp = new HashSet<>();
        HashSet<String> njoin = new HashSet<>();
        HashSet<String> chrunUp = new HashSet<>();
        HashSet<String> resurct = new HashSet<>();
        Date prev = null;
        for (int i = 0; i < n; i++) {
            Date date = dateFormat.parse(sc.nextToken());
            String u = sc.nextToken();
            if (prev == null || prev.equals(date)) {
                prev = date;
                temp.add(u);
            } else {
                calculate(temp, njoin, chrunUp, resurct);
                System.out.println(njoin.size() + " " + chrunUp.size() + " " + resurct.size());
                temp.clear();
                prev = date;
                temp.add(u);
            }
        }
        calculate(temp, njoin, chrunUp, resurct);
        System.out.println(njoin.size() + " " + chrunUp.size() + " " + resurct.size());
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