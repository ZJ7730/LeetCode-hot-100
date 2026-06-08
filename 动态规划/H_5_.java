/**
 * @program: suanfa
 * @ClassName: H_5
 * @description: 5. 最长回文子串
 * @author: zhoujie07
 * @create: 2026-06-04
 **/
public class H_5_ {

    /**
     * 代码随想录动规五部曲：
     * 1. dp数组含义：dp[i][j] 表示真实下标区间 s[i..j] 是否是回文子串。
     * 2. 递推公式：
     *    - 如果 s[i] != s[j]，dp[i][j] = false。
     *    - 如果 s[i] == s[j] 且 j - i <= 1，长度为 1 或 2，dp[i][j] = true。
     *    - 如果 s[i] == s[j] 且 j - i > 1，dp[i][j] = dp[i + 1][j - 1]。
     * 3. 初始化：boolean 默认 false，长度为 1 的情况在 j - i <= 1 中处理。
     * 4. 遍历顺序：dp[i][j] 依赖 dp[i + 1][j - 1]，所以 i 从后往前，j 从 i 往后。
     * 5. 解题思路：遍历所有区间，遇到回文区间就用 j - i + 1 更新最长长度和起点。
     */
    public String longestPalindrome(String s) {
        char[] charArray = s.toCharArray();
        boolean[][] dp = new boolean[charArray.length][charArray.length];

        int start = 0;
        int len = 0;

        for (int i = charArray.length - 1; i >= 0; i--) {
            for (int j = i; j < charArray.length; j++) {
                if (charArray[i] == charArray[j]) {
                    if (j - i <= 1) {
                        dp[i][j] = true;
                        int length = j - i + 1;
                        if (length > len) {
                            start = i;
                            len = length;
                        }
                    } else if (j - i > 1) {
                        dp[i][j] = dp[i + 1][j - 1];
                        if (dp[i][j]) {
                            int length = j - i + 1;
                            if (length > len) {
                                start = i;
                                len = length;
                            }
                        }
                    }
                }
            }
        }
        return s.substring(start, start + len);
    }
}

