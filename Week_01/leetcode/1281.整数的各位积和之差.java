/*
 * @lc app=leetcode.cn id=1281 lang=java
 *
 * [1281] 整数的各位积和之差
 */

// @lc code=start
class Solution {
    public int subtractProductAndSum(int n) {
        int product = 1;
        int sum = 0;
        int t;
        while (n != 0) {
            t = n % 10;
            product *= t;
            sum += t;
            n = n / 10;
        }
        return product - sum;
    }
}
// @lc code=end

