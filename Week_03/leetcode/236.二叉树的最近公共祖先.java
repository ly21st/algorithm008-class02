import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=236 lang=java
 *
 * [236] 二叉树的最近公共祖先
 *
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 *
 * algorithms
 * Medium (61.25%)
 * Likes:    478
 * Dislikes: 0
 * Total Accepted:    63.9K
 * Total Submissions: 103.5K
 * Testcase Example:  '[3,5,1,6,2,0,8,null,null,7,4]\n5\n1'
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x
 * 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * 
 * 
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 
 * 
 * 示例 2:
 * 
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 * 
 * 
 * 
 * 
 * 说明:
 * 
 * 
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
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
    TreeNode target;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        isAncestor(root, p, q);
        return target;
    }

    public boolean isAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;

        int left = isAncestor(root.left, p, q)? 1 : 0;
        int right = isAncestor(root.right, p, q)? 1 : 0;
        int mid = (root.val == p.val || root.val == q.val)? 1 : 0;

        if (left + right + mid >= 2) {
            target = root;
        }

        return left + right + mid >= 1;
    }
}


// @lc code=end

// 分别找出根节点到两个节点的路径，然后从后往前找两个节点的公共节点
*/
// class Solution {
//     boolean found = false;
//     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//         if (root == null) return null;
//         LinkedList<TreeNode> ans = new LinkedList<TreeNode>();
//         LinkedList<TreeNode> ans1 = new LinkedList<TreeNode>();
//         dfs(root, p, ans, ans1, 0);

//         found = false;
//         ans.clear();
//         LinkedList<TreeNode> ans2 = new LinkedList<TreeNode>();
//         dfs(root, q, ans, ans2, 0);

//         int len1 = ans1.size();
//         int len2 = ans2.size();
//         for (int i = len1 - 1; i >= 0; i--) {
//             for (int j = len2 - 1; j >= 0; j--) {
//                 TreeNode node1 = ans1.get(i);
//                 TreeNode node2 = ans2.get(j);
//                 if (node1 == node2) return node1;
//             }
//         }
//         return root;
//     }

//     public void dfs(TreeNode root, TreeNode t, LinkedList<TreeNode> ans, LinkedList<TreeNode> res, int i) {
//         if (root.val == t.val) {
//             ans.add(i, t);
//             found = true; 
//             for (int j = 0; j <= i; j++) {
//                 res.add(ans.get(j));
//             }
//             return;
//         }
//         if (found) return;
//         ans.add(i, root);
//         if (root.left != null) {
//             dfs(root.left, t, ans, res, i + 1);
//         }
//         if (root.right != null) {
//             dfs(root.right, t, ans, res, i + 1);
//         }
//     }
// }



// 求出根节点到p所经过的所有路径p1，从下往上遍历p1,寻找从经过的节点出发，是否能找到另一个节点q。
// class Solution {
//     class NodeWrap {
//         LinkedList<TreeNode> path = new LinkedList<>();
//         boolean found = false;
//     }

//     boolean foundNode = false;

//     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//         if (root == null)
//             return null;
//         LinkedList<TreeNode> ans = new LinkedList<TreeNode>();
//         NodeWrap nodeWrap = new NodeWrap();
//         searchPath(root, p, ans, nodeWrap, 0);

//         for (TreeNode node : nodeWrap.path) {
//             searchNode(node, q);
//             if (foundNode) return node;
//         }
//         return root;
//     }

//     public void searchPath(TreeNode root, TreeNode t, LinkedList<TreeNode> ans, NodeWrap nodeWrap, int i) {
//         if (root.val == t.val) {
//             ans.add(i, t);
//             nodeWrap.found = true; 
//             for (int j = 0; j <= i; j++) {
//                 nodeWrap.path.addFirst(ans.get(j));
//             }
//             return;
//         }
//         if (nodeWrap.found) return;
//         ans.add(i, root);
//         if (root.left != null) {
//             searchPath(root.left, t, ans, nodeWrap, i + 1);
//         }
//         if (root.right != null) {
//             searchPath(root.right, t, ans, nodeWrap, i + 1);
//         }
//     }

