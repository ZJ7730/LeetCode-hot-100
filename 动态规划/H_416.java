/**
 * @program: suanfa
 * @ClassName: H_416
 * @description: 416. 分割等和子集
 * @author: zhoujie07
 * @create: 2026-05-25
 **/
public class H_416 {

    /**
     * 代码随想录动规五部曲：
     * 1. dp数组含义：dp[j] 表示容量为 j 的背包，最多可以凑出的子集和。
     * 2. 递推公式：每个 nums[i] 只能使用一次，
     *    dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i])。
     * 3. 初始化：dp[j] 默认 0，表示还没有凑出任何和。
     * 4. 遍历顺序：01背包一维数组，先遍历物品，再倒序遍历容量，避免同一个数被重复使用。
     * 5. 解题思路：总和为奇数直接失败；否则看能否从数组中选出一些数，使它们的和等于 sum / 2。
     */
    public boolean canPartition(int[] nums) {
        int target = 0;
        for (int i = 0; i < nums.length; i++) {
            target += nums[i];
        }
        if (target % 2 != 0) {
            return false;
        }
        target = target / 2;

        int[] dp = new int[target + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i] = 0;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }

        return dp[target] == target;
    }

}
