/**
 * @program: suanfa
 * @ClassName: H_322
 * @description: 322. 零钱兑换
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_322 {

    /**
     * 代码随想录动规五部曲：
     * 1. dp数组含义：dp[j] 表示凑成金额 j 所需的最少硬币数。
     * 2. 递推公式：当前硬币 coins[i] 可以重复使用，
     *    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1)。
     * 3. 初始化：dp[0] = 0；其他金额初始化为 Integer.MAX_VALUE，表示暂时无法凑成。
     * 4. 遍历顺序：完全背包，物品外层，容量正序，允许同一硬币重复使用。
     * 5. 解题思路：求最小个数，转移前要判断 dp[j - coins[i]] 是否可达，避免无穷大加一。
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    static void main() {
        H_322 h_322 = new H_322();
        int[] coins = {2};
        int amount = 3;
        int result = h_322.coinChange(coins, amount);
        System.out.println(result);
    }

}
