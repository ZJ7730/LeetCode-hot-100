/**
 * @program: suanfa
 * @ClassName: H_114
 * @description: 114. 二叉树展开为链表；Hot 100 分类版中同时出现在链表、树分类，源码归档在树分类
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_114 {

    /**
     * 记录按后序反向遍历时已经处理好的前一个节点。
     */
    private TreeNode prev;

    /**
     * @param root 二叉树根节点
     * @author zhoujie07
     * @description 将二叉树原地展开为先序遍历顺序的单链表
     * @date 2026-05-26
     */
    public void flatten(TreeNode root) {
        dfs(root);
    }

    /**
     * @param root 当前处理的二叉树节点
     * @author zhoujie07
     * @description 按右子树、左子树、根节点的顺序反向连接链表
     * @date 2026-05-26
     */
    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        dfs(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
