import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=126 lang=java
 *
 * [126] 单词接龙 II
 *
 * https://leetcode-cn.com/problems/word-ladder-ii/description/
 *
 * algorithms
 * Hard (31.40%)
 * Likes:    163
 * Dislikes: 0
 * Total Accepted:    9.5K
 * Total Submissions: 30.2K
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord
 * 的最短转换序列。转换需遵循如下规则：
 * 
 * 
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 
 * 
 * 说明:
 * 
 * 
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * 输出:
 * [
 * ⁠ ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * 输出: []
 * 
 * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 * 
 */

// @lc code=start
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // 结果集
        List<List<String>> res = new ArrayList<>();
        Set<String> words = new HashSet<>(wordList);
        // 字典中不包含目标单词
        if (!words.contains(endWord)) {
            return res;
        }
        // 存放关系：每个单词可达的下层单词
        Map<String, List<String>> mapTree = new HashMap<>();
        Set<String> begin = new HashSet<>(), end = new HashSet<>();
        begin.add(beginWord);
        end.add(endWord);
        if (buildTree(words, begin, end, mapTree, true)) {
            dfs(res, mapTree, beginWord, endWord, new LinkedList<>());
        }
        return res;
    }
    
    // 双向BFS，构建每个单词的层级对应关系
    private boolean buildTree(Set<String> words, Set<String> begin, Set<String> end, Map<String, List<String>> mapTree, boolean isFront){
        if (begin.size() == 0) {
            return false;
        }
        // 始终以少的进行探索
        if (begin.size() > end.size()) {
            return buildTree(words, end, begin, mapTree, !isFront);
        }
        // 在已访问的单词集合中去除
        words.removeAll(begin);
        // 标记本层是否已到达目标单词
        boolean isMeet = false;
        // 记录本层所访问的单词
        Set<String> nextLevel = new HashSet<>();
        for (String word : begin) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char temp = chars[i];
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[i] = ch;
                    String str = String.valueOf(chars);
                    if (words.contains(str)) {
                        nextLevel.add(str);
                        // 根据访问顺序，添加层级对应关系：始终保持从上层到下层的存储存储关系
                        // true: 从上往下探索：word -> str
                        // false: 从下往上探索：str -> word（查找到的 str 是 word 上层的单词）
                        String key = isFront ? word : str;
                        String nextWord = isFront ? str : word;
                        // 判断是否遇见目标单词
                        if (end.contains(str)) {
                            isMeet = true;
                        }
                        if (!mapTree.containsKey(key)) {
                            mapTree.put(key, new ArrayList<>());
                        }
                        mapTree.get(key).add(nextWord);
                    }
                }
                chars[i] = temp;
            }
        }
        if (isMeet) {
            return true;
        }
        return buildTree(words, nextLevel, end, mapTree, isFront);
    }
    
    // DFS: 组合路径
    private void dfs (List<List<String>> res, Map<String, List<String>> mapTree, String beginWord, String endWord, LinkedList<String> list) {
        list.add(beginWord);
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(list));
            list.removeLast();
            return;
        }
        if (mapTree.containsKey(beginWord)) {
            for (String word : mapTree.get(beginWord)) {
                dfs(res, mapTree, word, endWord, list);
            }
        }
        list.removeLast();
    }
}
// @lc code=end


// 深度优先+广度优先
// class Solution {
//     public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
//         List<List<String>> ans = new ArrayList<>();
//         // 如果不含有结束单词，直接结束，不然后边会造成死循环
//         if (!wordList.contains(endWord)) {
//             return ans;
//         }
//         // 利用 BFS 得到所有的邻居节点,以及每个节点的所在层数
//         HashMap<String, Integer> distance = new HashMap<>();
//         HashMap<String, ArrayList<String>> map = new HashMap<>();
//         bfs(beginWord, endWord, wordList, map, distance);
//         ArrayList<String> temp = new ArrayList<String>();
//         // temp 用来保存当前的路径
//         temp.add(beginWord);
//         findLaddersHelper(beginWord, endWord, map, distance, temp, ans);
//         return ans;
//     }

