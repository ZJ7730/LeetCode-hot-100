/**
 * @program: suanfa
 * @ClassName: H_617
 * @description: 合并二叉树
 *
 * 面试笔记：
 * - 题目定位：把两棵二叉树按相同位置合并。
 * - 核心思路：如果某棵树为空直接返回另一棵；都不空则新建节点，值为两节点值之和。
 * - 状态含义：`root.left/right` 分别递归合并左右子树。
 * - 复杂度：时间 O(n)，空间 O(h)。
 *
 * @author: zhoujie07
 * @create: 2026-05-09
 **/
public class H_617 {


    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        TreeNode root = new TreeNode();
        root.val = root1.val + root2.val;
        // 同位置的左右子树继续递归合并
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
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
