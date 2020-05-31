import java.lang.reflect.Array;
import java.nio.channels.AcceptPendingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/*
 * @lc app=leetcode.cn id=322 lang=java
 *
 * [322] 零钱兑换
 *
 * https://leetcode-cn.com/problems/coin-change/description/
 *
 * algorithms
 * Medium (38.85%)
 * Likes:    601
 * Dislikes: 0
 * Total Accepted:    86.7K
 * Total Submissions: 218.8K
 * Testcase Example:  '[1,2,5]\n11'
 *
 * 给定不同面额的硬币 coins 和一个总金额
 * amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3 
 * 解释: 11 = 5 + 5 + 1
 * 
 * 示例 2:
 * 
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 
 * 
 * 
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 * 
 */

// @lc code=start
class Solution {
    int count = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount) {
        int len = coins.length;
        if (len < 1) return 0;
        if (amount <= 0) return 0;
        helper(coins, len, amount, 0);
        return count == Integer.MAX_VALUE? -1 : count;
    }

    public void helper(int[] coins, int n, int left, int curCnt) {
        if (left == 0) {
            if (curCnt < count) {
                count = curCnt;
                return;
            }
        }
        if (left < 0) return;
        if (curCnt >= count) return;
        for (int i = 0; i < n; i++) {
            if (coins[i] <= left) {
                helper(coins, n, left - coins[i], curCnt + 1);
            }
        }
    }
}
// @lc code=end


// 回溯法法，压测超时
// class Solution {
//     int count = Integer.MAX_VALUE;
//     public int coinChange(int[] coins, int amount) {
//         int len = coins.length;
//         if (len < 1) return 0;
//         if (amount <= 0) return 0;
//         Arrays.sort(coins);
//         helper(coins, len, amount, 0, 0);
//         return count == Integer.MAX_VALUE? -1 : count;
//     }

//     public void helper(int[] coins, int n, int amount, int total, int curCnt) {
//         if (total == amount) {
//             if (curCnt < count) {
//                 count = curCnt;
//                 return;
//             }
//         }
//         if (total > amount) return;
//         if (curCnt >= count) return;
//         for (int i = 0; i < n; i++) {
//             helper(coins, n, amount, total + coins[i], curCnt + 1);
//         }
//     }
// }



// 回溯法，压测超时
// class Solution {
//     int count = Integer.MAX_VALUE;
//     public int coinChange(int[] coins, int amount) {
//         int len = coins.length;
//         if (len < 1) return 0;
//         if (amount <= 0) return 0;
//         Arrays.sort(coins);
//         reverse(coins, len);
//         helper(coins, len, amount, 0);
//         return count == Integer.MAX_VALUE? -1 : count;
//     }

//     public void helper(int[] coins, int n, int left, int curCnt) {
//         if (left == 0) {
//             if (curCnt < count) {
//                 count = curCnt;
//                 return;
//             }
//         }
//         if (left < 0) return;
//         if (curCnt >= count) return;
//         for (int i = 0; i < n; i++) {
//             if (coins[i] <= left) {
//                 helper(coins, n, left - coins[i], curCnt + 1);
//             }
//         }
//     }

//     public void reverse(int[] coins, int n) {
//         int i = 0; 
//         int j = n - 1; 
//         while (i < j) {
//             int tmp = coins[i];
//             coins[i] = coins[j];
//             coins[j] = tmp;
//             i++;
//             j--;
//         }
//     }
// }