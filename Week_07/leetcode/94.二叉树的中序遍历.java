/*
 * @lc app=leetcode.cn id=94 lang=java
 *
 * [94] 二叉树的中序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/description/
 *
 * algorithms
 * Medium (70.96%)
 * Likes:    476
 * Dislikes: 0
 * Total Accepted:    144.5K
 * Total Submissions: 203.2K
 * Testcase Example:  '[1,null,2,3]'
 *
 * 给定一个二叉树，返回它的中序 遍历。
 * 
 * 示例:
 * 
 * 输入: [1,null,2,3]
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  3
 * 
 * 输出: [1,3,2]
 * 
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            helper(root.left, res);
        }
        res.add(root.val);
        if (root.right != null) {
            helper(root.right, res);
        }
    }
}
// @lc code=end


// 递归法
// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
//         List<Integer> res = new ArrayList<>();
//         if (root == null) {
//             return res;
//         }
//         helper(root, res);
//         return res;
//     }

//     public void helper(TreeNode root, List<Integer> res) {
//         if (root == null) {
//             return;
//         }
//         if (root.left != null) {
//             helper(root.left, res);
//         }
//         res.add(root.val);
//         if (root.right != null) {
//             helper(root.right, res);
//         }
//     }
// }


// 循环迭代法
// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
//         List<Integer> res = new ArrayList<>();
//         if (root == null) {
//             return res;
//         }
//         helper(root, res);
//         return res;
//     }

//     public void helper(TreeNode root, List<Integer> res) {
//         Deque<TreeNode> deque = new LinkedList<>();
//         TreeNode cur = root;
//         while (cur != null || !deque.isEmpty()) {
//             while (cur != null) {
//                 deque.addFirst(cur);
//                 cur = cur.left;
//             }
//             cur = deque.removeFirst();
//             res.add(cur.val);
//             cur = cur.right;
//         }
//     }
// }


// 双色标记法
// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
//         List<Integer> res = new ArrayList<>();
//         if (root == null) {
//             return res;
//         }
//         helper(root, res);
//         return res;
//     }

//     public void helper(TreeNode root, List<Integer> res) {
//         Deque<TreeNode> deque = new LinkedList<>();
//         TreeNode cur = root;
//         deque.add(root);
//         while (!deque.isEmpty()) {
//             cur = deque.removeFirst();
//             if (cur.left == null && cur.right == null) {
//                 res.add(cur.val);
//                 continue;
//             }
//             if (cur.right != null) {
//                 deque.addFirst(cur.right);
//             }
//             deque.addFirst(cur);
//             if (cur.left != null) {
//                 deque.addFirst(cur.left);
//             }
//             cur.left = null;
//             cur.right = null;
//         }
//     }
// }



// 莫里斯遍历
// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
//         List<Integer> res = new ArrayList<>();
//         if (root == null) {
//             return res;
//         }
//         helper(root, res);
//         return res;
//     }

//     public void helper(TreeNode root, List<Integer> res) {
//         TreeNode predecessor;
//         while (root != null) {
//             if (root.left == null) {
//                 res.add(root.val);
//                 root = root.right;
//                 continue;
//             }
//             predecessor = root.left;
//             while (predecessor.right != null && predecessor.right != root) {
//                 predecessor = predecessor.right;
//             }
//             if (predecessor.right == null) {
//                 predecessor.right = root;
//                 root = root.left;
//             } else {
//                 predecessor.right = null;
//                 res.add(root.val);
//                 root = root.right;
//             }
//         }
//     }
// }