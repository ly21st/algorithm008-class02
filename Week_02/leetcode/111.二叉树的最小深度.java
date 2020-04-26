import java.util.Deque;
import java.util.LinkedList;

import javax.swing.tree.TreeNode;

import javafx.util.Pair;

/*
 * @lc app=leetcode.cn id=111 lang=java
 *
 * [111] 二叉树的最小深度
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int min = Integer.MAX_VALUE;
        Deque<Pair<TreeNode, Integer>> deque = new LinkedList<Pair<TreeNode, Integer>>();
        Pair<TreeNode, Integer> elem = new Pair<>(root, 1);
        deque.addFirst(elem);
        while (!deque.isEmpty()) {
            elem = deque.removeFirst();
            TreeNode cur = elem.getKey();
            Integer depth = elem.getValue();
            if (cur.left == null && cur.right == null) {
                min = Math.min(min, depth);
            }
            if (cur.right != null) deque.addFirst(new Pair<TreeNode, Integer>(cur.right, depth + 1));
            if (cur.left != null) deque.addFirst(new Pair<TreeNode, Integer>(cur.left, depth + 1));
        }
        return min;
    }
}
// @lc code=end

// 递归方法
// class Solution {
//     public int minDepth(TreeNode root) {
//         if (root == null)
//             return 0;
//         int leftDepth;
//         int rifghtDepth;
//         if (root.left == null && root.right == null)
//             return 1;
//         if (root.left == null)
//             return 1 + minDepth(root.right);
//         if (root.right == null)
//             return 1 + minDepth(root.left);
//         leftDepth = minDepth(root.left);
//         rifghtDepth = minDepth(root.right);
//         return 1 + Math.min(leftDepth, rifghtDepth);
//     }
// }


// 递归方法2，顺推法
// class Solution {
//     int min = Integer.MAX_VALUE;

//     public int minDepth(TreeNode root) {
//         if (root == null)
//             return 0;
//         helper(root, 0);
//         return min;
//     }

//     public void helper(TreeNode root, int curDep) {
//         if (root == null)
//             return;
//         curDep++;
//         if (root.left == null && root.right == null) {
//             min = Math.min(min, curDep);
//         }
//         helper(root.left, curDep);
//         helper(root.right, curDep);
//     }
// }