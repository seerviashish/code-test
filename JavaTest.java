import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
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
        String st = sc.next();
        int z[] = new int[st.length()];
        int l = 0, r = 0;
        int n = z.length;

        for (int i = 1; i < z.length; i++) {
            if (i <= r) {
                z[i] = Integer.min(r - i + 1, z[i - l]);
            }
            while (z[i] + i < n && st.charAt(z[i] + i) == st.charAt(z[i])) {
                z[i]++;
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }

        HashMap<Integer, Integer> hm = new HashMap<>();
        int max = -1;
        for (int i = 1; i < n; i++) {
            hm.put(z[i], hm.getOrDefault(z[i], 0) + 1);
            if (max < z[i]) {
                max = z[i];
            }
        }
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (i + z[i] == n && (z[i] < max || (z[i] == max && hm.get(max) > 1)) && ans < z[i]) {
                ans = z[i];
            }
        }
        if (ans == -1) {
            System.out.println("Just a legend");
        } else {
            System.out.println(st.substring(0, ans));
        }
    }

}