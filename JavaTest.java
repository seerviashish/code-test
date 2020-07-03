import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class JavaTest {
    private static PrintWriter out = new PrintWriter(System.out);
    private static Scanner sc;

    public static void main(String args[]) throws Exception {
        InputStream is = JavaTest.class.getResourceAsStream("in.txt");
        boolean testMode = is != null;
        sc = new Scanner(testMode ? is : System.in);

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
        int h = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            Arrays.sort(arr, 0, i + 1);
            long sum = 0;
            for (int j = i; j >= 0; j -= 2) {
                sum += arr[j];
            }
            if (sum <= h) {
                ans = i + 1;
            }
        }
        System.out.println(ans);
    }

}