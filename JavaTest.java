import java.io.InputStream;
import java.io.PrintWriter;
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
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            int a, b, c;
            if (x == y && y == z) {
                a = b = c = x;
                System.out.println("YES");
                System.out.println(a + " " + b + " " + c);
            } else if (x == y && x > z) {
                a = x;
                b = z;
                c = z;
                System.out.println("YES");
                System.out.println(a + " " + b + " " + c);
            } else if (x == z && x > y) {
                b = x;
                a = y;
                c = y;
                System.out.println("YES");
                System.out.println(a + " " + b + " " + c);
            } else if (y == z && y > x) {
                c = y;
                a = x;
                b = x;
                System.out.println("YES");
                System.out.println(a + " " + b + " " + c);
            } else {
                System.out.println("NO");
            }

        }
    }

}