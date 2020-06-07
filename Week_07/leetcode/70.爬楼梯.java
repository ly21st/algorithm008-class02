/*
 * @lc app=leetcode.cn id=70 lang=java
 *
 * [70] 爬楼梯
 *
 * https://leetcode-cn.com/problems/climbing-stairs/description/
 *
 * algorithms
 * Easy (48.71%)
 * Likes:    1014
 * Dislikes: 0
 * Total Accepted:    198K
 * Total Submissions: 406.3K
 * Testcase Example:  '2'
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 
 * 注意：给定 n 是一个正整数。
 * 
 * 示例 1：
 * 
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 
 * 示例 2：
 * 
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * 
 * 
 */

// @lc code=start
class Solution {
    int count = 0;
    public int climbStairs(int n) {
        if (n < 3) return n;
        int []steps = new int[n + 1];
        steps[n - 1] = 1;
        steps[n - 2] = 2;
        return helper(n, 0, steps);
    }

    public int helper(int n, int i, int[] steps) {
        if (i == n) {
            return 1;
        } 
        if ( i > n) {
            return 0;
        }
        if (steps[i] > 0) {
            return steps[i];
        }
        steps[i] = helper(n, i + 1, steps) + helper(n, i + 2, steps);
        return steps[i]; 
    }
}
// @lc code=end

// 转换成斐波那契数列求解
// class Solution {
//     public int climbStairs(int n) {
//         if (n < 3) return n;
//         int a;
//         int pre = 1;
//         int cur = 2;
//         int i = 3; 
//         while (i <= n) {
//             a = pre + cur;
//             pre = cur;
//             cur = a;
//             i++;
//         }
//         return cur;
//     }
// }



// 傻递归解法，超时
// class Solution {
//     public int climbStairs(int n) {
//         return helper(n);
//     }

//     public int helper(int n) {
//         if (n < 3) return n;
//         return helper(n - 2) + helper(n - 1);
//     }
// }



// 带有记忆花数组的递归
// class Solution {
//     public int climbStairs(int n) {
//         if (n < 3) return n;
//         int []steps = new int[n + 1];
//         steps[1] = 1;
//         steps[2] = 2;
//         return helper(n, steps);
//     }

//     public int helper(int n, int[] steps) {
//         if (steps[n] > 0) {
//             return steps[n];
//         }
//         steps[n] = helper(n - 2, steps) + helper(n - 1, steps);
//         return steps[n];
//     }
// }


// 分治法
// class Solution {
//     public int climbStairs(int n) {
//         if (n < 3) return n;
//         int []steps = new int[n + 1];
//         steps[1] = 1;
//         steps[2] = 2;
//         for (int i = 3; i <= n; i++) {
//             steps[i] = steps[i - 2] + steps[i - 1];
//         }
//         return steps[n];
//     }
// }


// 动态规划法
// class Solution {
//     int count = 0;
//     public int climbStairs(int n) {
//         if (n < 3) return n;
//         int []steps = new int[n + 1];
//         steps[n - 1] = 1;
//         steps[n - 2] = 2;
//         return helper(n, 0, steps);
//     }

//     public int helper(int n, int i, int[] steps) {
//         if (i == n) {
//             return 1;
//         } 
//         if ( i > n) {
//             return 0;
//         }
//         if (steps[i] > 0) {
//             return steps[i];
//         }
//         steps[i] = helper(n, i + 1, steps) + helper(n, i + 2, steps);
//         return steps[i]; 
//     }
// }