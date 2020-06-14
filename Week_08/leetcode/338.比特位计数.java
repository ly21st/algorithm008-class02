/*
 * @lc app=leetcode.cn id=338 lang=java
 *
 * [338] 比特位计数
 *
 * https://leetcode-cn.com/problems/counting-bits/description/
 *
 * algorithms
 * Medium (75.20%)
 * Likes:    325
 * Dislikes: 0
 * Total Accepted:    41.4K
 * Total Submissions: 55K
 * Testcase Example:  '2'
 *
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * 
 * 示例 1:
 * 
 * 输入: 2
 * 输出: [0,1,1]
 * 
 * 示例 2:
 * 
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 
 * 进阶:
 * 
 * 
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 * 
 * 
 */

// @lc code=start
// dp[x] = dp[x / 2] + x % 2
class Solution {
    public int[] countBits(int num) {
        int []ans = new int[num + 1];
        int i = 0;
        int b = 1;
        while (b <= num) {
            while (i < b && i + b <= num) {
                ans[i + b] = ans[i] + 1;
                i++;
            }
            i = 0;
            b <<= 1;
        }
        return ans;
    }
}
// @lc code=end


// 暴力法
// class Solution {
//     public int[] countBits(int num) {
//         int []res = new int[num + 1];
//         for (int i = 0; i <= num; i++) {
//             res[i] = zeroCount(i);
//         }
//         return res;
//     }
//     private int zeroCount(int num) {
//         int count = 0; 
//         while (num != 0) {
//             num = num & (num - 1);
//             count++;
//         }
//         return count;
//     }
// }


// 动态规划 dp[x] = dp[x & (x-1)] + 1
// class Solution {
//     public int[] countBits(int num) {
//         int []dp = new int[num + 1];
//         for (int i = 1; i <= num; i++) {
//             dp[i] = dp[i & (i - 1)] + 1;
//         }
//         return dp;
//     }
// }



// dp[x] = dp[x / 2] + x % 2
// class Solution {
//     public int[] countBits(int num) {
//         int []dp = new int[num + 1];
//         for (int i = 1; i <= num; i++) {
//             dp[i] = dp[i >> 1] + (i & 1);
//         }
//         return dp;
//     }
// }