//     private void findLaddersHelper(String beginWord, String endWord, HashMap<String, ArrayList<String>> map,
//                                 HashMap<String, Integer> distance, ArrayList<String> temp, List<List<String>> ans) {
//         if (beginWord.equals(endWord)) {
//             ans.add(new ArrayList<String>(temp));
//             return;
//         }
//         // 得到所有的下一个的节点
//         /*
//         "a"
//         "c"
//         ["a","b","c"]*/
//         //之所以是 map.getOrDefault 而不是 get，就是上边的情况 get 会出错
//         ArrayList<String> neighbors = map.getOrDefault(beginWord, new ArrayList<String>());
//         for (String neighbor : neighbors) {
//             //判断层数是否符合
//             if (distance.get(beginWord) + 1 == distance.get(neighbor)) {
//                 temp.add(neighbor);
//                 findLaddersHelper(neighbor, endWord, map, distance, temp, ans);
//                 temp.remove(temp.size() - 1);
//             }
//         }
//     }

//     public void bfs(String beginWord, String endWord, List<String> wordList, HashMap<String, ArrayList<String>> map,
//                     HashMap<String, Integer> distance) {
//         Queue<String> queue = new LinkedList<>();
//         queue.offer(beginWord);
//         distance.put(beginWord, 0);
//         boolean isFound = false;
//         int depth = 0;
//         Set<String> dict = new HashSet<>(wordList);
//         while (!queue.isEmpty()) {
//             int size = queue.size();
//             depth++;
//             for (int j = 0; j < size; j++) {
//                 String temp = queue.poll();
//                 // 一次性得到所有的下一个的节点
//                 ArrayList<String> neighbors = getNeighbors(temp, dict);
//                 map.put(temp, neighbors);
//                 for (String neighbor : neighbors) {
//                     if (!distance.containsKey(neighbor)) {
//                         distance.put(neighbor, depth);
//                         if (neighbor.equals(endWord)) {
//                             isFound = true;
//                         }
//                         queue.offer(neighbor);
//                     }

//                 }
//             }
//             if (isFound) {
//                 break;
//             }
//         }
//     }

//     private ArrayList<String> getNeighbors(String node, Set<String> dict) {
//         ArrayList<String> res = new ArrayList<String>();
//         char chs[] = node.toCharArray();

//         for (char ch = 'a'; ch <= 'z'; ch++) {
//             for (int i = 0; i < chs.length; i++) {
//                 if (chs[i] == ch)
//                     continue;
//                 char old_ch = chs[i];
//                 chs[i] = ch;
//                 if (dict.contains(String.valueOf(chs))) {
//                     res.add(String.valueOf(chs));
//                 }
//                 chs[i] = old_ch;
//             }

//         }
//         return res;
//     }
// }



// 代码收藏，广度优先算法
// class Solution {
//     public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
//         List<List<String>> ans = new ArrayList<>();
//         // 如果不含有结束单词，直接结束，不然后边会造成死循环
//         if (!wordList.contains(endWord)) {
//             return ans;
//         }
//         bfs(beginWord, endWord, wordList, ans);
//         return ans;
//     }

//     public void bfs(String beginWord, String endWord, List<String> wordList, List<List<String>> ans) {
//         Queue<List<String>> queue = new LinkedList<>();
//         List<String> path = new ArrayList<>();
//         path.add(beginWord);
//         queue.offer(path);
//         boolean isFound = false;
//         Set<String> dict = new HashSet<>(wordList);
//         Set<String> visited = new HashSet<>();
//         visited.add(beginWord);
//         while (!queue.isEmpty()) {
//             int size = queue.size();
//             Set<String> subVisited = new HashSet<>();
//             for (int j = 0; j < size; j++) {
//                 List<String> p = queue.poll();
//                 //得到当前路径的末尾单词
//                 String temp = p.get(p.size() - 1);
//                 // 一次性得到所有的下一个的节点
//                 ArrayList<String> neighbors = getNeighbors(temp, dict);
//                 for (String neighbor : neighbors) {
//                     //只考虑之前没有出现过的单词
//                     if (!visited.contains(neighbor)) {
//                         //到达结束单词
//                         if (neighbor.equals(endWord)) {
//                             isFound = true;
//                             p.add(neighbor);
//                             ans.add(new ArrayList<String>(p));
//                             p.remove(p.size() - 1);
//                               // 下面这句break加上可能会优化，黎源意见
//                               // break;    
//                         }
//                         //加入当前单词
//                         p.add(neighbor);
//                         queue.offer(new ArrayList<String>(p));
//                         p.remove(p.size() - 1);
//                         subVisited.add(neighbor);
//                     }
//                 }
//             }
//             visited.addAll(subVisited);
//             if (isFound) {
//                 break;
//             }
//         }
//     }

