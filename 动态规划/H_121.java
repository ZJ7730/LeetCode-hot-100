/**
 * @program: suanfa
 * @ClassName: H_121
 * @description: 121. 买卖股票的最佳时机
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_121 {


    /**
     * 代码随想录动规五部曲：
     * 1. dp数组含义：
     *    - dp[i][0] 表示第 i 天持有股票时所得最大现金。
     *    - dp[i][1] 表示第 i 天不持有股票时所得最大现金。
     * 2. 递推公式：
     *    - dp[i][0] = Math.max(dp[i - 1][0], -prices[i])，继续持有，或今天第一次买入。
     *    - dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i])，继续不持有，或今天卖出。
     * 3. 初始化：dp[0][0] = -prices[0]，dp[0][1] = 0。
     * 4. 遍历顺序：从前往后，因为第 i 天状态依赖第 i - 1 天。
     * 5. 解题思路：只能买卖一次，所以买入状态只能来自今天买入，而不能带着之前卖出的利润再买。
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];

        dp[0][0] = -prices[0];
        dp[0][1] = 0;


        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
    }
}
