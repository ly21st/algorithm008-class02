package cn.com.leetcode.movezeroes;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void moveZeroes(int[] nums) {
        int numsLen = nums.length;
        int i = 0;
        int j;
        for (j = 0; j < numsLen; j++) {
            if (nums[j] != 0) {
                if (j != i) {
                    nums[i] = nums[j];
                    nums[j] = 0;
                }
                i++;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
