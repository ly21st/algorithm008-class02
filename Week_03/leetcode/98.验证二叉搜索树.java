import java.util.Deque;
import java.util.LinkedList;

import javax.swing.tree.TreeNode;

import sun.launcher.resources.launcher;

/*
 * @lc app=leetcode.cn id=98 lang=java
 *
 * [98] 验证二叉搜索树
 *
 * https://leetcode-cn.com/problems/validate-binary-search-tree/description/
 *
 * algorithms
 * Medium (29.72%)
 * Likes:    553
 * Dislikes: 0
 * Total Accepted:    108.6K
 * Total Submissions: 354K
 * Testcase Example:  '[2,1,3]'
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 
 * 假设一个二叉搜索树具有如下特征：
 * 
 * 
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * ⁠   2
 * ⁠  / \
 * ⁠ 1   3
 * 输出: true
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * ⁠   5
 * ⁠  / \
 * ⁠ 1   4
 * / \
 * 3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4 。
 * 
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
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return helper(root);
    }

    public boolean helper(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        double preVal = Double.MIN_VALUE;
        TreeNode cur = root;
        while (cur != null || !deque.isEmpty()) {
            while (cur != null) {
                deque.addFirst(cur);
                cur = cur.left;
            }
            cur = deque.removeFirst();
            if (cur.val <= preVal) return false;
            preVal = cur.val;
            cur = cur.right;
        }
        return true;
    }
}
// @lc code=end


// 根据二叉搜索树的定义，深度遍历
// class Solution {
//     public boolean isValidBST(TreeNode root) {
//         if (root == null) return true;
//         return helper(root, null, null);
//     }

//     public boolean helper(TreeNode root, Integer lower, Integer upper) {
//         if (root == null) return true;
//         if ((lower != null && root.val <= lower) || (upper !=null && root.val >= upper)) return false;
//         if (root.left != null && !helper(root.left, lower, root.val)) return false;
//         if (root.right != null && !helper(root.right, root.val, upper)) return false;
//         return true;
//     }
// }


