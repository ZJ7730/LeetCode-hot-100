/**
 * @program: suanfa
 * @ClassName: H_98
 * @description: 98.验证二叉搜索树
 *
 * 面试笔记：
 * - 题目定位：判断二叉树是否满足 BST。
 * - 核心思路：中序遍历时用 `pre` 记录上一个访问节点，要求当前值严格大于前驱值。
 * - 状态含义：`pre` 是中序遍历中的前驱节点。
 * - 复杂度：时间 O(n)，空间 O(h)。
 *
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
        if (pre != null && pre.val >= root.val) {
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
