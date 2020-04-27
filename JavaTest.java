import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class JavaTest {
    private static PrintWriter out = new PrintWriter(System.out);
    private static InputStreamReader in;

    public static void main(String args[]) throws Exception {
        InputStream is = JavaTest.class.getResourceAsStream("in.txt");
        boolean testMode = is != null;
        in = new InputStreamReader(testMode ? is : System.in);

        long start = System.currentTimeMillis();
        main();

        if (testMode) {
            out.println();
            out.print(System.currentTimeMillis() - start + " ms");
        }

        out.close();
    }

    private static void main() throws Exception {
        int n = in.readInt();
        int k = in.readInt();
        int[] arr = new int[n];
        int cnt = 0;
        for (int i = 0; i < n; i++)
            arr[i] = in.readInt();

        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                if(Math.abs(arr[i]-arr[j]) == k) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);

    }

    private static class InputStreamReader {
        private byte[] buf = new byte[1 << 8];
        private int curChar;
        private int charsRead;
        private InputStream stream;

        public InputStreamReader(InputStream stream) {
            this.stream = stream;
        }

        public int readInt() throws IOException {
            int c = read();
            while (skip(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!skip(c));
            return res * sgn;
        }

        public long readLong() throws IOException {
            int c = read();
            while (skip(c))
                c = read();
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!skip(c));
            return res * sign;
        }

        private int read() throws IOException {
            if (charsRead == -1)
                throw new InputMismatchException();
            if (curChar >= charsRead) {
                curChar = 0;
                charsRead = stream.read(buf);
                if (charsRead <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        private static boolean skip(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}