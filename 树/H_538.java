/**
 * @program: suanfa
 * @ClassName: H_538
 * @description: 538.把二叉搜索树转换为累加树
 * @author: zhoujie07
 * @create: 2026-05-11
 **/
public class H_538 {

    int prev = 0;

    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        root.val += prev;
        prev = root.val;
        dfs(root.left);
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
