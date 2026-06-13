/**
 * @program: suanfa
 * @ClassName: H_338
 * @description: 338. 比特位计数
 *
 * 面试笔记：
 * - 题目定位：返回 `0..n` 每个数字的二进制中 1 的个数。
 * - 核心思路：`i >> 1` 相当于去掉最低位，`i & 1` 表示最低位是否为 1。
 * - 状态含义：`dp[i]` 表示数字 `i` 的 1 的个数。
 * - 递推规则：`dp[i] = dp[i >> 1] + (i & 1)`。
 * - 复杂度：时间 O(n)，空间 O(n)。
 *
 * @author: zhoujie07
 * @create: 2026-06-05
 **/
public class H_338_ {


    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            // 去掉最低位后，剩余部分的 1 的个数 + 最低位是否为 1
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }


}
