/**
 * @program: suanfa
 * @ClassName: H_104
 * @description: 二叉树的最大深度
 *
 * 面试笔记：
 * - 题目定位：求根节点到最深叶子节点的节点数。
 * - 核心思路：递归返回当前子树最大高度。
 * - 状态含义：`leftHeight/rightHeight` 分别是左右子树高度。
 * - 复杂度：时间 O(n)，空间 O(h)。
 *
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