//     public void searchNode(TreeNode root, TreeNode t) {
//         if (root.val == t.val) {
//             foundNode = true; 
//             return;
//         }
//         if (foundNode) return;
//         if (root.left != null) {
//             searchNode(root.left, t);
//         }
//         if (root.right != null) {
//             searchNode(root.right, t);
//         }
//     }
// }




// 代码收藏，存储父节点的迭代
// class Solution {

//     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

//         // Stack for tree traversal
//         Deque<TreeNode> stack = new ArrayDeque<>();

//         // HashMap for parent pointers
//         Map<TreeNode, TreeNode> parent = new HashMap<>();

//         parent.put(root, null);
//         stack.push(root);

//         // Iterate until we find both the nodes p and q
//         while (!parent.containsKey(p) || !parent.containsKey(q)) {

//             TreeNode node = stack.pop();

//             // While traversing the tree, keep saving the parent pointers.
//             if (node.left != null) {
//                 parent.put(node.left, node);
//                 stack.push(node.left);
//             }
//             if (node.right != null) {
//                 parent.put(node.right, node);
//                 stack.push(node.right);
//             }
//         }

//         // Ancestors set() for node p.
//         Set<TreeNode> ancestors = new HashSet<>();

//         // Process all ancestors for node p using parent pointers.
//         while (p != null) {
//             ancestors.add(p);
//             p = parent.get(p);
//         }

//         // The first ancestor of q which appears in
//         // p's ancestor set() is their lowest common ancestor.
//         while (!ancestors.contains(q))
//             q = parent.get(q);
//         return q;
//     }

// }



// 代码收藏，无父子针的迭代
// class Solution {

//     // Three static flags to keep track of post-order traversal.

//     // Both left and right traversal pending for a node.
//     // Indicates the nodes children are yet to be traversed.
//     private static int BOTH_PENDING = 2;

//     // Left traversal done.
//     private static int LEFT_DONE = 1;

//     // Both left and right traversal done for a node.
//     // Indicates the node can be popped off the stack.
//     private static int BOTH_DONE = 0;

//     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

//         Stack<Pair<TreeNode, Integer>> stack = new Stack<Pair<TreeNode, Integer>>();

//         // Initialize the stack with the root node.
//         stack.push(new Pair<TreeNode, Integer>(root, Solution.BOTH_PENDING));

//         // This flag is set when either one of p or q is found.
//         boolean one_node_found = false;

//         // This is used to keep track of the LCA.
//         TreeNode LCA = null;

//         // Child node
//         TreeNode child_node = null;

//         // We do a post order traversal of the binary tree using stack
//         while (!stack.isEmpty()) {

//             Pair<TreeNode, Integer> top = stack.peek();
//             TreeNode parent_node = top.getKey();
//             int parent_state = top.getValue();

//             // If the parent_state is not equal to BOTH_DONE,
//             // this means the parent_node can't be popped off yet.
//             if (parent_state != Solution.BOTH_DONE) {

//                 // If both child traversals are pending
//                 if (parent_state == Solution.BOTH_PENDING) {

//                     // Check if the current parent_node is either p or q.
//                     if (parent_node == p || parent_node == q) {

//                         // If one_node_found was set already, this means we have found
//                         // both the nodes.
//                         if (one_node_found) {
//                             return LCA;
//                         } else {
//                             // Otherwise, set one_node_found to True,
//                             // to mark one of p and q is found.
//                             one_node_found = true;

//                             // Save the current top element of stack as the LCA.
//                             LCA = stack.peek().getKey();
//                         }
//                     }

//                     // If both pending, traverse the left child first
//                     child_node = parent_node.left;
//                 } else {
//                     // traverse right child
//                     child_node = parent_node.right;
//                 }

//                 // Update the node state at the top of the stack
//                 // Since we have visited one more child.
//                 stack.pop();
//                 stack.push(new Pair<TreeNode, Integer>(parent_node, parent_state - 1));

//                 // Add the child node to the stack for traversal.
//                 if (child_node != null) {
//                     stack.push(new Pair<TreeNode, Integer>(child_node, Solution.BOTH_PENDING));
//                 }
//             } else {

//                 // If the parent_state of the node is both done,
//                 // the top node could be popped off the stack.
//                 // Update the LCA node to be the next top node.
//                 if (LCA == stack.pop().getKey() && one_node_found) {
//                     LCA = stack.peek().getKey();
//                 }

//             }
//         }

//         return null;
//     }
// }


