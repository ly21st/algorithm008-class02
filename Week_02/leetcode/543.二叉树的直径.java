/*
 * @lc app=leetcode.cn id=543 lang=java
 *
 * [543] 二叉树的直径
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
// 求深度的同时，求节点的最大路径
class Solution {
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        maxDepth(root, 0);
        return max;
    }

    public int maxDepth(TreeNode root, int depth) {
        if (root == null) return 0; 
        depth++;
        int leftDep = maxDepth(root.left);
        int rightDep = maxDepth(root.right);
        max = Math.max(leftDep + rightDep, max);
        return Math.max(leftDep, rightDep) + 1;
    }
}
// @lc code=end



// 暴力法
// class Solution {
//     int max = 0;
//     public int diameterOfBinaryTree(TreeNode root) {
//         if (root == null) return 0;
//         int distance = maxDepth(root.left) + maxDepth(root.right);
//         if (distance > max) {
//             max = distance;
//         }
//         diameterOfBinaryTree(root.left);
//         diameterOfBinaryTree(root.right);
//         return max;
//     }

//     public int maxDepth(TreeNode root) {
//         if (root == null) return 0; 
//         return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
//     }
// }




// 求深度的同时，求节点的最大路径
// class Solution {
//     int max = 0;
//     public int diameterOfBinaryTree(TreeNode root) {
//         if (root == null) return 0;
//         maxDepth(root);
//         return max;
//     }

//     public int maxDepth(TreeNode root) {
//         if (root == null) return 0; 
//         int leftDep = maxDepth(root.left);
//         int rightDep = maxDepth(root.right);
//         max = Math.max(leftDep + rightDep, max);
//         return Math.max(leftDep, rightDep) + 1;
//     }
// }