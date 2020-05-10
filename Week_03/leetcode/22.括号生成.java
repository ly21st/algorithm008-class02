import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=22 lang=java
 *
 * [22] 括号生成
 *
 * https://leetcode-cn.com/problems/generate-parentheses/description/
 *
 * algorithms
 * Medium (75.20%)
 * Likes:    935
 * Dislikes: 0
 * Total Accepted:    113.7K
 * Total Submissions: 151.2K
 * Testcase Example:  '3'
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 
 * 
 * 
 * 示例：
 * 
 * 输入：n = 3
 * 输出：[
 * ⁠      "((()))",
 * ⁠      "(()())",
 * ⁠      "(())()",
 * ⁠      "()(())",
 * ⁠      "()()()"
 * ⁠    ]
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        char []arr = new char[2*n];
        helper(0, 0, 0, n, res, arr);
        return res;
    }

    public void helper(int i, int j, int count, int n, List<String> res, char []arr) {
        if (count >= 2*n) {
            res.add(arrToString(arr));
        }
        
        if (i < n) {
            arr[count++] = '(';
            helper(i+1, j, count, n, res, arr);
            count--;
        }
        if (j < n && j < i) {
            arr[count++] = ')';
            helper(i, j + 1, count, n, res, arr);
            count--;
        }
    }

    public String arrToString(char []arr) {
        StringBuffer sb = new StringBuffer();
        for (char c : arr) {
            sb.append(c);
        }
        return sb.toString();
    }
}
// @lc code=end



//  回溯法，网友代码收藏
// class Solution {
//     public List<String> generateParenthesis(int n) {
//         List<String> ans = new ArrayList();
//         backtrack(ans, new StringBuilder(), 0, 0, n);
//         return ans;
//     }

//     public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max){
//         if (cur.length() == max * 2) {
//             ans.add(cur.toString());
//             return;
//         }

//         if (open < max) {
//             cur.append('(');
//             backtrack(ans, cur, open+1, close, max);
//             cur.deleteCharAt(cur.length() - 1);
//         }
//         if (close < open) {
//             cur.append(')');
//             backtrack(ans, cur, open, close+1, max);
//             cur.deleteCharAt(cur.length() - 1);
//         }
//     }
// }



// 按括号序列的长度递归，代码收藏
// class Solution {
//     ArrayList[] cache = new ArrayList[100];
//     public List<String> generate(int n) {
//         if (cache[n] != null) {
//             return cache[n];
//         }
//         ArrayList<String> ans = new ArrayList();
//         if (n == 0) {
//             ans.add("");
//         } else {
//             for (int c = 0; c < n; ++c)
//                 for (String left: generate(c))
//                     for (String right: generate(n - 1 - c))
//                         ans.add("(" + left + ")" + right);
//         }
//         cache[n] = ans;
//         return ans;
//     }
//     public List<String> generateParenthesis(int n) {
//         return generate(n);
//     }
// }




// 深度优先遍历，代码收藏
// import java.util.ArrayList;
// import java.util.List;

// public class Solution {

//     // 做减法

//     public List<String> generateParenthesis(int n) {
//         List<String> res = new ArrayList<>();
//         // 特判
//         if (n == 0) {
//             return res;
//         }

//         // 执行深度优先遍历，搜索可能的结果
//         dfs("", n, n, res);
//         return res;
//     }

//     /**
//      * @param curStr 当前递归得到的结果
//      * @param left   左括号还有几个可以使用
//      * @param right  右括号还有几个可以使用
//      * @param res    结果集
//      */
//     private void dfs(String curStr, int left, int right, List<String> res) {
//         // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
//         // 在递归终止的时候，直接把它添加到结果集即可，注意与「力扣」第 46 题、第 39 题区分
//         if (left == 0 && right == 0) {
//             res.add(curStr);
//             return;
//         }

//         // 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
//         if (left > right) {
//             return;
//         }

//         if (left > 0) {
//             dfs(curStr + "(", left - 1, right, res);
//         }

//         if (right > 0) {
//             dfs(curStr + ")", left, right - 1, res);
//         }
//     }
// }





// 深度优先遍历，代码收藏
// import java.util.ArrayList;
// import java.util.List;

// public class Solution {

//     // 做加法

//     public List<String> generateParenthesis(int n) {
//         List<String> res = new ArrayList<>();
//         // 特判
//         if (n == 0) {
//             return res;
//         }

//         dfs("", 0, 0, n, res);
//         return res;
//     }

//     /**
//      * @param curStr 当前递归得到的结果
//      * @param left   左括号已经用了几个
//      * @param right  右括号已经用了几个
//      * @param n      左括号、右括号一共得用几个
//      * @param res    结果集
//      */
//     private void dfs(String curStr, int left, int right, int n, List<String> res) {
//         if (left == n && right == n) {
//             res.add(curStr);
//             return;
//         }

//         // 剪枝
//         if (left < right) {
//             return;
//         }

//         if (left < n) {
//             dfs(curStr + "(", left + 1, right, n, res);
//         }
//         if (right < n) {
//             dfs(curStr + ")", left, right + 1, n, res);
//         }
//     }
// }




// 广度优先遍历，代码收藏
// public class Solution {

//     class Node {
//         /**
//          * 当前得到的字符串
//          */
//         private String res;
//         /**
//          * 剩余左括号数量
//          */
//         private int left;
//         /**
//          * 剩余右括号数量
//          */
//         private int right;

//         public Node(String str, int left, int right) {
//             this.res = str;
//             this.left = left;
//             this.right = right;
//         }
//     }

//     public List<String> generateParenthesis(int n) {
//         List<String> res = new ArrayList<>();
//         if (n == 0) {
//             return res;
//         }
//         Queue<Node> queue = new LinkedList<>();
//         queue.offer(new Node("", n, n));

//         while (!queue.isEmpty()) {

//             Node curNode = queue.poll();
//             if (curNode.left == 0 && curNode.right == 0) {
//                 res.add(curNode.res);
//             }
//             if (curNode.left > 0) {
//                 queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
//             }
//             if (curNode.right > 0 && curNode.left < curNode.right) {
//                 queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
//             }
//         }
//         return res;
//     }
// }




// 动态规划法，代码收藏
// import java.util.ArrayList;
// import java.util.List;

// public class Solution {

//     // 把结果集保存在动态规划的数组里

//     public List<String> generateParenthesis(int n) {
//         if (n == 0) {
//             return new ArrayList<>();
//         }
//         // 这里 dp 数组我们把它变成列表的样子，方便调用而已
//         List<List<String>> dp = new ArrayList<>(n);

//         List<String> dp0 = new ArrayList<>();
//         dp0.add("");
//         dp.add(dp0);

//         for (int i = 1; i <= n; i++) {
//             List<String> cur = new ArrayList<>();
//             for (int j = 0; j < i; j++) {
//                 List<String> str1 = dp.get(j);
//                 List<String> str2 = dp.get(i - 1 - j);
//                 for (String s1 : str1) {
//                     for (String s2 : str2) {
//                         // 枚举右括号的位置
//                         cur.add("(" + s1 + ")" + s2);
//                     }
//                 }
//             }
//             dp.add(cur);
//         }
//         return dp.get(n);
//     }
// }





