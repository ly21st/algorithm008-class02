

# 学习笔记

## 70爬楼梯问题

这个问题已经出现过很多次，有很多方法解答。

* 斐波那契数列法
* 傻递归法
* 带有记忆数组的递归
* 使用数组循环递推法
* 动态规划法

```

    class Solution {
        public int climbStairs(int n) {
            if (n < 3) return n;
            int a;
            int pre = 1;
            int cur = 2;
            int i = 3; 
            while (i <= n) {
                a = pre + cur;
                pre = cur;
                cur = a;
                i++;
            }
            return cur;
        }
    }

```

## 区域包围问题

这个问题跟孤岛问题类似，可以用深度优先算法，广度优先算法，或并查集算法。
深度优先也就是回溯方法，关键是定义剪枝条件。

## Trie树
Trie树高效完成以下操作：

* 找到具有同一前缀的全部键值
* 按词典序枚举字符串的数据集

### Trie树的结点结构
Trie树是一个有根的树，其结点具有以下字段：
* 最多 RRR 个指向子结点的链接，其中每个链接对应字母表数据集中的一个字母。
  本文中假定 RRR 为 26，小写拉丁字母的数量。
* 布尔字段，以指定节点是对应键的结尾还是只是键前缀。

### Trie树的一个实现
```

    class TrieNode {
        boolean end;
        final int R = 26;
        TrieNode[] links;

        public TrieNode() {
            links = new TrieNode[R];
        }

        public boolean getEnd() {
            return end;
        }

        public void setEnd(boolean end) {
            this.end = end;
        }

        public TrieNode get(char c) {
            return links[c - 'a'];
        }

        public void set(char c) {
            links[c - 'a'] = new TrieNode();
        }
    }


    class Trie {
        TrieNode root = new TrieNode();

        /** Initialize your data structure here. */
        public Trie() {
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            int len = word.length();
            TrieNode node = root;
            for (int i = 0; i < len; i++) {
                TrieNode next = node.get(word.charAt(i));
                if (next == null) {
                    node.set(word.charAt(i));
                }
                node = node.get(word.charAt(i));
            }
            node.setEnd(true);
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            int len = word.length();
            TrieNode node = root;
            for (int i = 0; i < len; i++) {
                node = node.get(word.charAt(i));
                if (node == null) {
                    return false;
                }
            }
            return node.getEnd() == true;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            int len = prefix.length();
            TrieNode node = root;
            for (int i = 0; i < len; i++) {
                node = node.get(prefix.charAt(i));
                if (node == null) {
                    return false;
                }
            }
            return true;
        }
    }
```

### Trie树模板

```
    
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

```



## 数独问题
判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。


上图是一个部分填充的有效的数独。

数独部分空格内已填入了数字，空白格用 '.' 表示。

