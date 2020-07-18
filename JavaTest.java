import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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

    public static class EdgeLink {
        int src;
        int dest;

        public EdgeLink(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    public static class Graph {
        List<Edge>[] graph;
        List<EdgeLink> newLink;
        int size;
        boolean[] isVisited;

        public Graph(int size) {
            this.size = size;
            this.graph = new LinkedList[size + 1];
            this.newLink = new ArrayList<EdgeLink>();
            this.isVisited = new boolean[size + 1];
            for (int i = 0; i <= size; i++) {
                this.graph[i] = new LinkedList<Edge>();
            }
        }

        public void addEdge(int src, int dest, boolean isDirected) {
            if (isDirected) {
                this.graph[src].add(new Edge(true, dest));
            } else {
                this.graph[src].add(new Edge(false, dest));
                this.graph[dest].add(new Edge(false, src));
            }
        }

        public void resetIsVisited() {
            Arrays.fill(this.isVisited, false);
        }

        public void dfs(int src) {
            if (this.isVisited[src]) {
                return;
            }
            this.isVisited[src] = true;
            for (int i = 0; i < this.graph[src].size(); i++) {
                Edge edge = this.graph[src].get(i);
                if (!edge.isDirected) {
                    this.newLink.add(new EdgeLink(src, edge.nextNode));
                }
                dfs(edge.nextNode);
            }
        }

        public boolean isAllNodeVisited() {
            boolean result = true;
            for (int i = 1; i <= this.size; i++) {
                result &= this.isVisited[i];
            }
            return result;
        }
    }

    public static class Edge {
        boolean isDirected;
        int nextNode;

        public Edge(boolean isDirected, int nextNode) {
            this.isDirected = isDirected;
            this.nextNode = nextNode;
        }

    }

    private static void main() throws Exception {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            Graph graph = new Graph(n);
            while (m-- > 0) {
                int dir = sc.nextInt();
                int src = sc.nextInt();
                int dest = sc.nextInt();
                if (dir == 0) {
                    graph.addEdge(src, dest, false);
                } else {
                    graph.addEdge(src, dest, true);
                }
            }
            for (int i = 1; i <= n; i++) {
                if (!graph.isVisited[i]) {
                    graph.dfs(i);
                }
            }
            for (EdgeLink edgeLink : graph.newLink) {
                System.out.println(edgeLink.src + " <======> " + edgeLink.dest);
            }
            System.out.println("===========");
        }
    }

}