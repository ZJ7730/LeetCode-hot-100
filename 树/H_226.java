/**
 * @program: suanfa
 * @ClassName: H_226
 * @description: 反转二叉树
 * @author: zhoujie07
 * @create: 2026-05-08
 **/
public class H_226 {


    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        swapChildren(root);
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    private void swapChildren(TreeNode root) {
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
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
