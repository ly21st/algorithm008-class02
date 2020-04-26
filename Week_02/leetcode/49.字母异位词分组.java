package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=49 lang=java
 *
 * [49] 字母异位词分组
 */

// @lc code=start
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (strs.length < 1)
            return result;
        Map<String, List<String>> groupMap = new HashMap<String, List<String>>();
        for (String s : strs) {
            char[] arr = s.toCharArray(); 
            Arrays.sort(arr); 
            String newStr = new String(arr);
            List<String> list = groupMap.getOrDefault(newStr, new ArrayList<String>());
            list.add(s);
            groupMap.put(newStr, list);
        }
        for (List<String> list : groupMap.values()) {
            result.add(list);
        }   
        return result;
    }
}
// @lc code=end
