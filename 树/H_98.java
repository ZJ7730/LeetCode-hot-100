/**
 * @program: suanfa
 * @ClassName: H_98
 * @description: 98.验证二叉搜索树
 * @author: zhoujie07
 * @create: 2026-05-10
 **/
public class H_98 {

    TreeNode pre = null;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean left = isValidBST(root.left);
        if (pre != null && pre.val>= root.val) {
            return false;
        }
        pre = root;
        boolean right = isValidBST(root.right);
        return left && right;
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
