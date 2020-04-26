import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=75 lang=java
 *
 * [75] 颜色分类
 */

// @lc code=start
class Solution {
    public void sortColors(int[] nums) {
        int len = nums.length;
        int p0 = 0, cur = 0, p2 = len - 1;
        while (cur <= p2) {
            if (nums[cur] == 0) {
                int tmp = nums[p0];
                nums[p0++] = nums[cur];
                nums[cur++] = tmp;
            } else if (nums[cur] == 2) {
                int tmp = nums[p2];
                nums[p2--] = nums[cur];
                nums[cur] = tmp;
            } else {
                cur++;
            }
        }
    }
}
// @lc code=end


// 暴力法
// class Solution {
//     public void sortColors(int[] nums) {
//         Map<Integer, Integer> colorsMap = new HashMap<Integer, Integer>();
//         int len = nums.length;
//         for (int i = 0; i < len; i++) {
//             if (nums[i] == 0) {
//                 colorsMap.put(0, colorsMap.getOrDefault(0, 0) + 1);
//             } else if (nums[i] == 1) {
//                 colorsMap.put(1, colorsMap.getOrDefault(1, 0) + 1);
//             } else if (nums[i] == 2) {
//                 colorsMap.put(2, colorsMap.getOrDefault(2, 0) + 1);
//             }
//         }
//         int redCnt = colorsMap.getOrDefault(0, 0);
//         int i = 0;
//         for (i = 0; i < redCnt; i++) {
//             nums[i] = 0;
//         }
//         int whiteCnt = colorsMap.getOrDefault(1, 0);
//         for (; i < redCnt + whiteCnt; i++) {
//             nums[i] = 1;
//         }
//         int blueCnt = colorsMap.getOrDefault(2, 0);
//         for (; i < len; i++) {
//             nums[i] = 2;
//         }
//     }
// }