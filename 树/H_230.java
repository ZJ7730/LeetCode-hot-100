/**
 * @program: suanfa
 * @ClassName: H_230
 * @description: 230. 二叉搜索树中第 K 小的元素
 *
 * 面试笔记：
 * - 题目定位：在 BST 中找到第 k 小的元素。
 * - 核心思路：BST 的中序遍历结果是递增序列，因此只要中序走到第 k 个节点即可。
 * - 状态含义：`count` 记录还需要访问多少个节点；`result` 保存答案。
 * - 剪枝规则：当 `count == 0` 时，可以提前停止递归。
 * - 复杂度：时间 O(n)，空间 O(h)。
 *
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_230 {

    private int count;
    private int result;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        inorder(root);
        return result;
    }

    private void inorder(TreeNode root) {
        if (root == null || count == 0) {
            return;
        }
        inorder(root.left);
        count--;
        if (count == 0) {
            // 第 k 个节点就是答案
            result = root.val;
            return;
        }
        inorder(root.right);
    }
}
