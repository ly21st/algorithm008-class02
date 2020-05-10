import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=105 lang=java
 *
 * [105] 从前序与中序遍历序列构造二叉树
 *
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 *
 * algorithms
 * Medium (64.84%)
 * Likes:    426
 * Dislikes: 0
 * Total Accepted:    62.3K
 * Total Submissions: 95.7K
 * Testcase Example:  '[3,9,20,15,7]\n[9,3,15,20,7]'
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 
 * 例如，给出
 * 
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 
 * 返回如下的二叉树：
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
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
    int preIndex = 0;
    Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len1= preorder.length;
        int len2 = inorder.length;
        if (len1 < 1 || len2 < 1 || len1 != len2) return null;
        for (int i = 0; i < len1; i++) {
            indexMap.put(inorder[i], i);
        }
        return helper(preorder, inorder, 0, len1);
    }

    public TreeNode helper(int[] preorder, int[] inorder, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) return null;
        TreeNode root = new TreeNode(preorder[preIndex]);
        int inorderMid;
        inorderMid = indexMap.get(preorder[preIndex]);
        preIndex++;
        root.left = helper(preorder, inorder, leftIndex, inorderMid);
        root.right = helper(preorder, inorder, inorderMid + 1, rightIndex);
        return root;
    }
}
// @lc code=end




// 分别划分前序遍历数组与中序遍历数组
// class Solution {
//     public TreeNode buildTree(int[] preorder, int[] inorder) {
//         int len1= preorder.length;
//         int len2 = inorder.length;
//         if (len1 < 1 || len2 < 1 || len1 != len2) return null;
//         return helper(preorder, inorder, 0, len1-1, 0, len1 - 1);
//     }

//     public TreeNode helper(int[] preorder, int[] inorder, int x1, int y1, int x2, int y2) {
//         if (x1 == y1 && x2 == y2) {
//             return new TreeNode(preorder[x1]);
//         }
//         if (x1 > y1 || x2 > y2) return null;
//         TreeNode root = new TreeNode(preorder[x1]);

//         int inorderMid;
//         for (inorderMid = x2; inorderMid <= y2; inorderMid++) {
//             if (inorder[inorderMid] == preorder[x1]) {
//                 break;
//             }
//         }
//         // 没有左节点
//         if (inorderMid == x2) {
//             if (x2 + 1 <= y2) {
//                 root.right = helper(preorder, inorder, x1+1, y1, x2+1, y2);
//             }
//         } else if (inorderMid == y2) {
//             // 没有右节点
//             root.left = helper(preorder, inorder, x1+1, y1, x2, y2-1);
//         } else {
//             root.left = helper(preorder, inorder, x1+1, x1 + inorderMid - x2, x2, inorderMid-1);
//             root.right = helper(preorder, inorder, x1 + inorderMid - x2 + 1  , y1, inorderMid+1, y2);
//         }
//         return root;
//     }
// }



// 划分中序遍历数组
// class Solution {
//     int preIndex = 0;
//     public TreeNode buildTree(int[] preorder, int[] inorder) {
//         int len1= preorder.length;
//         int len2 = inorder.length;
//         if (len1 < 1 || len2 < 1 || len1 != len2) return null;
//         return helper(preorder, inorder, 0, len1);
//     }

//     public TreeNode helper(int[] preorder, int[] inorder, int leftIndex, int rightIndex) {
//         if (leftIndex == rightIndex) return null;
//         TreeNode root = new TreeNode(preorder[preIndex]);
//         int inorderMid;
//         for (inorderMid = leftIndex; inorderMid < rightIndex; inorderMid++) {
//             if (preorder[preIndex] == inorder[inorderMid]) break;
//         }
//         preIndex++;
//         root.left = helper(preorder, inorder, leftIndex, inorderMid);
//         root.right = helper(preorder, inorder, inorderMid + 1, rightIndex);
//         return root;
//     }
// }