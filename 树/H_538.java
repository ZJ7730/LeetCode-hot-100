/**
 * @program: suanfa
 * @ClassName: H_538
 * @description: 538.把二叉搜索树转换为累加树
 *
 * 面试笔记：
 * - 题目定位：把每个节点值改成原树中大于等于它的所有节点值之和。
 * - 核心思路：BST 中序是升序，反中序右、根、左就是降序，用 `prev` 累加已访问的大值。
 * - 状态含义：`prev` 保存当前节点右侧所有更大节点的累加和。
 * - 复杂度：时间 O(n)，空间 O(h)。
 *
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
        // 当前节点要加上所有比它更大的节点和
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
