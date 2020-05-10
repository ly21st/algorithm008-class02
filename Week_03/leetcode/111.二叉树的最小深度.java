/*
 * @lc app=leetcode.cn id=111 lang=java
 *
 * [111] 二叉树的最小深度
 *
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/description/
 *
 * algorithms
 * Easy (41.98%)
 * Likes:    245
 * Dislikes: 0
 * Total Accepted:    68.9K
 * Total Submissions: 163.6K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * 给定一个二叉树，找出其最小深度。
 * 
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 
 * 说明: 叶子节点是指没有子节点的节点。
 * 
 * 示例:
 * 
 * 给定二叉树 [3,9,20,null,null,15,7],
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * 返回它的最小深度  2.
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
    int min = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        return helper(root);

    }
    public int helper(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int leftDept = Integer.MAX_VALUE;
        int rightDept = Integer.MAX_VALUE;
        if (root.left != null) {
            leftDept = helper(root.left);
        }
        if (root.right != null) {
            rightDept = helper(root.right);
        }

        return Math.min(leftDept, rightDept) + 1;

    }
}
// @lc code=end

//




// class Solution {
//     int min = Integer.MAX_VALUE;
//     public int minDepth(TreeNode root) {
//         if (root == null) return 0;
//         helper(root, 0);
//         return min;
//     }

//     public void helper(TreeNode root, int curDept) {
//         if (root.left == null && root.right == null) {
//             curDept += 1;
//             min = Math.min(min, curDept);
//             return;
//         }
//         curDept += 1;
//         if (curDept >= min) return;
//         if (curDept >= min) return;
//         if (root.left != null) {
//             helper(root.left, curDept);
//         }
//         if (root.right != null) {
//             helper(root.right, curDept);
//         }
//     }

// }