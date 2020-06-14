package problems-0008;

import java.util.Arrays;
import java.util.PriorityQueue;



class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        int len = arr.length;
        if (len <= k) {
            return arr;
        }
        int []res = new int[k];
        if (k <=0) return res;
        PriorityQueue<Integer> q = new PriorityQueue<>();

}



// 先排序，再返回前k个数
// class Solution {
//     public int[] getLeastNumbers(int[] arr, int k) {
//         Arrays.sort(arr);
//         int len = arr.length;
//         if (len <= k) {
//             return arr;
//         }
//         return Arrays.copyOfRange(arr, 0, k);
//     }
// }


// n * k 复杂度
// class Solution {
//     public int[] getLeastNumbers(int[] arr, int k) {
//         int len = arr.length;
//         if (len <= k) {
//             return arr;
//         }
//         int []res = new int[k];
//         if (k <=0) return res;
//         int max = Integer.MIN_VALUE;
//         int maxi = 0;
//         for (int i = 0; i < k; i++) {
//             res[i] = arr[i];
//             if (arr[i] > max) {
//                 max = arr[i];
//                 maxi = i;
//             }
//         }
//         for (int i = k; i < len; i++) {
//             if (arr[i] >= max) {
//                 continue;
//             }
//             res[maxi] = arr[i];
//             max = res[0];
//             for (int j = 0; j < k; j++) {
//                 if (res[j] > max) {
//                     max = res[j];
//                     maxi = j;
//                 }
//             }
//         }
//         return res;
//     }
// }