/**
 * @program: suanfa
 * @ClassName: H_72
 * @description: 72. 编辑距离
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_72 {

    /**
     * 代码随想录动规五部曲：
     * 1. dp数组含义：dp[i][j] 表示以下标 i - 1 结尾的 word1 和以下标 j - 1 结尾的 word2
     *    的最近编辑距离。等价理解：word1 前 i 个字符转成 word2 前 j 个字符的最少操作数。
     * 2. 递推公式：
     *    - 如果 word1[i - 1] == word2[j - 1]，dp[i][j] = dp[i - 1][j - 1]。
     *    - 否则在删除、插入、替换三种操作中取最小值再加 1。
     * 3. 初始化：dp[i][0] = i，表示删除 i 次；dp[0][j] = j，表示插入 j 次。
     * 4. 遍历顺序：从前往后遍历两个字符串前缀。
     * 5. 解题思路：把 word1 转 word2 的最后一步操作拆成删除、插入、替换三种来源。
     */
    public int minDistance(String word1, String word2) {
        char[] word1CharArray = word1.toCharArray();
        char[] word2CharArray = word2.toCharArray();

        int[][] dp = new int[word1CharArray.length + 1][word2CharArray.length + 1];

        for (int i = 0; i <= word1CharArray.length; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= word2CharArray.length; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= word1CharArray.length; i++) {
            for (int j = 1; j <= word2CharArray.length; j++) {
                if (word1CharArray[i - 1] == word2CharArray[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }

        return dp[word1CharArray.length][word2CharArray.length];
    }


}
