/**
 * @program: suanfa
 * @ClassName: H_62
 * @description: 62. 不同路径
 * <p>
 * <p>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * <p>
 * 问总共有多少条不同的路径？
 * @link: https://leetcode.cn/problems/unique-paths/
 * @author: zhoujie07
 * @create: 2026-05-21
 **/
public class H_62 {

    /**
     * 代码随想录动规五部曲：
     * 1. dp数组含义：dp[i][j] 表示从左上角走到坐标 (i, j) 的不同路径数。
     * 2. 递推公式：机器人只能从上方或左方到达当前位置，
     *    所以 dp[i][j] = dp[i - 1][j] + dp[i][j - 1]。
     * 3. 初始化：第一行、第一列都只有一种走法，初始化为 1。
     * 4. 遍历顺序：从左到右、从上到下。
     * 5. 返回值：dp[m - 1][n - 1]。
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

}
