/**
 * @program: suanfa
 * @ClassName: H_647
 * @description: 647. 回文子串
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_647 {

    public int countSubstrings(String s) {
        char[] charArray = s.toCharArray();
        boolean[][] dp = new boolean[charArray.length][charArray.length];
        int result = 0;
        for (int i = charArray.length - 1; i >= 0; i--) {
            for (int j = i; j < charArray.length; j++) {
                if (charArray[i] == charArray[j]) {
                    if (j - i <= 1) {
                        dp[i][j] = true;
                        result++;
                    } else if (j - i > 1) {
                        dp[i][j] = dp[i + 1][j - 1];
                        if (dp[i][j]) {
                            result++;
                        }
                    }
                }
            }
        }

        return result;
    }
}