![f56910644ac6fb54bde203da34acdd5a.png](en-resource://database/5441:1)

### 问题分析
这道题属于数组问题，遍历数组中每一个元素，判断每个元素在每一行，每一列，每一个3 * 3 小宫格内是否符合要求即可。由于整个矩阵的大小是固定的，而且里面的元素大小固定，因此用几个数组即可解决。

```
    
    class Solution {
        public boolean isValidSudoku(char[][] board) {
            int [][]rows = new int[9][10];
            int [][]cols = new int[9][10];
            int [][][]m = new int[3][3][10];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] >= '0' && board[i][j] <= '9') {
                        int t = board[i][j] - '0';
                        if (rows[i][t] != 0) return false;
                        if (cols[j][t] != 0) return false;
                        if (m[i / 3][j / 3][t] != 0) return false;
                        rows[i][t] = 1;
                        cols[j][t] = 1;
                        m[i / 3][j / 3][t] = 1;
                    }
                }
            }
            return true;
        }
    }


```


##  [350] 两个数组的交集 II
 
 
 ```
 
     *
     * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/description/
     *
     * algorithms
     * Easy (47.68%)
     * Likes:    265
     * Dislikes: 0
     * Total Accepted:    79.7K
     * Total Submissions: 166.5K
     * Testcase Example:  '[1,2,2,1]\n[2,2]'
     *
     * 给定两个数组，编写一个函数来计算它们的交集。
     * 
     * 示例 1:
     * 
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2,2]
     * 
     * 
     * 示例 2:
     * 
     * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出: [4,9]
     * 
     * 说明：
     * 
     * 
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
     * 我们可以不考虑输出结果的顺序。
     
```


###  解法
可以使用计数法；双指针法。


## [212] 单词搜索 II

 * https://leetcode-cn.com/problems/word-search-ii/description/

我的做法是先实现一棵Trie树，然后回溯中调用这棵Trie树实例的方法。

官方解法是Trie树与回溯结合实现，代码更为简洁高效。


### 时间复杂度

以官方实现进行说明。
假设单词个数为M，单词长度为N，则构造Trie树时间为M * N

面板的大小为m * n, 对每一个元素进行开始回溯，回溯的深度是查找Trie树的查找深度k，可以向4个方向查找，
因此时间是m * n * k * 4. 总的时间复杂度是 O(M * N + m * n * k * 4)


## 并查集

代码模板
```
    
    
    class UnionFind { 
            private int count = 0; 
            private int[] parent; 
            public UnionFind(int n) { 
                    count = n; 
                    parent = new int[n]; 
                    for (int i = 0; i < n; i++) { 
                            parent[i] = i;
                    }
            } 
            public int find(int p) { 
                    while (p != parent[p]) { 
                            parent[p] = parent[parent[p]]; 
                            p = parent[p]; 
                    }
                    return p; 
            }
            public void union(int p, int q) { 
                    int rootP = find(p); 
                    int rootQ = find(q); 
                    if (rootP == rootQ) return; 
                    parent[rootP] = rootQ; 
                    count--;
            }
    }
```

## [547]朋友圈问题
有几种解法：

* 深度优先算法
* 广度优先算法
* 并查集方法

这里用你并查集进行求解。
```
    
    class Solution {
        public int findCircleNum(int[][] M) {
            int n = M.length; 
            int []parent = new int[n];
            Arrays.fill(parent, -1);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < M[i].length; j++) {
                    if (M[i][j] == 1 && i != j) {
                        union(parent, i, j);
                    }
                }
            }
            int count = 0; 
            for (int i = 0; i < n; i++) {
                if (parent[i] == -1) {
                    count++;
                }
            }
            return count;
        }
        public void union(int[]parent, int i, int j) {
            int setX = find(parent, i);
            int setY = find(parent, j);
            if (setX != setY) {
                parent[setX] = setY;
            }
        }
        public int find(int []parent, int i) {
            while (parent[i] != -1) {
                i = parent[i];
            }
            return i;
        }
    }

```

## 双向BFS模板

```
    
    
    void TBFS(){
           if(s1.state==s2.state)//起点终点相同时要特判
           {
                  //do something
                  found=true;
                  return;
           }
           bool found=false;
           memset(visited,0,sizeof(visited));  // 判重数组
           while(!Q1.empty())  Q1.pop();   // 正向队列
           while(!Q2.empty())  Q2.pop();  // 反向队列
           //======正向扩展的状态标记为1，反向扩展标记为2
           visited[s1.state]=1;   // 初始状态标记为1
           visited[s2.state]=2;   // 结束状态标记为2
           Q1.push(s1);  // 初始状态入正向队列
           Q2.push(s2);  // 结束状态入反向队列
           while(!Q1.empty() || !Q2.empty())
           {
                  if(!Q1.empty())
                         BFS_expand(Q1,true);  // 在正向队列中搜索
                  if(found)  // 搜索结束 
                         return ;
                  if(!Q2.empty())
                         BFS_expand(Q2,false);  // 在反向队列中搜索
                  if(found) // 搜索结束
                         return ;
           }}void BFS_expand(queue<Status> &Q,bool flag){  
           s=Q.front();  // 从队列中得到头结点s
          Q.pop()
          for( 每个s 的子节点 t )
         {
                 t.state=Gethash(t.temp)  // 获取子节点的状态
                 if(flag)   // 在正向队列中判断
                 {
                          if (visited[t.state]!=1）// 没在正向队列出现过
                        ｛
                               if(visited[t.state]==2)  // 该状态在反向队列中出现过
                              {
                                     各种操作；
                                     found=true；
                                     return;
                               }
                                visited[t.state]=1;   // 标记为在在正向队列中
                                Q.push(t);  // 入队
                           ｝
                 ｝
                 else    // 在正向队列中判断
                 {
                          if (visited[t.state]!=2） // 没在反向队列出现过
                        ｛
                               if(visited[t.state]==1)  // 该状态在正向向队列中出现过
                               {
                                      各种操作；
                                      found=true；
                                      return;
                                }
                                 visited[t.state]=2;  // 标记为在反向队列中
                                 Q.push(t);  // 入队
                           ｝
                 ｝             
    }  
    
```