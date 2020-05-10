/*
 * @lc app=leetcode.cn id=50 lang=java
 *
 * [50] Pow(x, n)
 *
 * https://leetcode-cn.com/problems/powx-n/description/
 *
 * algorithms
 * Medium (34.37%)
 * Likes:    321
 * Dislikes: 0
 * Total Accepted:    72.4K
 * Total Submissions: 209.3K
 * Testcase Example:  '2.00000\n10'
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * 
 * 示例 1:
 * 
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 
 * 
 * 示例 2:
 * 
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 
 * 
 * 示例 3:
 * 
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2^-2 = 1/2^2 = 1/4 = 0.25
 * 
 * 说明:
 * 
 * 
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 * 
 * 
 */

// @lc code=start
class Solution {
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n > 0) return helper(x, n);
        else return 1 / helper(x, -n);
    }

    public double helper(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n % 2 == 0) {
            double s = helper(x, n / 2);
            return s * s;
        } else {
            double s = helper(x, n / 2);
            return s * s * x;
        }
    }
}
// @lc code=end



// 暴力法
// class Solution {
//     public double myPow(double x, int n) {
//         double res = 1;
//         int i = 1; 
//         if (n == 0) return 1;
//         boolean flag = false;
//         if (n < 0) {
//             n = -n;
//             flag = true;
//         }
//         if (n > 0);
//         while (i <= n) {
//             res = res * x;
//             i++;
//         }
//         if (flag) return 1 / res;
//         return res;
//     }
// }