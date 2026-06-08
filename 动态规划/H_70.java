/**
 * @program: suanfa
 * @ClassName: H_70
 * @description: 70. 爬楼梯
 * @author: zhoujie07
 * @create: 2026-05-21
 **/
public class H_70 {

    /**
     * 代码随想录动规五部曲：
     * 1. dp数组含义：dp[i] 表示爬到第 i 阶楼梯的方法数。
     * 2. 递推公式：到第 i 阶可以从 i - 1 跨 1 阶，也可以从 i - 2 跨 2 阶，
     *    所以 dp[i] = dp[i - 1] + dp[i - 2]。
     * 3. 初始化：dp[1] = 1，dp[2] = 2。
     * 4. 遍历顺序：从前往后遍历，因为 dp[i] 依赖前面的状态。
     * 5. 返回值：dp[n]。
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }
}
