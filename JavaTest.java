import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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

    static HashMap<Character, ArrayList<Edge>> gf = new HashMap<>();

    static class Edge {
        char dest;
        int cap;

        public Edge(char dest, int cap) {
            this.dest = dest;
            this.cap = cap;
        }
    }

    private static void main() throws Exception {
        int t = sc.nextInt();
        while (t-- > 0) {
            char x = sc.next().charAt(0);
            char y = sc.next().charAt(0);
            int cap = sc.nextInt();
            addEdge(x, y, cap);
        }
    }

    private static void addEdge(char x, char y, int cap) {
        if (gf.containsKey(x)) {
            gf.get(x).add(new Edge(y, cap));
        } else {
            gf.put(x, new ArrayList<Edge>());
            gf.get(x).add(new Edge(y, cap));
        }
    }
}