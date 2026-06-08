/**
 * @program: suanfa
 * @ClassName: H_111
 * @description: 111.二叉树的最小深度
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
