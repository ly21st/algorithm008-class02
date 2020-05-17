import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=102 lang=java
 *
 * [102] 二叉树的层序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/description/
 *
 * algorithms
 * Medium (61.45%)
 * Likes:    470
 * Dislikes: 0
 * Total Accepted:    114.6K
 * Total Submissions: 185.4K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * 
 * 
 * 
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * 
 * 返回其层次遍历结果：
 * 
 * [
 * ⁠ [3],
 * ⁠ [9,20],
 * ⁠ [15,7]
 * ]
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        helper(root, res, 0);
        return res;
    }

    public void helper(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) return;
        if (level == res.size()) res.add(new ArrayList<Integer>());

        res.get(level).add(root.val);
        if (root.left != null) helper(root.left, res, level + 1);
        if (root.right != null) helper(root.right, res, level + 1);
    }
}
// @lc code=end


// 广度优先遍历方法
// class Solution {
//     public List<List<Integer>> levelOrder(TreeNode root) {
//         List<List<Integer>> res = new ArrayList<List<Integer>>();
//         helper(root, res);
//         return res;
//     }

//     public void helper(TreeNode root, List<List<Integer>> res) {
//         if (root == null) return;
//         Deque<TreeNode> deque = new LinkedList<TreeNode>();
        
//         deque.addLast(root);
//         ArrayList<Integer> list = new ArrayList<Integer>();
//         list.add(root.val);
//         res.add(list);
//         TreeNode node;
//         while (!deque.isEmpty()) {
//             Deque<TreeNode> levelDeque = new LinkedList<TreeNode>();
//             list = new ArrayList<Integer>();
//             while (!deque.isEmpty()) {
//                 node = deque.removeFirst();
//                 if (node.left == null && node.right == null) continue;
//                 if (node.left != null) {
//                     list.add(node.left.val);
//                     levelDeque.addLast(node.left);
//                 }
//                 if (node.right != null) {
//                     list.add(node.right.val);
//                     levelDeque.addLast(node.right);
//                 }  
//             }
//             if (list.isEmpty()) res.add(list);
//             deque = levelDeque;
//         }
//     }
// }


// 代码收藏

// 103.二叉树的锯齿形层次遍历

// DFS
// class Solution {
// public:
//     vector<vector<int>> res;   
//     vector<vector<int>> levelOrder(TreeNode* root) 
//     {
//         addVector(root,0);      //调用递归函数
//         return res;
//     }
  
//     void addVector(TreeNode* root,int level)
//     {
//         if(root == NULL)    return;
//         if(res.size() == level)       res.resize(level+1);    //level表示层数，也对应二维数组的第一层索引，
//         res[level].push_back(root->val);
//         addVector(root->left,level+1);
//         addVector(root->right,level+1);
//     }
// };


// BFS
// class Solution {
// public:
//     vector<vector<int>> levelOrder(TreeNode* root) {
//         vector<vector<int>> res;
        
//         if (root == NULL)   return {};  
//         queue<TreeNode*> q;     
//         q.push(root);  

//         while (!q.empty()) 
//         {  
//             vector<int>level;                //存放每一层的元素值
//             int count=q.size();             //队列大小表示当前层数的元素个数
//             while(count--)                  //count--逐个对该层元素进行处理
//             {
//             TreeNode *t=q.front();             q.pop();     
//             level.push_back(t->val);
//             if(t->left)     q.push(t->left);
//             if(t->right)    q.push(t->right);
//             }

//             res.push_back(level);           //将当层元素的vector加入res中
//         }
//         return res;
//         }
// };

