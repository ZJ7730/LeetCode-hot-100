/**
 * @program: suanfa
 * @ClassName: H_494
 * @description: 494. 目标和
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_494 {

    /**
     * 代码随想录动规五部曲：
     * 1. dp数组含义：dp[j] 表示从 nums 中选数，凑成和 j 的方法数。
     * 2. 问题转换：设加号集合和为 x，总和为 sum，则 x - (sum - x) = target，
     *    得到 x = (target + sum) / 2，转成 01背包装满 bagSize 的方案数。
     * 3. 递推公式：dp[j] += dp[j - nums[i]]。
     * 4. 初始化：dp[0] = 1，表示什么都不选凑成 0 有一种方法。
     * 5. 遍历顺序：01背包一维数组，物品外层，容量倒序。
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (Math.abs(target) > sum) {
            return 0;
        }
        if ((target + sum) % 2 == 1) {
            return 0;
        }

        int bagSize = (target + sum) / 2;

        int[] dp = new int[bagSize + 1];
        dp[0] = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = bagSize; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[bagSize];
    }

}
