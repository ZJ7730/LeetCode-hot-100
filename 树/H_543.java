/**
 * @program: suanfa
 * @ClassName: H_543
 * @description: 543.二叉树的直径
 *
 * 面试笔记：
 * - 题目定位：求任意两个节点之间最长路径的边数。
 * - 核心思路：递归返回高度，同时用全局 `maxDiameter` 更新经过当前节点的最大路径。
 * - 状态含义：经过当前节点的直径是 `left + right`。
 * - 复杂度：时间 O(n)，空间 O(h)。
 *
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
        // 左右子树高度之和，就是经过当前节点的最长路径
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
