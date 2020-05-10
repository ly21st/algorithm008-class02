import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=144 lang=java
 *
 * [144] 二叉树的前序遍历
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

 // 逆向入栈出栈法
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        TreeNode node;
        Deque<TreeNode> deque = new LinkedList<TreeNode>();
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            node = deque.removeFirst();
            res.add(node.val);
            if (node.right != null) {
                deque.addFirst(node.right);
            }
            if (node.left != null) {
                deque.addFirst(node.left);
            }

        }
    }
}
// @lc code=end


// 递归方法
// class Solution {
//     public List<Integer> preorderTraversal(TreeNode root) {
//         List<Integer> res = new ArrayList<Integer>();
//         helper(root, res);
//         return res;
//     }

//     public void helper(TreeNode root, List<Integer> res) {
//         if (root == null) return;
//         res.add(root.val);
//         helper(root.left, res);
//         helper(root.right, res);
//     }
// }



// 循环迭代，时间复杂度O(n),空间复杂度O(n)
// class Solution {
//     public List<Integer> preorderTraversal(TreeNode root) {
//         List<Integer> res = new ArrayList<Integer>();
//         helper(root, res);
//         return res;
//     }

//     public void helper(TreeNode root, List<Integer> res) {
//         if (root == null) return;
//         Deque<TreeNode> deque = new LinkedList<TreeNode>();
//         TreeNode cur;
//         deque.addFirst(root);
//         while (!deque.isEmpty()) {
//             cur = deque.removeFirst();
//             res.add(cur.val);
//             if (cur.right != null) {
//                 deque.addFirst(cur.right);
//             }
//             if (cur.left != null) {
//                 deque.addFirst(cur.left);
//             }
//         }
//     }
// }


// 莫里斯遍历
// class Solution {
//     public List<Integer> preorderTraversal(TreeNode root) {
//         List<Integer> res = new ArrayList<Integer>();
//         helper(root, res);
//         return res;
//     }

//     public void helper(TreeNode root, List<Integer> res) {
//         if (root == null)
//             return;
//         TreeNode node = root;
//         TreeNode predecessor;

//         while (node != null) {
//             if (node.left == null) {
//                 res.add(node.val);
//                 node = node.right;
//             } else {
//                 predecessor = node.left;
//                 while (predecessor.right != null && predecessor.right != node) {
//                     predecessor = predecessor.right;
//                 }
//                 if (predecessor.right == null) {
//                     res.add(node.val);
//                     predecessor.right = node;
//                     node = node.left;
//                 } else {
//                     predecessor.right = null;
//                     node = node.right;
//                 }
//             }
//         }
//     }
// }



// 递归方法
// class Solution {
//     public List<Integer> preorderTraversal(TreeNode root) {
//         List<Integer> res = new ArrayList<Integer>();
//         helper(root, res);
//         return res;
//     }

//     public void helper(TreeNode root, List<Integer> res) {
//         if (root == null)
//             return;
//         res.add(root.val);
//         if (root.left != null) helper(root.left, res);
//         if (root.right != null) helper(root.right, res);
//     }
// }



// 用栈循环迭代方式
// class Solution {
//     public List<Integer> preorderTraversal(TreeNode root) {
//         List<Integer> res = new ArrayList<Integer>();
//         helper(root, res);
//         return res;
//     }

//     public void helper(TreeNode root, List<Integer> res) {
//         if (root == null)
//             return;
//         Deque<TreeNode> deque = new LinkedList<TreeNode>();
//         TreeNode cur;
//         deque.addFirst(root);
//         while (!deque.isEmpty()) {
//             cur = deque.removeFirst();
//             res.add(cur.val);
//             if (cur.right != null) {
//                 deque.addFirst(cur.right);
//             }
//             if (cur.left != null) {
//                 deque.addFirst(cur.left);
//             }
//         }
//     }
// }


// 莫里斯遍历
// class Solution {
//     public List<Integer> preorderTraversal(TreeNode root) {
//         List<Integer> res = new ArrayList<Integer>();
//         helper(root, res);
//         return res;
//     }

//     public void helper(TreeNode root, List<Integer> res) {
//         if (root == null)
//             return;
//         TreeNode node = root;
//         TreeNode predecessor;
//         while (node != null) {
//             if (node.left == null) {
//                 res.add(node.val);
//                 node = node.right;
//                 continue;
//             }
//             predecessor = node.left;
//             while (predecessor.right != null && predecessor.right != node) {
//                 predecessor = predecessor.right;
//             }
//             if (predecessor.right == null) {
//                 res.add(node.val);
//                 predecessor.right = node;
//                 node = node.left;
//                 continue;
//             }
//             node = node.right;
//             predecessor.right = null;
//         }
//     }
// }