/**
 * @program: suanfa
 * @ClassName: H_5
 * @description: 5. 最长回文子串
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
