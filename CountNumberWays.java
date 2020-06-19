public class CountNumberWays {
    public static void main(String[] args) {
        System.out.println(count(20, 1000));
    }

    private static int count(int n, int lastSelected) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        int ans = 0;
        if (lastSelected >= 3) {
            ans += count(n - 3, 3);
        }
        if (lastSelected >= 5) {
            ans += count(n - 5, 5);
        }
        if (lastSelected >= 10) {
            ans += count(n - 10, 10);
        }
        return ans;
    }
}