import java.util.HashSet;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=433 lang=java
 *
 * [433] 最小基因变化
 *
 * https://leetcode-cn.com/problems/minimum-genetic-mutation/description/
 *
 * algorithms
 * Medium (49.35%)
 * Likes:    32
 * Dislikes: 0
 * Total Accepted:    4.1K
 * Total Submissions: 8.2K
 * Testcase Example:  '"AACCGGTT"\n"AACCGGTA"\n["AACCGGTA"]'
 *
 * 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
 * 
 * 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
 * 
 * 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
 * 
 * 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
 * 
 * 现在给定3个参数 — start, end,
 * bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回
 * -1。
 * 
 * 注意:
 * 
 * 
 * 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
 * 所有的目标基因序列必须是合法的。
 * 假定起始基因序列与目标基因序列是不一样的。
 * 
 * 
 * 示例 1:
 * 
 * 
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 * 
 * 返回值: 1
 * 
 * 
 * 示例 2:
 * 
 * 
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 * 
 * 返回值: 2
 * 
 * 
 * 示例 3:
 * 
 * 
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 * 
 * 返回值: 3
 * 
 * 
 */


// 根据网友代码思路编写
// @lc code=start
class Solution {
    int min = Integer.MAX_VALUE;
    public int minMutation(String start, String end, String[] bank) {
        
        Set<String> visited =new  HashSet<String>();
        helper(start, end, bank, visited, 0);
        return min == Integer.MAX_VALUE? -1 : min;
    }

    public void helper(String cur, String end, String[] bank, Set<String> visited, int count) {
        if (cur.equals(end)) {
            if (count < min) {
                min = count;
            }
            return;
        }
        if (count >= min) return;
        for (String s : bank) {
            int diff = 0;
            int len = s.length();
            for (int i = 0; i < len; i++) {
                if (s.charAt(i) != cur.charAt(i)) diff++;
                if (diff > 1) break;
            }
            if (diff == 1 && !visited.contains(s)) {
                visited.add(s);
                helper(s, end, bank, visited, count + 1);
                visited.remove(s);
            }
        }
    }
}
// @lc code=end


// 代码收藏，深度优先遍历方法
// class Solution {
//     int minStepCount = Integer.MAX_VALUE;
//     public int minMutation(String start, String end, String[] bank) {
//         dfs(new HashSet<String>(), 0, start, end, bank);
//         return (minStepCount == Integer.MAX_VALUE) ? -1 : minStepCount;
//     }
//     private void dfs (HashSet<String> step, int stepCount, 
//         String current, String end, String[] bank) {
//         if (current.equals(end)) 
//             minStepCount = Math.min(stepCount, minStepCount);
//         for (String str: bank) {
//             int diff = 0;
//             for (int i = 0; i < str.length(); i++) 
//                 if (current.charAt(i) != str.charAt(i))
//                     if (++diff > 1) break;
//             if (diff == 1 && !step.contains(str)) {
//                 step.add(str);
//                 dfs(step, stepCount + 1, str, end, bank);
//                 step.remove(str);
//             }
//         }
//     }
// }




// 广度优先队列，代码收藏
// class Solution:
//     def minMutation(self, start: str, end: str, bank: List[str]) -> int:
//         bankset = set(bank)
//         n_s = len(start)
//         ls = list(start)
//         visited = set()
//         queue = collections.deque()
//         queue.append(ls)
//         visited.add(start)
//         cand = ['A','C', 'G', 'T']
//         level = 0
//         while queue:
//             for _ in range(len(queue)):
//                 ls = queue.popleft()
//                 if ''.join(ls) == end:
//                     return level
//                 for i, v in enumerate(ls):
//                     for c in cand:
//                         ls[i] = c
//                         cur = ''.join(ls)
//                         if cur not in visited and cur in bankset:
//                             queue.append(ls[:])
//                             visited.add(cur)
//                     ls[i] = v
//             level += 1
//         return -1

