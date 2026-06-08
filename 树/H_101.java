/**
 * @program: suanfa
 * @ClassName: H_101
 * @description: 对称二叉树
 * @author: zhoujie07
 * @create: 2026-05-08
 **/
public class H_101 {


    public boolean isSymmetric(TreeNode root) {
        return dfs(root.left, root.right);
    }


    public boolean dfs(TreeNode left, TreeNode right) {
        if (right == null && left == null) {
            return true;
        } else if (left == null && right != null) {
            return false;
        } else if (right == null && left != null) {
            return false;
        } else if (right.val != left.val) {
            return false;
        }

        boolean leftEqual = dfs(left.left, right.right);
        boolean rightEqual = dfs(left.right, right.left);
        return leftEqual && rightEqual;
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
