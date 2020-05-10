import java.util.Deque;
import java.util.LinkedList;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=226 lang=java
 *
 * [226] 翻转二叉树
 *
 * https://leetcode-cn.com/problems/invert-binary-tree/description/
 *
 * algorithms
 * Easy (74.72%)
 * Likes:    421
 * Dislikes: 0
 * Total Accepted:    74.8K
 * Total Submissions: 99.8K
 * Testcase Example:  '[4,2,7,1,3,6,9]'
 *
 * 翻转一棵二叉树。
 * 
 * 示例：
 * 
 * 输入：
 * 
 * ⁠    4
 * ⁠  /   \
 * ⁠ 2     7
 * ⁠/ \   / \
 * 1   3 6   9
 * 
 * 输出：
 * 
 * ⁠    4
 * ⁠  /   \
 * ⁠ 7     2
 * ⁠/ \   / \
 * 9   6 3   1
 * 
 * 备注:
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 * 
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        return helper(root);

    }

    public TreeNode helper(TreeNode root) {
        if (root == null) return null;
        Deque<TreeNode> deque = new LinkedList<TreeNode>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.removeFirst();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null) deque.addLast(node.left);
            if (node.right != null) deque.addLast(node.right);
        }
        return root;
    }
}
// @lc code=end


// 递归方法
// class Solution {
//     public TreeNode invertTree(TreeNode root) {
//         if (root == null) return null;
//         return helper(root);

//     }

//     public TreeNode helper(TreeNode root) {
//         if (root == null) return null;
//         TreeNode tmp;
//         tmp = root.left;
//         root.left = root.right;
//         root.right = tmp;
//         helper(root.left);
//         helper(root.right);
//         return root;
//     }
// }

