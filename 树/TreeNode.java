/**
 * @program: suanfa
 * @ClassName: TreeNode
 * @description: 树题通用二叉树节点
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class TreeNode {

    /**
     * 节点存储的整数值。
     */
    int val;

    /**
     * 左子节点引用。
     */
    TreeNode left;

    /**
     * 右子节点引用。
     */
    TreeNode right;

    /**
     * @author zhoujie07
     * @description 创建默认二叉树节点
     * @date 2026-05-26
     */
    TreeNode() {
    }

    /**
     * @param val 节点值
     * @author zhoujie07
     * @description 根据节点值创建二叉树节点
     * @date 2026-05-26
     */
    TreeNode(int val) {
        this.val = val;
    }

    /**
     * @param val 节点值
     * @param left 左子节点
     * @param right 右子节点
     * @author zhoujie07
     * @description 根据节点值和左右子节点创建二叉树节点
     * @date 2026-05-26
     */
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
