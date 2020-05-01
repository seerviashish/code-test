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
        int t = sc.nextInt();
        ArrayList<MyList> list = new ArrayList<>();
        while (t-- > 0) {
            int n = sc.nextInt();
            MyList ml = new MyList();
            for (int i = 0; i < n; i++) {
                ml.add(sc.nextInt());
            }
            list.add(ml);
        }

        // MyList<Integer> nl = new MyList<>();
        for (MyList ml : list) {
            Node n = ml.head;
            while (n != null) {
                System.out.print(n.item + "->");
                n = n.next;
            }
            System.out.println("null");
        }
        MyList ans = merge(list);
        printMyList(ans);
    }

    private static void printMyList(MyList l) {
        Node n = l.head;
        while (n != null) {
            System.out.print(n.item + "->");
            n = n.next;
        }
        System.out.println("null");
    }

    private static MyList merge(ArrayList<MyList> list) {
        MyQueue mq = new MyQueue(list.size());
        for (MyList ml : list) {
            mq.add(ml.head);
        }
        MyList ans = new MyList();
        while (mq.currentSize > 0) {
            Node temp = mq.pop();
            if (temp.next != null) {
                mq.add(temp.next);
            }
            ans.add(temp.item);
        }
        return ans;
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