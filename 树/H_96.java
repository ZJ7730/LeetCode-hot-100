/**
 * @program: suanfa
 * @ClassName: H_96
 * @description: 96. 不同的二叉搜索树
 *
 * 面试笔记：
 * - 题目定位：给定 1..n，问能组成多少种不同结构的 BST。
 * - 核心思路：以每个数作为根节点，左边是左子树数量，右边是右子树数量，做区间 DP。
 * - 状态含义：dp[i] 表示由 `1..i` 组成的不同 BST 数量。
 * - 转移规则：枚举根节点 `j`，`dp[i] += dp[j - 1] * dp[i - j]`。
 * - 复杂度：时间 O(n^2)，空间 O(n)。
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_96 {

    /**
     * @param n 节点数量
     * @return 由 1 到 n 组成的不同二叉搜索树数量
     * @author zhoujie07
     * @description 使用卡特兰数动态规划计算不同二叉搜索树数量
     * @date 2026-05-26
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
