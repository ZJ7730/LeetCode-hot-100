/**
 * @program: suanfa
 * @ClassName: H_221
 * @description: 221. 最大正方形
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_221 {
    /**
     * 已实现思路，参考代码随想录动规五部曲：
     * 1. dp数组含义：dp[i][j] 表示以原矩阵 matrix[i - 1][j - 1] 为右下角的最大正方形边长。
     *    这里多开一行一列做哨兵，所以 dp 的坐标比原矩阵坐标多 1。
     * 2. 递推公式：如果 matrix[i - 1][j - 1] == '1'，
     *    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1。
     *    如果 matrix[i - 1][j - 1] == '0'，dp[i][j] = 0。
     * 3. 初始化：这里多开一行一列作为哨兵，第一行和第一列默认都是 0；
     *    因此原矩阵中第一行、第一列只要是 '1'，dp 值自然会变成 1。
     * 4. 遍历顺序：从左到右、从上到下，保证上方、左方、左上方状态都已经计算过。
     * 5. 解题思路：当前位置能扩成多大的正方形，取决于上、左、左上三个方向能支撑的最小边长；
     *    最终返回的是最大边长的平方，也就是正方形面积。
     */

    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m + 1][n + 1];

        int res = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
                } else {
                    dp[i][j] = 0;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res * res;
    }

}
