import java.util.Deque;
import java.util.LinkedList;

import javax.swing.tree.TreeNode;

import javafx.util.Pair;
import sun.reflect.generics.tree.Tree;

/*
 * @lc app=leetcode.cn id=104 lang=java
 *
 * [104] 二叉树的最大深度
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
// 循环迭代方法
class Solution {
    int maxDepth = 0;
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        Deque<Pair<TreeNode, Integer>> deque = new LinkedList<Pair<TreeNode, Integer>>();
        Pair<TreeNode, Integer> elem = new Pair<>(root, 1);
        int max = 1;
        deque.addFirst(elem);
        while (!deque.isEmpty()) {
            elem = deque.removeFirst();
            TreeNode key = elem.getKey();
            Integer value = elem.getValue();
            max = Math.max(max, value);
            if (key.right != null) {
                deque.addFirst(new Pair<TreeNode, Integer>(key.right, value + 1));
            }
            if (key.left != null) {
                deque.addFirst(new Pair<TreeNode, Integer>(key.left, value + 1));
            }
        }
        return max;
    }

}
// @lc code=end


// 递归方法
// class Solution {
//     public int maxDepth(TreeNode root) {
//         if (root == null) return 0;
//         return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
//     }
// }




// 递归方法2

// class Solution {
//     int maxDepth = 0;
//     public int maxDepth(TreeNode root) {
//         helper(root, 0);
//         return maxDepth;
//     }

//     public void helper(TreeNode root, int depth) {
//         if (root == null) return;
//         depth++;
//         maxDepth = Math.max(maxDepth, depth);
//         helper(root.left, depth);
//         helper(root.right, depth);
//     }
// }
