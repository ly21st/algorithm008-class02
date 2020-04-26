import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=1200 lang=java
 *
 * [1200] 最小绝对差
 */

// @lc code=start
class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int len = arr.length;
        if (len < 2) return res;
        Arrays.sort(arr);
        int min = arr[1] - arr[0];
        List<Integer> first = new ArrayList<Integer>();
        first.add(arr[0]);
        first.add(arr[1]);
        res.add(first);
        for (int i = 2; i < len; i++) {
            if (arr[i] - arr[i - 1] < min) {
                min = arr[i] - arr[i - 1];
                res.clear();
                List<Integer> list = new ArrayList<Integer>();
                list.add(arr[i - 1]);
                list.add(arr[i]);
                res.add(list);
            } else if (arr[i] - arr[i - 1] == min) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(arr[i - 1]);
                list.add(arr[i]);
                res.add(list); 
            }
        }
        return res;
    }
}
// @lc code=end

