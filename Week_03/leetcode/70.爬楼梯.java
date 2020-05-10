import javax.xml.stream.events.StartDocument;

/*
 * @lc app=leetcode.cn id=70 lang=java
 *
 * [70] 爬楼梯
 *
 * https://leetcode-cn.com/problems/climbing-stairs/description/
 *
 * algorithms
 * Easy (48.26%)
 * Likes:    938
 * Dislikes: 0
 * Total Accepted:    172.1K
 * Total Submissions: 356.3K
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
    public int climbStairs(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int []stairs = new int[n+1];
        stairs[1] = 1;
        stairs[2] = 2;
        return helper(n, stairs);
    }

    public int helper(int n, int[] stairs) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (stairs[n] > 0) return stairs[n];
        stairs[n] = helper(n - 2, stairs) + helper(n - 1, stairs);
        return stairs[n];
    }
     
}
// @lc code=end



// 简单递归方法
// class Solution {
//     public int climbStairs(int n) {
//         if (n == 1) return 1;
//         if (n == 2) return 2;
//         return climbStairs(n - 1) + climbStairs(n - 2);
//     }
// }


// 带有记忆数组的递归方法
// class Solution {
//     public int climbStairs(int n) {
//         int []res = new int[n+1];
//         return helper(n, res);
//     }

//     public int helper(int n, int []res) {
//         if (n == 1) return 1;
//         if (n == 2) return 2;

//         if (res[n] == 0)  {
//             res[n]= helper(n - 1, res) + helper(n - 2, res);
//         } 
//         return res[n];
//     }
// }


// 带有记忆数组的循环方法
// class Solution {
//     public int climbStairs(int n) {
//         int []res = new int[n+1];
//         return helper(n, res);
//     }

//     public int helper(int n, int []res) {
//         if (n == 1) return 1;
//         if (n == 2) return 2;

//         res[1] = 1;
//         res[2] = 2;
//         int i = 3; 
//         while (i <= n) {
//             res[i] = res[i-2] + res[i-1];
//             i++;
//         }
//         return res[n];
//     }
// }


// 不保留中间结果的循环
//class Solution {
//     public int climbStairs(int n) {
//         return helper(n);
//     }

//     public int helper(int n) {
//         if (n == 1) return 1;
//         if (n == 2) return 2;

//         int a = 1;
//         int b = 2;
//         int c; 
//         int i = 3;
//         while (i <= n) {
//             c = a + b;
//             a = b; 
//             b = c;
//             i++;
//         }
//         return b;
//     }
// }



// 动态规划法
// class Solution {
//     public int climbStairs(int n) {
//         if (n == 1) return 1;
//         if (n == 2) return 2;
//         int []res = new int[n+1];
//         return helper(0, n, res);
//     }

//     public int helper(int i, int n, int []res) {
//         if (i > n) return 0;
//         if (i == n) return 1;

//         if (res[i] == 0) {
//             res[i] = helper(i+1, n, res) + helper(i+2, n, res);
//         }
//         return res[i];
//     }
// }



// 练习
// class Solution {
//     public int climbStairs(int n) {
//         if (n == 1) return 1;
//         if (n == 2) return 2;
//         int []res = new int[n+1];
//         return helper(0, n, res);
//     }

//     public int helper(int i, int n, int []res) {
//         if (i > n) return 0;
//         if (i == n) return 1;

//         if (res[i] == 0) {
//             res[i] = helper(i+1, n, res) + helper(i+2, n, res);
//         }
//         return res[i];
//     }
// }