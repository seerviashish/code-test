import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            ArrayList<Integer> p = new ArrayList<Integer>(2 * n);
            for (int i = 0; i < 2 * n; i++) {
                int x = sc.nextInt();
                p.add(x);
            }
            while (p.size() > 0) {
                int x = p.get(0);
                System.out.print(x);
                System.out.print(p.size() > 2 ? " " : "");
                p.remove((Integer) x);
                p.remove((Integer) x);
            }
            System.out.println();
        }
    }

}