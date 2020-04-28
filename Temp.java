import java.util.*;

public class Temp {
    static class Graph {
        int vertex;
        LinkedList<Integer> list[];

        public Graph(int vertex) {
            this.vertex = vertex;
            list = new LinkedList[vertex];
            for (int i = 0; i < vertex; i++) {
                list[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int dest) {
            list[source].addFirst(dest);
            list[dest].addFirst(source);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            char[] s1 = sc.next().toCharArray();
            char[] s2 = sc.next().toCharArray();
            Graph g = new Graph(n);
            for (int i = 0; i < k; i++) {
                int l, r;
                l = sc.nextInt();
                r = sc.nextInt();
                g.addEdge(l, r);
            }
            boolean vis[] = new boolean[n];
            ArrayList<Integer> s = new ArrayList<>();
            ArrayList<Character> c = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (!vis[i]) {
                    s.clear();
                    c.clear();
                    dfs(i, g, vis, s);
                    s.sort((i1, i2) -> (i1 - i2));
                    for (int j : s) {
                        c.add(s2[j]);
                    }
                    c.sort((c1, c2) -> c1 - c2);
                    int cur = 0;
                    for (int j : s) {
                        if (c.get(cur) < s1[j]) {
                            s1[j] = c.get(cur);
                            cur++;
                        }
                    }
                }
            }
            System.out.println(new String(s1));
        }
    }

    private static void dfs(int i, Graph g, boolean[] vis, ArrayList<Integer> s) {
        vis[i] = true;
        s.add(i);
        for (int cur : g.list[i]) {
            if (!vis[cur]) {
                dfs(cur, g, vis, s);
            }
        }
    }
}