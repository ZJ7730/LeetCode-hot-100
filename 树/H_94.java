import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_94
 * @description: 94. 二叉树的中序遍历
 *
 * 面试笔记：
 * - 题目定位：返回二叉树的中序遍历结果。
 * - 核心思路：递归顺序固定为左子树、根、右子树。
 * - 状态含义：`result` 保存遍历结果；`root == null` 是递归出口。
 * - 复杂度：时间 O(n)，空间 O(h)。
 *
 * @author: zhoujie07
 * @create: 2026-05-07
 **/
public class H_94 {

    List<Integer> result = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return result;
        }
        dfs(root);
        return result;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        dfs(root.left);
        result.add(root.val);
        dfs(root.right);
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
