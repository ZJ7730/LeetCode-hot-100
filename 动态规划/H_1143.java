/**
 * @program: suanfa
 * @ClassName: H_1143
 * @description: 1143. 最长公共子序列
 * @author: zhoujie07
 * @create: 2026-05-28
 **/
public class H_1143 {

    /**
     * 代码随想录动规五部曲：
     * 1. dp数组含义：dp[i][j] 表示长度为 [0, i - 1] 的 text1 与长度为 [0, j - 1] 的 text2
     *    的最长公共子序列长度。等价理解：text1 前 i 个字符和 text2 前 j 个字符的 LCS 长度。
     * 2. 递推公式：
     *    - 如果 text1[i - 1] == text2[j - 1]，dp[i][j] = dp[i - 1][j - 1] + 1。
     *    - 否则 dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])。
     * 3. 初始化：dp[i][0] 和 dp[0][j] 表示和空串的 LCS，都是 0。
     * 4. 遍历顺序：从前往后遍历两个字符串前缀。
     * 5. 解题思路：子序列不要求连续，不相等时可以分别丢弃 text1 或 text2 的末尾字符。
     */
    public int longestCommonSubsequence(String text1, String text2) {
        char[] text1CharArray = text1.toCharArray();
        char[] text2CharArray = text2.toCharArray();

        int[][] dp = new int[text1CharArray.length + 1][text2CharArray.length + 1];

        for (int i = 0; i <= text1CharArray.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= text2CharArray.length; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= text1CharArray.length; i++) {
            for (int j = 1; j <= text2CharArray.length; j++) {
                if (text1CharArray[i - 1] == text2CharArray[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1CharArray.length][text2CharArray.length];
    }

}
