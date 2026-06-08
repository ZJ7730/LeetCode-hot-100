/**
 * @program: suanfa
 * @ClassName: H_230
 * @description: 230. 二叉搜索树中第 K 小的元素
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
            result = root.val;
            return;
        }
        inorder(root.right);
    }
}
