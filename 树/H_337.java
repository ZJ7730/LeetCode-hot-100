/**
 * @program: suanfa
 * @ClassName: H_337
 * @description: 337. 打家劫舍 III
 *
 * 面试笔记：
 * - 题目定位：在二叉树上选择若干节点偷窃，要求不能同时偷父子节点。
 * - 核心思路：树形 DP，每个节点返回两个状态，偷当前节点和不偷当前节点。
 * - 状态含义：
 *   1. `dp[0]` 表示不偷当前节点时的最大收益。
 *   2. `dp[1]` 表示偷当前节点时的最大收益。
 * - 转移规则：偷当前节点时，左右子节点都不能偷；不偷当前节点时，左右子节点可自由选择最大值。
 * - 复杂度：时间 O(n)，空间 O(h)。
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_337 {

    /**
     *
     * dp[0] 表示不偷当前节点，dp[1] 表示偷当前节点
     */
    public int rob(TreeNode root) {
        int[] val = dfs(root);
        return Math.max(val[0], val[1]);
    }


    public int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int[] leftV = dfs(root.left);
        int[] rightV = dfs(root.right);

        int v1 = root.val + leftV[0] + rightV[0];
        int v2 = Math.max(leftV[0], leftV[1]) + Math.max(rightV[0], rightV[1]);
        return new int[]{v2, v1};
    }


}
