import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.util.Pair;


/*
 * @lc app=leetcode.cn id=127 lang=java
 *
 * [127] 单词接龙
 *
 * https://leetcode-cn.com/problems/word-ladder/description/
 *
 * algorithms
 * Medium (41.03%)
 * Likes:    302
 * Dislikes: 0
 * Total Accepted:    37.6K
 * Total Submissions: 90.6K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord
 * 的最短转换序列的长度。转换需遵循如下规则：
 * 
 * 
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 
 * 
 * 说明:
 * 
 * 
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * 输出: 5
 * 
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * ⁠    返回它的长度 5。
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * 输出: 0
 * 
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * 
 */

// @lc code=start
class Solution {
    int L;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> visited = new HashSet<String>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        L = beginWord.length();
        for (String s : wordList) {
            for (int i = 0; i < L; i++) {
                String newStr = s.substring(0, i) + '*' + s.substring(i + 1, L);
                List<String> list = map.getOrDefault(newStr, new ArrayList<String>());
                list.add(s);
                map.put(newStr, list);
            }
        }
        return helper(beginWord, endWord, map, visited);
    }

    public int helper(String beginWord, String endWord, Map<String, List<String>> map, 
                    Set<String> visited) {
        Deque<Pair<String, Integer>> deque = new LinkedList<Pair<String, Integer>>();
        deque.addLast(new Pair<String, Integer>(beginWord, 0));
        while (!deque.isEmpty()) {
            Pair<String, Integer> item = deque.removeFirst();
            String key = item.getKey();
            Integer val = item.getValue();
            if (key.equals(endWord)) {
                return val + 1;
            }
            for (int i = 0; i < L; i++) {
                String newStr = key.substring(0, i) + "*" + key.substring(i+1, L);
                List<String> list = map.getOrDefault(newStr, new ArrayList<String>());
                for (String s : list) {
                    if (!visited.contains(s)) {
                        visited.add(s);
                        deque.addLast(new Pair<String, Integer>(s, val + 1));
                    }
                }
            }
        }   
        return 0;
    }
}
// @lc code=end


// 第一版，压测超时
// class Solution {
//     int count = Integer.MAX_VALUE;
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         Set<String> visited = new HashSet<String>();
        
//         helper(beginWord, endWord, wordList, visited, 0);
//         return count == Integer.MAX_VALUE? 0 : count + 1;
//     }

//     public void helper(String curWord, String endWord, List<String> wordList, Set<String> visited, int curCount) {
//         if (curWord.equals(endWord)) {
//             if (curCount < count) {
//                 count = curCount;
//                 return;
//             }
//         }
//         if (curCount >= count) return;
//         int len = wordList.size();
//         int wordLen = curWord.length();
//         for (int i = 0; i < len; i++) {
//             String target = wordList.get(i);
//             if (visited.contains(target)) continue;
//             int diffCnt = 0;
//             for (int j = 0; j < wordLen; j++) {
//                 if (curWord.charAt(j) != target.charAt(j)) {
//                     diffCnt++;
//                     if (diffCnt >= 2) break;
//                 }
//             }
//             if (diffCnt == 1) {
//                 visited.add(target);
//                 helper(target, endWord, wordList, visited, curCount + 1);
//                 visited.remove(target);
//             }
//         }
//     }
// }




// 方法2，压测超时
// class Solution {
//     int count = Integer.MAX_VALUE;
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         Set<String> visited = new HashSet<String>();
//         Set<String> wordSet = new HashSet<String>(wordList);
//         helper(beginWord, endWord, wordSet, visited, 0);
//         return count == Integer.MAX_VALUE? 0 : count + 1;
//     }

//     public void helper(String curWord, String endWord, Set<String> wordSet, 
//                     Set<String> visited, int curCount) {
//         if (curWord.equals(endWord)) {
//             if (curCount < count) {
//                 count = curCount;
//                 return;
//             }
//         }
//         if (curCount >= count) return;
//         int wordLen = curWord.length();
//         for (String target : wordSet) {
//             if (visited.contains(target)) continue;
//             int diffCnt = 0;
//             for (int j = 0; j < wordLen; j++) {
//                 if (curWord.charAt(j) != target.charAt(j)) {
//                     diffCnt++;
//                     if (diffCnt >= 2) break;
//                 }
//             }
//             if (diffCnt == 1) {
//                 visited.add(target);
//                 helper(target, endWord, wordSet, visited, curCount + 1);
//                 visited.remove(target);
//             }
//         }
//     }
// }


