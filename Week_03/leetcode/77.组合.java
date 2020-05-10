import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sun.java2d.HeadlessGraphicsEnvironment;

/*
 * @lc app=leetcode.cn id=77 lang=java
 *
 * [77] 组合
 *
 * https://leetcode-cn.com/problems/combinations/description/
 *
 * algorithms
 * Medium (73.44%)
 * Likes:    261
 * Dislikes: 0
 * Total Accepted:    47.8K
 * Total Submissions: 65K
 * Testcase Example:  '4\n2'
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 
 * 示例:
 * 
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * ⁠ [2,4],
 * ⁠ [3,4],
 * ⁠ [2,3],
 * ⁠ [1,2],
 * ⁠ [1,3],
 * ⁠ [1,4],
 * ]
 * 
 */

// @lc code=start
// 对网友版本实现的优化
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int []arr = new int[k];
        helper(1, n, k, res, arr, 0);
        return res;
    }

    public void helper(int b, int n, int k, List<List<Integer>> res, int[] arr, int i) {
        if (i == k) {
            List<Integer> oneRes = new ArrayList<>();
            for (int v : arr) {
                oneRes.add(v);
            }
            res.add(oneRes);
            return;
        }
        for (int j = b; j <= n - (k - i) + 1; j++) {  
            arr[i] = j;
            helper(j + 1, n, k, res, arr, i + 1);
        }
    }
}
// @lc code=end

// 官方版本的数组实现
// class Solution {
//     public List<List<Integer>> combine(int n, int k) {
//         List<List<Integer>> res = new ArrayList<List<Integer>>();
//         int []arr = new int[k];
//         helper(1, n, k, res, arr, 0);
//         return res;
//     }

//     public void helper(int b, int n, int k, List<List<Integer>> res, int[] arr, int i) {
//         if (i == k) {
//             List<Integer> oneRes = new ArrayList<>();
//             for (int v : arr) {
//                 oneRes.add(v);
//             }
//             res.add(oneRes);
//             return;
//         }
//         for (int j = b; j <= n; j++) {  
//             arr[i] = j;
//             helper(j + 1, n, k, res, arr, i + 1);
//         }
//     }
// }


// 官方代码，代码收藏
// class Solution {
//     List<List<Integer>> output = new LinkedList();
//     int n;
//     int k;
  
//     public void backtrack(int first, LinkedList<Integer> curr) {
//       // if the combination is done
//       if (curr.size() == k)
//         output.add(new LinkedList(curr));
  
//       for (int i = first; i < n + 1; ++i) {
//         // add i into the current combination
//         curr.add(i);
//         // use next integers to complete the combination
//         backtrack(i + 1, curr);
//         // backtrack
//         curr.removeLast();
//       }
//     }
  
//     public List<List<Integer>> combine(int n, int k) {
//       this.n = n;
//       this.k = k;
//       backtrack(1, new LinkedList<Integer>());
//       return output;
//     }
//   }



// 剪枝优化版本：
// public class Solution {

//     private List<List<Integer>> result = new ArrayList<>();

//     public List<List<Integer>> combine(int n, int k) {
//         if (n <= 0 || k <= 0 || n < k) {
//             return result;
//         }
//         findCombinations(n, k, 1, new Stack<>());
//         return result;
//     }

//     // p 可以声明成一个栈
//     // i 的极限值满足： n - i + 1 = (k - pre.size())。
//     // 【关键】n - i + 1 是闭区间 [i,n] 的长度。
//     // k - pre.size() 是剩下还要寻找的数的个数。
//     private void findCombinations(int n, int k, int index, Stack<Integer> p) {
//         if (p.size() == k) {
//             result.add(new ArrayList<>(p));
//             return;
//         }
//         for (int i = index; i <= n - (k - p.size()) + 1; i++) {
//             p.push(i);
//             findCombinations(n, k, i + 1, p);
//             p.pop();
//         }
//     }
// }




// 对网友版本实现的优化
// class Solution {
//     public List<List<Integer>> combine(int n, int k) {
//         List<List<Integer>> res = new ArrayList<List<Integer>>();
//         int []arr = new int[k];
//         helper(1, n, k, res, arr, 0);
//         return res;
//     }

//     public void helper(int b, int n, int k, List<List<Integer>> res, int[] arr, int i) {
//         if (i == k) {
//             List<Integer> oneRes = new ArrayList<>();
//             for (int v : arr) {
//                 oneRes.add(v);
//             }
//             res.add(oneRes);
//             return;
//         }
//         for (int j = b; j <= n - (k - i) + 1; j++) {  
//             arr[i] = j;
//             helper(j + 1, n, k, res, arr, i + 1);
//         }
//     }
// }