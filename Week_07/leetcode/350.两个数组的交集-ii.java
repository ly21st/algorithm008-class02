import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=350 lang=java
 *
 * [350] 两个数组的交集 II
 *
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/description/
 *
 * algorithms
 * Easy (47.68%)
 * Likes:    265
 * Dislikes: 0
 * Total Accepted:    79.7K
 * Total Submissions: 166.5K
 * Testcase Example:  '[1,2,2,1]\n[2,2]'
 *
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 
 * 示例 1:
 * 
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 
 * 
 * 示例 2:
 * 
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 
 * 说明：
 * 
 * 
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 
 * 
 * 进阶:
 * 
 * 
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * 
 * 
 */

// @lc code=start
// 双指针法
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 < len2? len1 : len2;
        int []res = new int[len];
        int k = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        for (int i = 0, j= 0; i < len1 && j < len2; ) {
            if (nums1[i] == nums2[j]) {
                res[k++] = nums1[i];
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return Arrays.copyOfRange(res, 0, k);
    }
}
// @lc code=end


// 计数法
// class Solution {
//     public int[] intersect(int[] nums1, int[] nums2) {
//         int len1 = nums1.length;
//         int len2 = nums2.length;
//         int []shortNums;
//         int []longNums;
//         List<Integer> res = new ArrayList<Integer>();
//         Map<Integer, Integer> m = new HashMap<>();
//         if (len1 < len2) {
//             shortNums = nums1;
//             longNums = nums2;
//         } else {
//             shortNums = nums2;
//             longNums = nums1;
//         }
//         for (int n : shortNums) {
//             int v = m.getOrDefault(n, 0);
//             m.put(n, v + 1);
//         }
//         for (int n : longNums) {
//             if (!m.containsKey(n)) {
//                 continue;
//             }
//             int v = m.get(n);
//             if (v > 0) {
//                 m.put(n,v - 1);
//                 res.add(n);
//             } else {
//                 continue;
//             }
//         }
//         int outputSize = res.size();
//         int []output = new int[outputSize];
//         for (int i = 0; i < outputSize; i++) {
//             output[i] = res.get(i);
//         }
//         return output;
//     }
// }