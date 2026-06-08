/**
 * @program: suanfa
 * @ClassName: H_337
 * @description: 337. 打家劫舍 III
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
