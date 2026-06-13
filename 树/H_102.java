import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: suanfa
 * @ClassName: H_102
 * @description: 102. 二叉树的层序遍历  https://leetcode.cn/problems/binary-tree-level-order-traversal/
 *
 * 面试笔记：
 * - 题目定位：按层返回二叉树节点值。
 * - 核心思路：用队列保存当前待处理节点，每轮先固定当前层节点数 `size`。
 * - 状态含义：`queue` 保存当前层和下一层的节点；`res` 保存当前层结果。
 * - 复杂度：时间 O(n)，空间 O(w)。
 *
 * @author: zhoujie07
 * @create: 2026-05-07
 **/
public class H_102 {


    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();

        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> res = new ArrayList<>();
            while (size-- > 0) {
                TreeNode heard = queue.poll();
                res.add(heard.val);
                if (heard.left != null) {
                    queue.add(heard.left);
                }
                if (heard.right != null) {
                    queue.add(heard.right);
                }
            }
            result.add(res);
        }

        return result;
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
