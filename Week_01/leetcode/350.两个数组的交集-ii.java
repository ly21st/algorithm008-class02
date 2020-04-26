package problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=350 lang=java
 *
 * [350] 两个数组的交集 II
 */

// @lc code=start
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int []smallArr;
        int []bigArr;
        if (nums1.length <= nums2.length) {
            smallArr = nums1;
            bigArr = nums2;
        } else {
            smallArr = nums2;
            bigArr = nums1;
        }
        Map<Integer, Integer> m = new HashMap<>();
        for (int n : smallArr) {
            int v = m.getOrDefault(n, 0);
            m.put(n, v + 1);
        }
        int k = 0;
        for (int n : bigArr) {
            int v = m.getOrDefault(n, 0);
            if (v > 0) {
                nums1[k++] = n;
                m.put(n, v - 1);
            }
        }
        return Arrays.copyOfRange(nums1, 0 , k);
    }
}
// @lc code=end





// class Solution {
//     public int[] intersect(int[] nums1, int[] nums2) {
//         int nums1Len = nums1.length;
//         int nums2Len = nums2.length;
//         Map<Integer, Integer> m1 = new HashMap<>();
//         Map<Integer, Integer> m2 = new HashMap<>();
//         for (int i = 0; i < nums1Len; i++) {
//             incrVal(m1, nums1[i]);
//         }

//         for (int i = 0; i < nums2Len; i++) {
//             incrVal(m2, nums2[i]);
//         }
//         List<Integer> result = new ArrayList<>();
//         for (Entry<Integer, Integer> entry : m1.entrySet()) {
//             Integer k, v;
//             Integer minV;
//             k = entry.getKey();
//             v = m2.get(k);
//             if (v == null) v = 0;
//             minV = Math.min(entry.getValue(), v);
//             for (int i = 1; i <= minV; i++) {
//                 result.add(k);
//             }
//         }
        
//         int size = result.size();
//         int []r = new int[size];
//         for (int i = 0; i < size; i++) {
//             r[i] = result.get(i);
//         }
//         return r;
//     }

//     void incrVal(Map<Integer, Integer> m, Integer key) {
//         Integer v = m.get(key);
//         if (v == null) {
//             m.put(key, 1);
//         } else {
//             m.put(key, v + 1);
//         }
//     }
// }