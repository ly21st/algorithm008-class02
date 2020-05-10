import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
 * @lc app=leetcode.cn id=297 lang=java
 *
 * [297] 二叉树的序列化与反序列化
 *
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/description/
 *
 * algorithms
 * Hard (46.19%)
 * Likes:    180
 * Dislikes: 0
 * Total Accepted:    21.9K
 * Total Submissions: 46.7K
 * Testcase Example:  '[1,2,3,null,null,4,5]'
 *
 * 
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 /
 * 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 
 * 示例: 
 * 
 * 你可以将以下二叉树：
 * 
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   3
 * ⁠    / \
 * ⁠   4   5
 * 
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode
 * 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
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
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "null,";
        String s = serializeDfs(root);
    //    System.out.println("s:" + s);
        return s;
    }

    public String serializeDfs(TreeNode root) {
        if (root == null) return "null,";
        String s;
        s = String.valueOf(root.val) + ",";
        s += serializeDfs(root.left);
        s += serializeDfs(root.right);
        return s;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String []arr = data.split(",");
        LinkedList<String> list = new LinkedList<String>(Arrays.asList(arr));
        return deserializeDfs(list);
    }

    public TreeNode deserializeDfs(LinkedList<String> list) {
        if (list.isEmpty()) return null;
        String s = list.removeFirst();
        if (s.equals("null")) return null;
        TreeNode node = new TreeNode(Integer.valueOf(s));
        node.left = deserializeDfs(list);
        node.right = deserializeDfs(list);
        return node;
    }
}
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
// @lc code=end



// 使用完全二叉树的方法
// public class Codec {

//     // Encodes a tree to a single string.
//     public String serialize(TreeNode root) {
//         if (root == null) return "";
//         int depth = depth(root);
//         int size = (int)Math.pow(2, depth) - 1;
//         String []arr = new String[size];
//         dfsSerialize(root, arr, 0, size - 1);
//         String res = arrToString(arr, size);
//     //    System.out.println("res:" + res);
//         return res;
//     }

//     // Decodes your encoded data to tree.
//     public TreeNode deserialize(String data) {
//         if ("[]".equals(data)) return null;
//         String []arr;
//         arr = stringToArr(data);
//         TreeNode root;
//         root = arrToTreeNode(arr);
//         return root;
//     }

//     int depth(TreeNode root) {
//         if (root == null) return 0;
//         int leftDept = depth(root.left);
//         int rightDepth = depth(root.right);
//         return Math.max(leftDept, rightDepth) + 1;
//     }

//     public void dfsSerialize(TreeNode root, String[] arr, int index, int maxIndex) {
//         if (root == null) return;
//         if (index > maxIndex) return;
//         arr[index] = String.valueOf(root.val);
//         if (root.left != null) {
//             dfsSerialize(root.left, arr, 2 * index + 1, maxIndex);
//         }
//         if (root.right != null) {
//             dfsSerialize(root.right, arr, 2 * index + 2, maxIndex);
//         }
//     }

//     public String arrToString(String[] arr, int n) {
//         StringBuffer sb = new StringBuffer();
//         sb.append("[");
//         while (n > 1 && arr[n-1] == null) n--;
//         for (int i = 0; i < n; i++) {
//             if (i == 0) {
//                 sb.append(arr[i]);
//                 continue;
//             } 
//             if (arr[i] == null) {
//                 sb.append(",null");
//             } else {
//                 sb.append(",").append(arr[i]);
//             }
//         }
//         sb.append("]");
//         return sb.toString();
//     }

//     public String[] stringToArr(String s) {
//         int len = s.length();
//         String dstStr = s.substring(1, len - 1);
//         return dstStr.split(",");
//     }

//     public TreeNode arrToTreeNode(String[] arr) {
//         TreeNode root = new TreeNode(Integer.valueOf(arr[0]));
//         int len = arr.length;
//         arrToTreeNodeDfs(root, arr, len, 0);
//         return root;
//     }

//     public void arrToTreeNodeDfs(TreeNode root, String[] arr, int n, int index) {
//         // 没有子节点
//         if (2 * index + 1 >= n) return;
//         if (2 * index + 2 < n && arr[2*index+1].equals("null") && arr[2*index+2].equals("null")) return;
        
//         if (!arr[2*index+1].equals("null")) {
//             TreeNode node = new TreeNode(Integer.valueOf(arr[2*index+1]));
//             root.left = node;
//             arrToTreeNodeDfs(root.left, arr, n, 2 * index + 1);
//         }

//         if (2 * index + 2 < n && !arr[2*index+2].equals("null")) {
//             TreeNode node = new TreeNode(Integer.valueOf(arr[2*index+2]));
//             root.right = node;
//             arrToTreeNodeDfs(root.right, arr, n, 2 * index + 2);
//         }
//     }
// }