// 方法3，压测超时
// class Solution {
//     int count = Integer.MAX_VALUE;
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         Set<String> wordSet = new HashSet<String>(wordList);
//         char []letters = new char[26];
//         letters[0] = 'a';
//         for (int i = 1; i < 26; i++) {
//             letters[i] = (char)(letters[i - 1] + 1); 
//         }
//         helper(beginWord, endWord, wordSet, letters, 0);
//         return count == Integer.MAX_VALUE? 0 : count + 1;
//     }

//     public void helper(String curWord, String endWord, Set<String> wordSet, char[] letters, int curCount) {
//         if (curWord.equals(endWord)) {
//             if (curCount < count) {
//                 count = curCount;
//                 return;
//             }
//         }
//         if (curCount >= count) return;
//         int wordLen = curWord.length();
//         char []curArr = curWord.toCharArray();
//         int letterLen = letters.length;
//         for (int i = 0; i < wordLen; i++) {
//             for (int j = 0; j < letterLen; j++) {
//                 char tmp = curArr[i];
//                 curArr[i] = letters[j];
//                 String target = new String(curArr);
//                 if (wordSet.contains((target))) {
//                     wordSet.remove(target);
//                     helper(target, endWord, wordSet, letters, curCount + 1);
//                     wordSet.add(target);
//                 }
//                 curArr[i] = tmp;
//             }
//         }
//     }
// }




// 优化的深度优先解法，压测超时
// class Solution {
//     int count = Integer.MAX_VALUE;
//     int L;
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         Set<String> visited = new HashSet<String>();
//         Map<String, List<String>> map = new HashMap<String, List<String>>();
//         L = beginWord.length();
//         for (String s : wordList) {
//             for (int i = 0; i < L; i++) {
//                 String newStr = s.substring(0, i) + '*' + s.substring(i + 1, L);
//                 List<String> list = map.getOrDefault(newStr, new ArrayList<String>());
//                 list.add(s);
//                 map.put(newStr, list);
//             }
//         }
//         helper(beginWord, endWord, map, visited, 0);
//         return count == Integer.MAX_VALUE? 0 : count + 1;
//     }

//     public void helper(String curWord, String endWord, Map<String, List<String>> map, 
//                     Set<String> visited, int curCount) {
//         if (curWord.equals(endWord)) {
//             if (curCount < count) {
//                 count = curCount;
//                 return;
//             }
//         }
//         if (curCount >= count) return;
//         for (int i = 0; i < L; i++) {
//             String newStr = curWord.substring(0, i) + "*" + curWord.substring(i + 1, L);
//             List<String> list = map.get(newStr);
//             if (list == null) continue;
//             for (String s : list) {
//                 if (!visited.contains(s)) {
//                     visited.add(s);
//                     helper(s, endWord, map, visited, curCount + 1);
//                     if (curCount >= count) return;
//                     visited.remove(s);
//                 }
//             }
//             if (curCount >= count) return;
//         }

//     }
// }



// 代码收藏，官方解法
// class Solution {
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
  
//       // Since all words are of same length.
//       int L = beginWord.length();
  
//       // Dictionary to hold combination of words that can be formed,
//       // from any given word. By changing one letter at a time.
//       Map<String, List<String>> allComboDict = new HashMap<>();
  
//       wordList.forEach(
//           word -> {
//             for (int i = 0; i < L; i++) {
//               // Key is the generic word
//               // Value is a list of words which have the same intermediate generic word.
//               String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
//               List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
//               transformations.add(word);
//               allComboDict.put(newWord, transformations);
//             }
//           });
  
//       // Queue for BFS
//       Queue<Pair<String, Integer>> Q = new LinkedList<>();
//       Q.add(new Pair(beginWord, 1));
  
//       // Visited to make sure we don't repeat processing same word.
//       Map<String, Boolean> visited = new HashMap<>();
//       visited.put(beginWord, true);
  
//       while (!Q.isEmpty()) {
//         Pair<String, Integer> node = Q.remove();
//         String word = node.getKey();
//         int level = node.getValue();
//         for (int i = 0; i < L; i++) {
  
//           // Intermediate words for current word
//           String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
  
//           // Next states are all the words which share the same intermediate state.
//           for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
//             // If at any point if we find what we are looking for
//             // i.e. the end word - we can return with the answer.
//             if (adjacentWord.equals(endWord)) {
//               return level + 1;
//             }
//             // Otherwise, add it to the BFS Queue. Also mark it visited
//             if (!visited.containsKey(adjacentWord)) {
//               visited.put(adjacentWord, true);
//               Q.add(new Pair(adjacentWord, level + 1));
//             }
//           }
//         }
//       }
  
//       return 0;
//     }
//   }
  
  