//     private ArrayList<String> getNeighbors(String node, Set<String> dict) {
//         ArrayList<String> res = new ArrayList<String>();
//         char chs[] = node.toCharArray();
//         for (char ch = 'a'; ch <= 'z'; ch++) {
//             for (int i = 0; i < chs.length; i++) {
//                 if (chs[i] == ch)
//                     continue;
//                 char old_ch = chs[i];
//                 chs[i] = ch;
//                 if (dict.contains(String.valueOf(chs))) {
//                     res.add(String.valueOf(chs));
//                 }
//                 chs[i] = old_ch;
//             }

//         }
//         return res;
//     }
// }




// 代码收藏
// class Solution {
//     public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
//         // 结果集
//         List<List<String>> res = new ArrayList<>();
//         Set<String> distSet = new HashSet<>(wordList);
//         // 字典中不包含目标单词
//         if (!distSet.contains(endWord)) {
//             return res;
//         }
//         // 已经访问过的单词集合：只找最短路径，所以之前出现过的单词不用出现在下一层
//         Set<String> visited = new HashSet<>();
//         // 累积每一层的结果队列
//         Queue<List<String>> queue= new LinkedList<>();
//         List<String> list = new ArrayList<>(Arrays.asList(beginWord));
//         queue.add(list);
//         visited.add(beginWord);
//         // 是否到达符合条件的层：如果该层添加的某一单词符合目标单词，则说明截止该层的所有解为最短路径，停止循环
//         boolean flag = false;
//         while (!queue.isEmpty() && !flag) {
//             // 上一层的结果队列
//             int size = queue.size();
//             // 该层添加的所有元素：每层必须在所有结果都添加完新的单词之后，再将这些单词统一添加到已使用单词集合
//             // 如果直接添加到 visited 中，会导致该层本次结果添加之后的相同添加行为失败
//             // 如：该层遇到目标单词，有两条路径都可以遇到，但是先到达的将该单词添加进 visited 中，会导致第二条路径无法添加
//             Set<String> subVisited = new HashSet<>();
//             for (int i = 0; i < size; i++) {
//                 List<String> path = queue.poll();
//                 // 获取该路径上一层的单词
//                 String word = path.get(path.size() - 1);
//                 char[] chars = word.toCharArray();
//                 // 寻找该单词的下一个符合条件的单词
//                 for (int j = 0; j < chars.length; j++) {
//                     char temp = chars[j];
//                     for (char ch = 'a'; ch <= 'z'; ch++) {
//                         chars[j] = ch;
//                         if (temp == ch) {
//                             continue;
//                         }
//                         String str = new String(chars);
//                         // 符合条件：在 wordList 中 && 之前的层没有使用过
//                         if (distSet.contains(str) && !visited.contains(str)) {
//                             // 生成新的路径
//                             List<String> pathList = new ArrayList<>(path);
//                             pathList.add(str);
//                             // 如果该单词是目标单词：将该路径添加到结果集中，查询截止到该层
//                             if (str.equals(endWord)) {
//                                 flag = true;
//                                 res.add(pathList);
//                                 // 添加break可以优化，黎源意见
//                                 // break;
//                             }
//                             // 将该路径添加到该层队列中
//                             queue.add(pathList); 
//                             // 将该单词添加到该层已访问的单词集合中
//                             subVisited.add(str);
//                         }
//                     }
//                     chars[j] = temp;
//                 }
//             }
//             // 将该层所有访问的单词添加到总的已访问集合中
//             visited.addAll(subVisited);
//         }
//         return res;
//     }
// }