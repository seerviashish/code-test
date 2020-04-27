package javacode;


public class LargestRegion {
    private static int ROW, COL, count;
    public static void main(String args[]) {
        int M[][] = {
                        {0, 0, 1, 1, 0},
                        {1, 0, 1, 1, 0},
                        {0, 1, 0, 0, 0},
                        {0, 0, 0, 0, 1}
                    };
        ROW = 4;
        COL = 5;
        System.out.println(largestRegion(M));
    }

    private static int largestRegion(int[][] M) {
        boolean[][] vis = new boolean[ROW][COL];
        int result = 0;
        for (int i = 0; i < ROW; i++) {
            for(int j = 0; j < COL; j++) {
                if(M[i][j] == 1 && !vis[i][j]) {
                    count = 1;
                    DFS(M, i, j, vis);
                    result = Math.max(result, count);
                }
            }
        }
        return result;
    }

    private static void DFS(int[][] M, int row, int col, boolean[][] vis) {
        int[] rowNbr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colNbr = {-1, 0, 1, -1, 1, -1, 0, 1};

        vis[row][col] = true;

        for(int k = 0; k < 8; k++) {
            if(isSafe(M, row + rowNbr[k], col + colNbr[k], vis)) {
                count++;
                DFS(M, row + rowNbr[k], col+colNbr[k], vis);
            }
        }
    }

    private static boolean isSafe(int[][] M, int row, int col, boolean[][] vis){ 
        return ((row >= 0) && (row < ROW) && (col >= 0) && (col < COL) && (M[row][col] == 1 && !vis[row][col]));
    }
}