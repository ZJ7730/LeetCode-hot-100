/**
 * @program: suanfa
 * @ClassName: H_543
 * @description: 543.二叉树的直径
 * @author: zhoujie07
 * @create: 2026-05-10
 **/
public class H_543 {

    int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return maxDiameter;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);
        maxDiameter = Math.max(maxDiameter, left + right);
        return Math.max(left, right) + 1;
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
