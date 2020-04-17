/*
 * @lc app=leetcode.cn id=977 lang=java
 *
 * [977] 有序数组的平方
 */

// @lc code=start
class Solution {
    public int[] sortedSquares(int[] A) {
        int len = A.length;
        int i = 0; 
        int j = len - 1;
        int []result = new int[len];
        for (int t = j; t >= 0; t--) {
            int product1 = A[i] * A[i];
            int product2 = A[j] * A[j];
            if (product1 <= product2) {
                result[t] = product2;
                j--;
            } else {
                result[t] = product1;
                i++;
            }
        }
        return result;
    }
}
// @lc code=end

