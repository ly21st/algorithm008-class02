/*
 * @lc app=leetcode.cn id=189 lang=java
 *
 * [189] 旋转数组
 */

// @lc code=start
public class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        for (int i = 0;i < gcd(k, len);i++){
            int idx = (k + i) % len, num = nums[i];
            while (idx != i){
                int item = nums[idx];
                nums[idx] = num;
                idx = (idx + k) % len;
                num = item;
            }
            nums[i] = num;
        }
    }
    
    private int gcd(int a, int b) {
        return a % b == 0 ? b : gcd(b % a, a);
    }
}




// @lc code=end



// public class Solution {
//     public void rotate(int[] nums, int k) {
//         k = k % nums.length;
//         int count = 0;
//         for (int start = 0; count < nums.length; start++) {
//             int current = start;
//             int prev = nums[start];
//             do {
//                 int next = (current + k) % nums.length;
//                 int temp = nums[next];
//                 nums[next] = prev;
//                 prev = temp;
//                 current = next;
//                 count++;
//             } while (start != current);
//         }
//     }
// }



// public class Solution {
//     public void rotate(int[] nums, int k) {
//         k %= nums.length;
//         reverse(nums, 0, nums.length - 1);
//         reverse(nums, 0, k - 1);
//         reverse(nums, k, nums.length - 1);
//     }
//     public void reverse(int[] nums, int start, int end) {
//         while (start < end) {
//             int temp = nums[start];
//             nums[start] = nums[end];
//             nums[end] = temp;
//             start++;
//             end--;
//         }
//     }
// }