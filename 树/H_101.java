/**
 * @program: suanfa
 * @ClassName: H_101
 * @description: 对称二叉树
 *
 * 面试笔记：
 * - 题目定位：判断二叉树是否关于中心轴对称。
 * - 核心思路：递归同时比较左子树和右子树的镜像位置。
 * - 状态含义：外侧比较 `left.left` 和 `right.right`，内侧比较 `left.right` 和 `right.left`。
 * - 复杂度：时间 O(n)，空间 O(h)。
 *
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
