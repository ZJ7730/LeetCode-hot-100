/**
 * @program: suanfa
 * @ClassName: H_111
 * @description: 111.二叉树的最小深度
 *
 * 面试笔记：
 * - 题目定位：求根到最近叶子节点的节点数。
 * - 核心思路：递归求左右深度，但单边为空时不能把空子树当作 0 直接参与 `min`。
 * - 状态含义：空节点返回 0；单子树为空时只取非空一侧的深度。
 * - 复杂度：时间 O(n)，空间 O(h)。
 *
 * @author: zhoujie07
 * @create: 2026-05-11
 **/
public class H_111 {

    public int minDepth(TreeNode root) {
        if (root ==null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if(root.left == null && root.right != null) {
            return 1 + right;
        }
        if(root.right == null && root.left != null) {
            return 1 + left;
        }
        return 1 + Math.min(left, right);
    }



}
