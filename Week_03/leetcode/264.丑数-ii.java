import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=264 lang=java
 *
 * [264] 丑数 II
 *
 * https://leetcode-cn.com/problems/ugly-number-ii/description/
 *
 * algorithms
 * Medium (51.09%)
 * Likes:    268
 * Dislikes: 0
 * Total Accepted:    24.2K
 * Total Submissions: 47K
 * Testcase Example:  '10'
 *
 * 编写一个程序，找出第 n 个丑数。
 * 
 * 丑数就是质因数只包含 2, 3, 5 的正整数。
 * 
 * 示例:
 * 
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 
 * 说明:  
 * 
 * 
 * 1 是丑数。
 * n 不超过1690。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int nthUglyNumber(int n) {
        if (n <= 5) return n;
        int count = 5;
        int i;
        int []arr = new int[5*n + 1];
        for (i = 1; i <= 5; i++) arr[i] = 1;
        i = 6;
        while (true) {
            if (i % 2 == 0) {
                if (arr[i/2] == 1) {
                    count++;
                    if (count == n) return i;
                    arr[i] = 1;
                    i++;
                }
            } else if (i % 3 == 0) {
                if (arr[i/3] == 1) {
                    count++;
                    if (count == n) return i;
                    arr[i] = 1;
                    i++;
                }
            } else if (i % 5 == 0) {
                if (arr[i/5] == 1) {
                    count++;
                    if (count == n) return i;
                    arr[i] = 1;
                    i++;
                }
            } else {
                i++;
            }
        }
        
    }
}
// @lc code=end

