/**
 * @program: suanfa
 * @ClassName: H_338
 * @description: 338. 比特位计数
 *
 * 面试笔记：
 * - 题目定位：给定 n，返回 0 到 n 每个数的二进制 1 的个数。
 * - 状态定义：dp[i] 表示数字 i 的二进制中 1 的个数。
 * - 转移规则：i >> 1 相当于去掉最低位，(i & 1) 表示最低位是否为 1。
 *   所以 dp[i] = dp[i >> 1] + (i & 1)。
 * - 计算顺序：从 1 到 n 顺序递推即可，因为当前状态只依赖更小的状态。
 * - 复杂度：时间 O(n)，空间 O(n)。
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_338 {
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
