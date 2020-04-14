/*
 * @lc app=leetcode.cn id=66 lang=java
 *
 * [66] 加一
 */

// @lc code=start
class Solution {
    public int[] plusOne(int[] digits) {
        int length = digits.length;
        for (int i = length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int []result = new int[length+1];
        result[0] = 1;
        for (int i = 0; i < length; i++) {
            result[i+1] = digits[i];
        }
        return result;
    }
}
// @lc code=end

