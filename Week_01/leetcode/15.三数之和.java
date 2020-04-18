import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=15 lang=java
 *
 * [15] 三数之和
 */

// @lc code=start
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (len < 3) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            if (nums[i] > 0) break;   
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for (int left = i + 1, right = len - 1; left < right; ) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> e = new ArrayList<>();
                    e.add(nums[i]);
                    e.add(nums[left]);
                    e.add(nums[right]);
                    result.add(e);
                    while (left < right && nums[left] == nums[left+1]) left++;
                    while (left < right && nums[right] == nums[right-1]) right--;
                    left++; 
                    right--;
                } else if(sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
// @lc code=end




// class Solution {
//     public List<List<Integer>> threeSum(int[] nums) {
//         Arrays.sort(nums);
//         List<List<Integer>> list = new ArrayList<>();
//         int len = nums.length;
//         Set<Integer> firstSet = new HashSet<>();
//         // Set<Integer> secondSet = new HashSet<>();

//         for (int i = 0; i < len - 2; i++) {
//             if (nums[i] > 0) {
//                 continue;
//             }
//             if (firstSet.contains(nums[i])) {
//                 continue;
//             }
//             firstSet.add(nums[i]);
//             Set<Integer> secondSet = new HashSet<>();
//             for (int j = i + 1; j < len - 1; j++) {
//                 if (secondSet.contains(nums[j])) {
//                     continue;
//                 }
//                 secondSet.add(nums[j]);  
//                 for (int k = j + 1; k < len; k++) {
//                     if (nums[i] + nums[j] + nums[k] == 0) {
//                         List<Integer> e = new ArrayList<>();
//                         e.add(nums[i]);
//                         e.add(nums[j]);
//                         e.add(nums[k]);
//                         list.add(e);
//                         break;
//                     }
//                 }
//             }
//         }

//         return list;
//     }
// }




// class Solution {
//     public List<List<Integer>> threeSum(int[] nums) {
//         int len = nums.length;
//         List<List<Integer>> result = new ArrayList<>();
//         if (len < 3) {
//             return result;
//         }
//         Arrays.sort(nums);
//         for (int i = 0; i < len - 2; i++) {
//             if (i > 0 && nums[i] == nums[i-1]) {
//                 continue;
//             }
//             if (nums[i] > 0) break;   
//             for (int left = i + 1, right = len - 1; left < right; ) {
//                 int sum = nums[i] + nums[left] + nums[right];
//                 if (sum == 0) {
//                     List<Integer> e = new ArrayList<>();
//                     e.add(nums[i]);
//                     e.add(nums[left]);
//                     e.add(nums[right]);
//                     result.add(e);
//                     while (left < right && nums[left] == nums[left+1]) left++;
//                     while (left < right && nums[right] == nums[right-1]) right--;
//                     left++; 
//                     right--;
//                 } else if(sum < 0) {
//                     left++;
//                 } else {
//                     right--;
//                 }
//             }
//         }
//         return result;
//     }
// }