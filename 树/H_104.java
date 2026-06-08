/**
 * @program: suanfa
 * @ClassName: H_104
 * @description: 二叉树的最大深度
 * @author: zhoujie07
 * @create: 2026-05-09
 **/
public class H_104 {

    public int maxDepth(TreeNode root) {
        return dfs(root);
    }


    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = dfs(root.left);
        int rightHeight = dfs(root.right);
        int maxHeight = Math.max(leftHeight, rightHeight) + 1;
        return maxHeight;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
