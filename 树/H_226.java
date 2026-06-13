/**
 * @program: suanfa
 * @ClassName: H_226
 * @description: 反转二叉树
 *
 * 面试笔记：
 * - 题目定位：交换每个节点的左右子树。
 * - 核心思路：当前节点先交换左右孩子，再递归处理交换后的左右子树。
 * - 状态含义：`swapChildren` 用 `tmp` 临时保存左子树。
 * - 复杂度：时间 O(n)，空间 O(h)。
 *
 * @author: zhoujie07
 * @create: 2026-05-08
 **/
public class H_226 {


    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        // 先交换当前节点的左右孩子
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
