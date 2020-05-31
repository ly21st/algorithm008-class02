import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=120 lang=java
 *
 * [120] 三角形最小路径和
 *
 * https://leetcode-cn.com/problems/triangle/description/
 *
 * algorithms
 * Medium (64.30%)
 * Likes:    398
 * Dislikes: 0
 * Total Accepted:    58.9K
 * Total Submissions: 91.1K
 * Testcase Example:  '[[2],[3,4],[6,5,7],[4,1,8,3]]'
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 
 * 
 * 
 * 例如，给定三角形：
 * 
 * [
 * ⁠    [2],
 * ⁠   [3,4],
 * ⁠  [6,5,7],
 * ⁠ [4,1,8,3]
 * ]
 * 
 * 
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 
 * 
 * 
 * 说明：
 * 
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 * 
 */

// @lc code=start
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int lines = triangle.size();
        if (lines < 1) return 0;
        if (lines == 1) return triangle.get(0).get(0);
        int min = Integer.MAX_VALUE; 
        int []cur = new int[lines];
        int []pre = new int[lines];
        pre [0] = triangle.get(0).get(0);

        for (int i = 1; i < lines; i++) {
            List<Integer> list= triangle.get(i);
            int cols = list.size();
            for (int j = 0; j < cols; j++) {
                if (j == 0) {
                    int v = pre[j] + list.get(j);
                    cur[j] = v;
                    if (i == lines - 1) min = Math.min(min,v);
                } else if (j == cols - 1) {
                    int v = pre[j - 1] + list.get(j);
                    cur[j] = v;
                    if (i == lines - 1) min = Math.min(min,v);
                } else {
                    int v = Math.min(pre[j - 1], pre[j]) + list.get(j);
                    cur[j] = v;
                    if (i == lines - 1) min = Math.min(min,v);
                }
            }
            pre = Arrays.copyOf(cur, lines);
        }
        return min;
    }
}
// @lc code=end


// 方法1
// class Solution {
//     public int minimumTotal(List<List<Integer>> triangle) {
//         int lines = triangle.size();
//         if (lines < 1) return 0;
//         if (lines == 1) return triangle.get(0).get(0);            
//         for (int i = 1; i < lines; i++) {
//             int cols = triangle.get(i).size();
//             for (int j = 0; j < cols; j++) {
//                 if (j == 0) {
//                     int min = triangle.get(i - 1).get(j) + triangle.get(i).get(j);
//                     triangle.get(i).set(j, min);
//                 } else if (j == cols - 1) {
//                     int min = triangle.get(i - 1).get(j - 1) + triangle.get(i).get(j);
//                     triangle.get(i).set(j, min);
//                 } else {
//                     int min = Math.min(triangle.get(i - 1).get(j - 1), triangle.get(i - 1).get(j)) + triangle.get(i).get(j);
//                     triangle.get(i).set(j, min);
//                 }
//             }
//         }
//         int k = triangle.get(lines - 1).size();
//         int min = Integer.MAX_VALUE; 
//         for (int i = 0; i < k; i++) {
//             min = Math.min(min, triangle.get(lines - 1).get(i));
//         }
//         return min;
//     }
// }



// 方法2
// class Solution {
//     public int minimumTotal(List<List<Integer>> triangle) {
//         int lines = triangle.size();
//         if (lines < 1) return 0;
//         if (lines == 1) return triangle.get(0).get(0);
//         int min = Integer.MAX_VALUE; 
//         for (int i = 1; i < lines; i++) {
//             List<Integer> list= triangle.get(i);
//             List<Integer> pre = triangle.get(i - 1);
//             int cols = list.size();
//             for (int j = 0; j < cols; j++) {
//                 if (j == 0) {
//                     int v = pre.get(j) + list.get(j);
//                     list.set(j, v);
//                     if (i == lines - 1) min = Math.min(min,v);
//                 } else if (j == cols - 1) {
//                     int v = pre.get(j - 1) + list.get(j);
//                     list.set(j, v);
//                     if (i == lines - 1) min = Math.min(min,v);
//                 } else {
//                     int v = Math.min(pre.get(j - 1), pre.get(j)) + list.get(j);
//                     list.set(j, v);
//                     if (i == lines - 1) min = Math.min(min,v);
//                 }
//             }
//         }
//         return min;
//     }
// }