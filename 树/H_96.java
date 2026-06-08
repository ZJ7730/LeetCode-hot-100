/**
 * @program: suanfa
 * @ClassName: H_96
 * @description: 96. 不同的二叉搜索树
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
