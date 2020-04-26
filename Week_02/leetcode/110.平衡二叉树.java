/*
 * @lc app=leetcode.cn id=110 lang=java
 *
 * [110] 平衡二叉树
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        if (Math.abs(height(root.left) - height(root.right)) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int height(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }
}
// @lc code=end

// 递归方法
// class Solution {
//     public boolean isBalanced(TreeNode root) {
//         if (root == null)
//             return true;
//         if (Math.abs(height(root.left) - height(root.right)) > 1) {
//             return false;
//         }
//         return isBalanced(root.left) && isBalanced(root.right);
//     }

//     public int height(TreeNode root) {
//         if (root == null)
//             return 0;
//         return 1 + Math.max(height(root.left), height(root.right));
//     }
// }