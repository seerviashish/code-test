import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        ArrayList<String> lines = new ArrayList<>();
        String line;
        int maxLen = -1;
        while ((line = sc.nextLine()) != null) {
            lines.add(line);
            int len = line.length();
            if (len > maxLen) {
                maxLen = len;
            }
        }

        for (int i = 0; i <= lines.size() + 1; i++) {
            if (i == 0 || i == lines.size() + 1) {
                for (int j = 0; j < maxLen + 2; j++) {
                    System.out.print("*");
                }
                System.out.println();
            } else {
                int index = i - 1;
                String st = lines.get(index);
                int space = maxLen - st.length();
                int lSpace = 0;
                int rSpace = 0;
                if (space % 2 == 0) {
                    rSpace = space / 2;
                    lSpace = space / 2;
                } else {
                    lSpace = space / 2;
                    rSpace = space - lSpace;

                }
                StringBuilder ans = new StringBuilder("*");
                for (int j = 0; j < lSpace; j++) {
                    ans.append(" ");
                }
                ans.append(st);
                for (int j = 0; j < rSpace; j++) {
                    ans.append(" ");
                }
                ans.append("*");
                System.out.println(ans);
            }
        }
    }

    private static class FastScanner {
        private BufferedReader br;
        private StringTokenizer st;

        public FastScanner(InputStream stream) {
            br = new BufferedReader(new InputStreamReader(stream));
        }

        public String nextLine() throws IOException {
            return br.readLine();
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