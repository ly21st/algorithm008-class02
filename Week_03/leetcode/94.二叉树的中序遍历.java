import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.TreeNode;

import javafx.util.Pair;

/*
 * @lc app=leetcode.cn id=94 lang=java
 *
 * [94] 二叉树的中序遍历
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
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        Deque<Pair<Integer, TreeNode>> deque = new LinkedList<>();
        deque.addFirst(new Pair<Integer, TreeNode>(0, root));
        Pair<Integer, TreeNode> cur;
        TreeNode node;
        while (!deque.isEmpty()) {
            cur = deque.removeFirst();
            node = cur.getValue();
            if (cur.getKey() == 1) {
                res.add(node.val);
                continue;
            }
            if (node.right != null) {
                deque.addFirst(new Pair<Integer, TreeNode>(0, node.right));
            }
            deque.addFirst(new Pair<Integer, TreeNode>(1, node));
            if (node.left != null) {
                deque.addFirst(new Pair<Integer, TreeNode>(0, node.left));
            }
        }
        return res;
    }
}

// @lc code=end


// 递归方式
// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
//         List<Integer> res = new ArrayList<Integer>();
//         helper(root, res);
//         return res;
//     }

//     public void helper(TreeNode root, List<Integer> res) {
//         if (root == null) return;
//         helper(root.left, res);
//         res.add(root.val);
//         helper(root.right, res);
//     }
// }



// 使用栈循环迭代
// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
//         List<Integer> res = new ArrayList<Integer>();
//         helper(root, res);
//         return res;
//     }

//     public void helper(TreeNode root, List<Integer> res) {
//         if (root == null) return;
//         TreeNode node = root;
//         Deque<TreeNode> deque = new LinkedList<>();
//         while (node != null || !deque.isEmpty()) {
//             while (node != null) {
//                 deque.addFirst(node);
//                 node = node.left;
//             }
//             node = deque.removeFirst();
//             res.add(node.val);
//             node = node.right;
//         }
//     }
// }



// 莫里斯遍历，破坏原来数据结构
// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
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
//                 while (predecessor.right != null) {
//                     predecessor = predecessor.right;
//                 }
//                 predecessor.right = node;
//                 TreeNode tmp = node;
//                 node = node.left;
//                 tmp.left = null;
//             }
//         }
//     }
// }



// 莫里斯遍历，不破坏原来的数据结构
// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
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
//                     predecessor.right = node;
//                     node = node.left;
//                     continue;
//                 }
//                 res.add(node.val);
//                 predecessor.right = null;
//                 node = node.right;
//             }
//         }
//     }
// }


// 用动态数组实现遍历，时间复杂度差
// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
//         //方法3的优化版：试着不用noNodes, 左右add后，将left right置为空即可
//         List<Integer> list = new ArrayList<>();
//         //1.
//         if (root == null) {
//             return list;
//         }
//         //2. 迭代
//         List<TreeNode> nodes = new ArrayList<>();
//         nodes.add(root);
//         //记录当前层是否还有分支
//         boolean hasBranch = true;
//         while (hasBranch) {
//             hasBranch = false;
//             //按层遍历
//             for (int i = 0; i < nodes.size(); i++) {
//                 TreeNode node = nodes.get(i);
//                 hasBranch = hasBranch || !(node.left == null && node.right == null);
//                 //分别将left 和right插入到node的左右
//                 if (node.left != null) {
//                     nodes.add(i++, node.left);
//                     //left置为空
//                     node.left = null;
//                 }
//                 if (node.right != null) {
//                     nodes.add(++i, node.right);
//                     //right置为空
//                     node.right = null;
//                 }
//             }
//         }
//         //3. 遍历nodes即可
//         for (TreeNode node : nodes) {
//             list.add(node.val);
//         }
//         return list;
//     }
// }



// 栈的逆向入栈法遍历
// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
//         List<Integer> res = new ArrayList<Integer>();
//         if (root == null) return res;
//         Deque<TreeNode> deque = new LinkedList<TreeNode>();
//         deque.addFirst(root);
//         TreeNode cur;
//         while (!deque.isEmpty()) {
//             cur = deque.removeFirst();
//             if (cur.left == null && cur.right == null) {
//                 res.add(cur.val);
//                 continue;
//             }
//             if (cur.right != null) {
//                 deque.addFirst(cur.right);
//                 cur.right = null;
//             }
//             deque.addFirst(cur);
//             if (cur.left != null) {
//                 deque.addFirst(cur.left);
//                 cur.left = null;
//             }
//         }
//         return res;
//     }
// }



// 颜色遍历法
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        Deque<Pair<Integer, TreeNode>> deque = new LinkedList<>();
        deque.addFirst(new Pair<Integer, TreeNode>(0, root));
        Pair<Integer, TreeNode> cur;
        TreeNode node;
        while (!deque.isEmpty()) {
            cur = deque.removeFirst();
            node = cur.getValue();
            if (cur.getKey() == 1) {
                res.add(node.val);
                continue;
            }
            if (node.right != null) {
                deque.addFirst(new Pair<Integer, TreeNode>(0, node.right));
            }
            deque.addFirst(new Pair<Integer, TreeNode>(1, node));
            if (node.left != null) {
                deque.addFirst(new Pair<Integer, TreeNode>(0, node.left));
            }
        }
        return res;
    }
}