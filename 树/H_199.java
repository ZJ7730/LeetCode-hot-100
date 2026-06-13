import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: suanfa
 * @ClassName: H_199
 * @description: 199. 二叉树的右视图
 *
 * 面试笔记：
 * - 题目定位：返回二叉树从右侧看到的节点值。
 * - 核心思路：BFS 按层遍历，每一层最后一个被弹出的节点就是这一层的右视图节点。
 * - 状态含义：`size` 固定当前层节点数量；`result` 收集每层最右侧节点。
 * - 复杂度：时间 O(n)，空间 O(w)。
 *
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_199 {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    // 每层最后一个节点就是右视图节点
                    result.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return result;
    }
}
