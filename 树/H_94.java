import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_94
 * @description: 94. 二叉树的中序遍历
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
