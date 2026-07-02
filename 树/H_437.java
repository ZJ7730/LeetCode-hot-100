import java.util.HashMap;
import java.util.Map;

/**
 * @program: suanfa
 * @ClassName: H_437
 * @description: 437. 路径总和 III
 *
 * 面试笔记：
 * - 题目定位：统计二叉树中路径和等于 targetSum 的路径数量，路径必须向下走，但不要求从根节点开始。
 * - 核心思路：把树上的路径和转换成前缀和问题。当前路径和为 curSum，如果之前出现过 curSum - targetSum，
 *   说明从那个前缀节点之后到当前节点这一段路径和就是 targetSum。
 * - Map 语义：prefixSumCount 的 key 是从根到当前路径上的某个前缀和，value 是这个前缀和出现的次数。
 * - 递归规则：进入节点时更新 curSum，先统计能和当前节点组成 targetSum 的历史前缀，再把当前前缀和加入 map。
 * - 回溯规则：左右子树递归结束后，要把当前前缀和次数减一，避免影响其他分支。
 * - 边界细节：前缀和可能超过 int 范围，所以 curSum 和 map 的 key 使用 long。
 * - 复杂度：时间 O(n)，空间 O(h)，最坏退化为 O(n)。
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_437 {

    public int pathSum(TreeNode root, int targetSum) {
        // prefixSumCount 记录当前 DFS 路径上，每个前缀和出现了几次
        Map<Long, Integer> prefixSumCount = new HashMap<>();

        // 前缀和为 0 先出现 1 次，用来处理“从根节点开始的路径刚好等于 targetSum”的情况
        prefixSumCount.put(0L, 1);

        // 从根节点开始 DFS，初始路径和为 0
        return dfs(root, 0L, targetSum, prefixSumCount);
    }


    public int dfs(TreeNode node, long curSum, int targetSum, Map<Long, Integer> prefixSumCount) {
        // 空节点不能形成路径，直接返回 0
        if (node == null) {
            return 0;
        }

        // 进入当前节点，把当前节点值加入从根到当前节点的路径和
        curSum += node.val;

        // 如果之前出现过 curSum - targetSum，
        // 说明从那个前缀之后到当前节点这一段路径和正好是 targetSum
        long sum = curSum - targetSum;
        Integer pathCount = prefixSumCount.getOrDefault(sum, 0);

        // 把当前前缀和加入 map，供左右子树继续使用
        prefixSumCount.put(curSum, prefixSumCount.getOrDefault(curSum, 0) + 1);

        // 继续统计左子树和右子树中，以后代节点结尾的合法路径数量
        pathCount += dfs(node.left, curSum, targetSum, prefixSumCount);
        pathCount += dfs(node.right, curSum, targetSum, prefixSumCount);

        // 回溯：当前节点所在路径处理完后，移除当前前缀和，避免影响兄弟分支
        prefixSumCount.put(curSum, prefixSumCount.get(curSum) - 1);

        // 返回当前子树中满足路径和的总数量
        return pathCount;
    }

}
