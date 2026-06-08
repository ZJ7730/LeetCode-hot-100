/**
 * @program: suanfa
 * @ClassName: H_236
 * @description: 236.二叉树的最近公共祖先
 * @author: zhoujie07
 * @create: 2026-05-10
 **/
public class H_236 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);
        if (leftNode != null && rightNode != null) {
            return root;
        }
        if (leftNode == null && rightNode != null) {
            return rightNode;
        } else if (leftNode != null && rightNode == null) {
            return leftNode;
        }
        return null;


    }


}
