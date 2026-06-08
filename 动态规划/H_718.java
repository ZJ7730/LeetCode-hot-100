/**
 * @program: suanfa
 * @ClassName: H_718
 * @description: 718. 最长重复子数组
 * @author: zhoujie07
 * @create: 2026-06-02
 **/
public class H_718 {

    /**
     * 代码随想录动规五部曲：
     * 1. dp数组含义：dp[i][j] 表示以 nums1[i - 1] 和 nums2[j - 1] 结尾的最长公共连续子数组长度。
     *    这里 i、j 是前缀长度，所以访问数组时使用 i - 1、j - 1。
     * 2. 递推公式：如果 nums1[i - 1] == nums2[j - 1]，
     *    dp[i][j] = dp[i - 1][j - 1] + 1；否则保持默认 0。
     * 3. 初始化：多开一行一列，dp[i][0] 和 dp[0][j] 都为 0。
     * 4. 遍历顺序：从前往后遍历两个数组前缀。
     * 5. 解题思路：连续子数组一旦不相等就断开，所以只从左上角转移，并在遍历中维护最大值。
     */
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        for (int i = 0; i <= nums1.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= nums2.length; i++) {
            dp[0][i] = 0;
        }

        int result = 0;
        for (int i = 1; i<= nums1.length; i++) {
            for (int j = 1; j<=nums2.length; j++) {
                if (nums1[i-1]==nums2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                if (dp[i][j] > result) {
                    result = dp[i][j];
                }
            }
        }

        return result;
    }
}
