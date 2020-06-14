/*
 * @lc app=leetcode.cn id=231 lang=java
 *
 * [231] 2的幂
 *
 * https://leetcode-cn.com/problems/power-of-two/description/
 *
 * algorithms
 * Easy (48.10%)
 * Likes:    203
 * Dislikes: 0
 * Total Accepted:    62.6K
 * Total Submissions: 130K
 * Testcase Example:  '1'
 *
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 * 
 * 示例 1:
 * 
 * 输入: 1
 * 输出: true
 * 解释: 2^0 = 1
 * 
 * 示例 2:
 * 
 * 输入: 16
 * 输出: true
 * 解释: 2^4 = 16
 * 
 * 示例 3:
 * 
 * 输入: 218
 * 输出: false
 * 
 */

// @lc code=start
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }
}
// @lc code=end


// 通过位运算技巧求1的个数，再判断
// class Solution {
//     public boolean isPowerOfTwo(int n) {
//         if (n < 0) return false;
//         return oneCount(n) == 1;
//     }

//     public int oneCount(int n) {
//         int count = 0;
//         while(n != 0) {
//             n = n & (n - 1);
//             count++;
//         }
//         return count;
//     }
// }



// 先转换成字符串，再求字符'1'的个数，效率差
// class Solution {
//     public boolean isPowerOfTwo(int n) {
//         if (n < 0) return false;
//         return oneCount(n) == 1;
//     }

//     public int oneCount(int n) {
//         String s = Integer.toBinaryString(n);
//         char[] cArr = s.toCharArray();
//         int count = 0;
//         for (char c : cArr) {
//             if (c == '1') {
//                 count++;
//             }
//             if (count > 1) {
//                 return count;
//             }
//         }
//         return count;
//     }
// }