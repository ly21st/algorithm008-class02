import java.util.Deque;
import java.util.LinkedList;

/*
 * @lc app=leetcode.cn id=239 lang=java
 *
 * [239] 滑动窗口最大值
 */

// @lc code=start
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len < 1) return new int[]{};
        int []result = new int[len - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        result[0] = deque.peekFirst();

        for (int i = k; i < len; i++) {
            if (deque.peekFirst() == nums[i - k]) deque.removeFirst();
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            result[i - k + 1] = deque.peekFirst();
        }
        return result;
    }
}
// @lc code=end


// 方法一：暴力求解法

// class Solution {
//     public int[] maxSlidingWindow(int[] nums, int k) {
//         int len = nums.length;
//         if (len < 1) return new int[]{};
//         if (k < 1) return new int[]{};
//         if (len < k) {
//             return new int[]{};
//         }
//         int []result = new int[len - k + 1];
//         for (int i = 0; i <= len - k; i++) {
//             int max = nums[i];
//             for (int j = i; j < i + k; j++) {
//                 max = Math.max(max, nums[j]);
//             }
//             result[i] = max;
//         }
//         return result;
//     }
// }




// 优化的暴力解法
// class Solution {
//     public int[] maxSlidingWindow(int[] nums, int k) {
//         int len = nums.length;
//         if (len < 1) return new int[]{};
//         if (k < 1) return new int[]{};
//         if (len < k) {
//             return new int[]{};
//         }
//         int []result = new int[len - k + 1];
//         int max = nums[0];
//         for (int i = 1; i < k; i++) {
//             max = Math.max(max, nums[i]);
//         }
//         int t = 0;
//         result[t++] = max;
//         for (int i = k; i < len; i++) {
//             if (nums[i] >= result[t - 1]) {
//                 result[t++] = nums[i];
//                 continue;
//             } 
//             if (nums[i - k] < result[t - 1]) {
//                 result[t] = result[t - 1];
//                 t++;
//                 continue;
//             }
//             max = nums[i - k + 1];
//             for (int j = i - k + 1; j <= i; j++) {
//                 max = Math.max(max, nums[j]);
//             }
//             result[t++] = max;
//         }
//         return result;
//     }
// }



// 双端队列法

