/*
 * @lc app=leetcode.cn id=258 lang=java
 *
 * [258] 各位相加
 */

// @lc code=start
class Solution {
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}
// @lc code=end




// 递归法求解
// class Solution {
//     public int addDigits(int num) {
//         if (num < 10) {
//             return num;
//         }
//         int sum = 0;
//         int t;
//         while (num != 0) {
//             t = num % 10;
//             num = num / 10;
//             sum += t;
//         }
//         return addDigits(sum);
//     }
// }




// class Solution {
//     public int addDigits(int num) {
//         int sum;
//         int t; 
//         while(num >= 10) {
//             sum = 0;
//             while (num != 0) {
//                 t = num % 10;
//                 num = num / 10;
//                 sum += t;
//             }
//             num = sum;
//         }
//         return num;
//     }
// }