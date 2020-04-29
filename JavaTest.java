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
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        heapSort(arr, n);
    }

    private static void heapSort(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
        printArray(arr, n);
    }

    private static void printArray(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            out.print(arr[i] + " ");
        }
        out.println();
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void heapify(int[] arr, int n, int root) {
        int max = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < n && arr[left] > arr[max])
            max = left;

        if (right < n && arr[right] > arr[max])
            max = right;

        if (max != root) {
            swap(arr, root, max);
            heapify(arr, n, max);
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