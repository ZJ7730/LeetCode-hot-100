/**
 * @program: suanfa
 * @ClassName: H_338
 * @description: 338. 比特位计数
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_338 {
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }
}
