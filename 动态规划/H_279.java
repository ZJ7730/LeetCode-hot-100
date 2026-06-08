/**
 * @program: suanfa
 * @ClassName: H_279
 * @description: 279. 完全平方数
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_279 {

    /**
     * 代码随想录动规五部曲：
     * 1. dp数组含义：dp[j] 表示组成整数 j 所需的最少完全平方数个数。
     * 2. 物品含义：1、4、9、...、i * i 都是可以重复使用的物品。
     * 3. 递推公式：dp[j] = Math.min(dp[j], dp[j - i * i] + 1)。
     * 4. 初始化：dp[0] = 0；其他位置初始化为 Integer.MAX_VALUE。
     * 5. 遍历顺序：完全背包，平方数外层，容量正序。
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i * i <= n; i++) {
            for (int j = i * i; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }

        return dp[n];
    }

}
