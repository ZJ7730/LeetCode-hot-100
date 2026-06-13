/**
 * @program: suanfa
 * @ClassName: H_5
 * @description: 5. 最长回文子串
 *
 * 面试笔记：
 * - 题目定位：在字符串中找最长的连续回文子串。
 * - 状态定义：dp[i][j] 表示 s[i..j] 是否是回文。
 * - 转移规则：
 *   1. s[i] != s[j]，直接不是回文。
 *   2. s[i] == s[j] 且区间长度 <= 2，直接是回文。
 *   3. s[i] == s[j] 且区间长度 > 2，依赖 dp[i + 1][j - 1]。
 * - 遍历顺序：i 从后往前，j 从前往后，保证内层状态先被计算。
 * - 答案维护：每次 dp[i][j] 为 true 时，用区间长度更新最优起点和长度。
 * - 复杂度：时间 O(n^2)，空间 O(n^2)。
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_5 {

    public String longestPalindrome(String s) {
        char[] charArray = s.toCharArray();
        boolean[][] dp = new boolean[charArray.length][charArray.length];

        int start = 0;
        int len = 0;

        for (int i = charArray.length - 1; i >= 0; i--) {
            for (int j = i; j < charArray.length; j++) {
                if (charArray[i] == charArray[j]) {
                    // 长度为 1 或 2 时，只要两端字符相同，就是回文
                    if (j - i <= 1) {
                        dp[i][j] = true;
                        int length = j - i + 1;
                        if (length > len) {
                            start = i;
                            len = length;
                        }
                    } else if (j - i > 1) {
                        // 长度大于 2 时，依赖内部区间是否回文
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
