/*
 * @lc app=leetcode.cn id=104 lang=java
 *
 * [104] 二叉树的最大深度
 *
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/description/
 *
 * algorithms
 * Easy (72.78%)
 * Likes:    513
 * Dislikes: 0
 * Total Accepted:    162.4K
 * Total Submissions: 222.7K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * 给定一个二叉树，找出其最大深度。
 * 
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 
 * 说明: 叶子节点是指没有子节点的节点。
 * 
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * 返回它的最大深度 3 。
 * 
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
    int max = 1;
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        helper(root, 1);
        return max;
    }

    public void helper(TreeNode root, int curDept) {
        if (root.left == null && root.right == null) return;
        curDept += 1;
        if (curDept > max) max = curDept;
        if (root.left != null) helper(root.left, curDept);
        if (root.right != null) helper(root.right, curDept);
    }
}
// @lc code=end


// 递归方法1
// class Solution {
//     public int maxDepth(TreeNode root) {
//         if (root == null) return 0;
//         return helper(root);
//     }

//     public int helper(TreeNode root) {
//         if (root == null) return 0;
//         int leftDept = helper(root.left);
//         int rightDept = helper(root.right);
//         return Math.max(leftDept, rightDept) + 1;
//     }
// }