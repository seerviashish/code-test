public class TrainCost {
    static int INF = Integer.MAX_VALUE, N = 4;

    public static void main(String[] args) {
        int cost[][] = { { 0, 15, 80, 90 }, { INF, 0, 40, 50 }, { INF, INF, 0, 70 }, { INF, INF, INF, 0 } };
        System.out.println(minCost(cost, 0, 3));
    }

    private static int minCost(int arr[][], int s, int d) {
        if (s == d || s + 1 == d) {
            return arr[s][d];
        }
        int min = arr[s][d];
        for (int i = s + 1; i < d; i++) {
            int c = minCost(arr, s, i) + minCost(arr, i, d);
            if (c < min) {
                min = c;
            }
        }
        return min;
    }
}