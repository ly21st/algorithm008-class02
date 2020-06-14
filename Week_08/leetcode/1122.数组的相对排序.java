import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=1122 lang=java
 *
 * [1122] 数组的相对排序
 *
 * https://leetcode-cn.com/problems/relative-sort-array/description/
 *
 * algorithms
 * Easy (65.99%)
 * Likes:    54
 * Dislikes: 0
 * Total Accepted:    16.7K
 * Total Submissions: 25.3K
 * Testcase Example:  '[2,3,1,3,2,4,6,7,9,2,19]\n[2,1,4,3,9,6]'
 *
 * 给你两个数组，arr1 和 arr2，
 * 
 * 
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 
 * 
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1
 * 的末尾。
 * 
 * 
 * 
 * 示例：
 * 
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int []m = new int[1001];
        int []res = new int[len1];
        for (int n : arr1) {
            m[n] += 1;
        }
        int j = 0;
        for (int n : arr2) {
            while (m[n] > 0) {
                res[j++] = n;
                m[n]--;
            }
        }
        int k = j;
        for (int n : arr1) {
            if (m[n] > 0) {
                res[k++] = n;
            }
        }

        for (int i = j; i < k; i++) {
            int tmp = res[i];
            int ii = i - 1;
            for (; ii >= j; ) {
                if (res[ii] > tmp) {
                    res[ii + 1] = res[ii];
                    ii--;
                } else {
                    break;
                }
            }
            res[ii + 1] = tmp;
        }
        return res;
    }
}
// @lc code=end


// 版本1
// class Solution {
//     public int[] relativeSortArray(int[] arr1, int[] arr2) {
//         int len1 = arr1.length;
//         int len2 = arr2.length;
//         int []m1 = new int[1001];
//         int []m2 = new int[1001];
//         int []arr3 = new int[len1];
//         int []res = new int[len1];
//         int k = 0;
//         for (int i = 0; i < len2; i++) {
//             int n = arr2[i];
//             m2[n] += 1;
//         }
//         for (int i = 0; i < len1; i++) {
//             int n = arr1[i];
//             if (m2[n] > 0) {
//                 m1[n] += 1;
//             } else {
//                 arr3[k++] = n;
//             }
//         }
//         int j = 0;
//         for (int i = 0; i < len2; i++) {
//             for (int ii = 0; ii < m1[arr2[i]]; ii++) {
//                 res[j++] = arr2[i];
//             }
//         }
//         for (int i = 0; i < k; i++) {
//             int tmp = arr3[i];
//             int ii = i - 1;
//             for (; ii >= 0; ) {
//                 if (arr3[ii] > tmp) {
//                     arr3[ii + 1] = arr3[ii];
//                     ii--;
//                 } else {
//                     break;
//                 }
//             }
//             arr3[ii + 1] = tmp;
//         }
//         for (int i = 0; i < k; i++) {
//             res[j++] = arr3[i];
//         }
//         return res;
//     }
// }