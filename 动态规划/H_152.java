/**
 * @program: suanfa
 * @ClassName: H_152
 * @description: 152. 乘积最大子数组
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_152 {
    /**
     * 代码随想录动规五部曲：
     * 1. dp数组含义：
     *    dp[i][0] 表示以 nums[i] 结尾的连续子数组的最大乘积。
     *    dp[i][1] 表示以 nums[i] 结尾的连续子数组的最小乘积。
     * 2. 递推公式：
     *    以 nums[i] 结尾时，有三种选择：
     *    - 只选 nums[i]，从当前位置重新开始。
     *    - 接在前一个最大乘积后面：dp[i - 1][0] * nums[i]。
     *    - 接在前一个最小乘积后面：dp[i - 1][1] * nums[i]。
     *    因为负数会让最大值和最小值互换，所以最大乘积和最小乘积都要维护。
     *    dp[i][0] = max(nums[i], dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i])
     *    dp[i][1] = min(nums[i], dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i])
     * 3. 初始化：dp[0][0] = nums[0]，dp[0][1] = nums[0]。
     * 4. 遍历顺序：从前往后。
     * 5. 解题思路：
     *    dp[i][0] 只表示“必须以 nums[i] 结尾”的最大乘积，最终答案不一定在最后一个位置。
     *    例如 [2,3,-2,4] 中 dp[3][0] = 4，但最大答案是前面的 [2,3] = 6，
     *    所以遍历时要用 result 记录所有 dp[i][0] 的最大值。
     */
    public int maxProduct(int[] nums) {
        int[][] dp = new int[nums.length][2];

        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            dp[i][0] = Math.max(num, Math.max(dp[i - 1][0] * num, dp[i - 1][1] * num));
            dp[i][1] = Math.min(num, Math.min(dp[i - 1][0] * num, dp[i - 1][1] * num));
            result = Math.max(result, dp[i][0]);
        }
        return result;
    }


}
