import java.util.Arrays;

/**
 * @program: suanfa
 * @ClassName: H_300
 * @description: 300. 最长递增子序列
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_300 {


    /**
     * 代码随想录动规五部曲：
     * 1. dp数组含义：dp[i] 表示以 nums[i] 结尾的最长递增子序列长度。
     * 2. 递推公式：枚举 j < i，如果 nums[i] > nums[j]，
     *    说明 nums[i] 可以接在以 nums[j] 结尾的递增子序列后面，
     *    dp[i] = Math.max(dp[i], dp[j] + 1)。
     * 3. 初始化：每个元素本身就是长度为 1 的递增子序列，所以 dp[i] 初始为 1。
     * 4. 遍历顺序：i 从前往后，j 枚举 i 之前的所有位置。
     * 5. 解题思路：dp[i] 只表示“以 i 结尾”，最终答案是所有 dp[i] 的最大值。
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int[] dp = new int[nums.length];
        int result = 0;
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            if (dp[i] > result) {
                result = dp[i];
            }
        }
        return result;
    }

}
