import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=94 lang=java
 *
 * [94] 二叉树的中序遍历
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> deque =new LinkedList<>();
        deque.add(root);
        TreeNode node = root;
        while (!deque.isEmpty()) {
            while (node.left != null) {
                deque.add(node.left);
                node = node.left;
            }
            node = deque.peekLast();
            res.add(node.val);
            deque.removeLast();
            if (node.right != null) {
                deque.add(node.right);
                node = node.right;
            }
        }
        return res;
    }
}
// @lc code=end




// 递归方式求解
// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
//         List<Integer> res = new ArrayList<Integer>();
//         if (root == null) {
//             return res;
//         }
//         if (root.left != null) {
//             res.addAll(inorderTraversal(root.left));    
//         }
//         res.add(root.val);
//         if (root.right != null) {
//             res.addAll(inorderTraversal(root.right));
//         }
//         return res;
//     }
// }