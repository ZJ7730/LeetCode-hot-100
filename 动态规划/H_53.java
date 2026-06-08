/**
 * @program: suanfa
 * @ClassName: H_53
 * @description: 53. 最大子数组和
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_53 {

    /**
     * 代码随想录动规五部曲：
     * 1. dp数组含义：dp[i] 表示以 nums[i] 结尾的最大连续子数组和。
     * 2. 递推公式：当前位置要么接在前一个连续子数组后面，要么从当前位置重新开始，
     *    dp[i] = Math.max(dp[i - 1] + nums[i], nums[i])。
     * 3. 初始化：dp[0] = nums[0]。
     * 4. 遍历顺序：从前往后。
     * 5. 解题思路：dp[i] 只表示“以 i 结尾”，最终答案是遍历过程中所有 dp[i] 的最大值。
     */
    public int maxSubArray(int[] nums) {
        if (nums.length <= 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
        }

        return result;
    }
}
