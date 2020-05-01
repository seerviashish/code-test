import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class JavaTest {
    private static PrintWriter out = new PrintWriter(System.out);
    private static FastScanner sc;

    private static HashSet<String> state = new HashSet<>();

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

    private static int[] x = { -1, 0, 1, 0 };
    private static int[] y = { 0, 1, 0, -1 };
    private static char[] move = { 'U', 'R', 'D', 'L' };

    private static int MOD = 1_00_000_000 + 7;

    private static char getReuiredMove(char move) {
        if (move == 'U') {
            return 'D';
        } else if (move == 'D') {
            return 'U';
        } else if (move == 'R') {
            return 'L';
        } else {
            return 'R';
        }
    }

    private static void main() throws Exception {
        int n = sc.nextInt();
        char[][] s = new char[n][n];
        char[][] e = new char[n][n];
        int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
        for (int i = 0; i < n; i++) {
            s[i] = sc.nextToken().toCharArray();
            if (x1 == -1 && y1 == -1) {
                for (int j = 0; j < n; j++) {
                    if (s[i][j] == 'W') {
                        x1 = i;
                        y1 = j;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            e[i] = sc.nextToken().toCharArray();
            if (x2 == -1 && y2 == -1) {
                for (int j = 0; j < n; j++) {
                    if (e[i][j] == 'W') {
                        x2 = i;
                        y2 = j;
                    }
                }
            }
        }
        ArrayList<Character> ans = new ArrayList<>();
        findRoot(ans, x1, y1, s, e, n);
        int checkSum = 0;
        for (char c : ans) {
            char cc = getReuiredMove(c);
            checkSum += (checkSum * 243 + (int) cc) % MOD;
        }
        System.out.println(checkSum);
    }

    private static String convertArrayToString(char[][] arr, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(arr[i][j]);
            }
        }
        return sb.toString();
    }

    private static void printMatrix(char[][] arr, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean compareMatrix(char[][] a, char[][] b, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void swap(char[][] a, int x1, int y1, int x2, int y2) {
        char temp = a[x1][y1];
        a[x1][y1] = a[x2][y2];
        a[x2][y2] = temp;
    }

    private static boolean findRoot(ArrayList<Character> ans, int x1, int y1, char[][] s, char[][] e, int n) {
        if (state.contains(convertArrayToString(s, n))) {
            return false;
        }
        state.add(convertArrayToString(s, n));
        if (compareMatrix(s, e, n)) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            int nX2 = x1 + x[i];
            int nY2 = y1 + y[i];
            if (inBoundary(nX2, nY2, n)) {
                ans.add(move[i]);
                swap(s, x1, y1, nX2, nY2);
                boolean tempAns = findRoot(ans, nX2, nY2, s, e, n);
                if (tempAns) {
                    return true;
                }
                ans.remove(ans.size() - 1);
                swap(s, x1, y1, nX2, nY2);
            }
        }
        return false;

    }

    private static boolean inBoundary(int x, int y, int n) {
        if (x >= 0 && x < n && y >= 0 && y < n) {
            return true;
        }
        return false;
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