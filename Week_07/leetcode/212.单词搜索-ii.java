import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=212 lang=java
 *
 * [212] 单词搜索 II
 *
 * https://leetcode-cn.com/problems/word-search-ii/description/
 *
 * algorithms
 * Hard (40.77%)
 * Likes:    171
 * Dislikes: 0
 * Total Accepted:    14.8K
 * Total Submissions: 36.3K
 * Testcase Example:  '[["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]\n' +
  '["oath","pea","eat","rain"]'
 *
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 * 
 * 
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * 
 * 示例:
 * 
 * 输入: 
 * words = ["oath","pea","eat","rain"] and board =
 * [
 * ⁠ ['o','a','a','n'],
 * ⁠ ['e','t','a','e'],
 * ⁠ ['i','h','k','r'],
 * ⁠ ['i','f','l','v']
 * ]
 * 
 * 输出: ["eat","oath"]
 * 
 * 说明:
 * 你可以假设所有输入都由小写字母 a-z 组成。
 * 
 * 提示:
 * 
 * 
 * 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？
 * 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
 * 
 * 
 */

// @lc code=start
class Solution {
    int [][]offset = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };
    int L = 0;
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> output = new HashSet<String>();
        List<String> res = null;
        Trie trie = new Trie();
        for (String word : words) {
            int len = word.length();
            if (len > L) {
                L = len;
            }
            trie.insert(word);
        }
        int m = board.length;
        if (m < 1) return res;
        int n = board[0].length;
        if (n < 1) return res;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String s = "" + board[i][j];
                if (trie.startsWith(s)) {
                    dfs(board, m, n, i, j, output, trie, "");
                }
            }
        }
        res = new ArrayList<String>(output);
        return res;
    }

    public void dfs(char[][]board, int m, int n, int i, int j, Set<String> res, Trie trie, String s) {
        if (board[i][j] != '#') {
            s += board[i][j];
            char tmp = board[i][j];
            board[i][j] = '#';
            if (trie.search(s)) {
                res.add(s);
                if (s.length() >= L) {
                    board[i][j] = tmp;
                    return;
                }
            } 
            if (trie.startsWith(s)) {
                for (int ii = 0; ii < 4; ii++) {
                    if (i + offset[ii][0] >= 0 && i + offset[ii][0] < m && 
                        j + offset[ii][1] >= 0 && j + offset[ii][1] < n) {
                        dfs(board, m, n, i + offset[ii][0], j + offset[ii][1], res, trie, s);
                    }
                }
            }
            board[i][j] = tmp;
        }
    }
}

class Trie {
    private boolean isEnd;
    private Trie[] next;
    /** Initialize your data structure here. */
    public Trie() {
        isEnd = false;
        next = new Trie[26];
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            int n = words[i] - 'a';
            if (curr.next[n] == null) curr.next[n] = new Trie();
            curr = curr.next[n];
        }
        curr.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    private Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            node = node.next[words[i] - 'a'];
            if (node == null) return null;
        }
        return node;
    }
}

// @lc code=end

