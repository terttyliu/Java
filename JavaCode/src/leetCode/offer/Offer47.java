package leetCode.offer;

/**
 * @category 动态规划
 * @author `pjliu`
 * @category 动态规划
 * @date 2020/10/01
 *
 *  * 动态规划最重要的是找到状态转移表达式
 *  * 另dp[i][j]表示到达第i行j列时获得的最大转移数值；
 *  * 则一般的,dp[i][j]=max{dp[i-1][j],dp[i][j-1]}+value[i][j]
 *  * 注意处理第一行第一列。
 */
public class Offer47 {
    public static int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                if (i == 0) grid[i][j] += grid[i][j - 1];
                else if (j == 0) grid[i][j] += grid[i - 1][j];
                else grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[m - 1][n - 1];
    }


    public static void main(String[] args) {
        System.out.println(maxValue(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}}));
    }
}
