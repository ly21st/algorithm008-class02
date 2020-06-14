import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=146 lang=java
 *
 * [146] LRU缓存机制
 *
 * https://leetcode-cn.com/problems/lru-cache/description/
 *
 * algorithms
 * Medium (49.33%)
 * Likes:    673
 * Dislikes: 0
 * Total Accepted:    71.8K
 * Total Submissions: 145.1K
 * Testcase Example:  '["LRUCache","put","put","get","put","get","put","get","get","get"]\n' +
  '[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]'
 *
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * 
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) -
 * 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * 
 * 
 * 
 * 进阶:
 * 
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * 
 * 
 * 
 * 示例:
 * 
 * LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
 * 
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回  1
 * cache.put(3, 3);    // 该操作会使得关键字 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4, 4);    // 该操作会使得关键字 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 * 
 * 
 */

// @lc code=start
class LRUCache {
    class CacheNode {
        int key;
        int val;
        CacheNode pre = null;
        CacheNode next = null;
        CacheNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    class DoubleLinked {
        CacheNode head = null;
        CacheNode tail = null;
        public DoubleLinked() {
            head = new CacheNode(-1, -1);
            tail = new CacheNode(-1, -1);
            head.next = tail;
            head.pre = tail;
            tail.next = head;
            tail.pre = head;
        }
    }
    int size = 0; 
    int capacity = 0;
    DoubleLinked list = new DoubleLinked();
    Map<Integer, CacheNode> m = new HashMap<>();
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (!m.containsKey(key)) {
            return -1;
        }
        CacheNode node = m.get(key);
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.next = list.head.next;
        list.head.next.pre = node;
        list.head.next = node;
        node.pre = list.head;
        return node.val;
    }
    
    public void put(int key, int value) {
        CacheNode node = m.getOrDefault(key, null);
        if (node == null) {
            node = new CacheNode(key, value);
            node.next = list.head.next;
            list.head.next.pre = node;
            list.head.next = node;
            node.pre = list.head;
            m.put(key, node);
            size++;
            if (size > capacity) {
                node = list.tail.pre;
                node.pre.next = list.tail;
                list.tail.pre = node.pre;
                m.remove(node.key);
            }
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.val = value;
            node.next = list.head.next;
            list.head.next.pre = node;
            list.head.next = node;
            node.pre = list.head;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end


// 解法1，使用已有数据结构LinkedList与HashMap
// class LRUCache {
//     class CacheNode {
//         int key;
//         int val;
//         CacheNode(int key, int val) {
//             this.key = key;
//             this.val = val;
//         }
//     }
//     int size = 0; 
//     int capacity = 0;
//     LinkedList<CacheNode> list = new LinkedList<>();
//     Map<Integer, CacheNode> m = new HashMap<>();
    
//     public LRUCache(int capacity) {
//         this.capacity = capacity;
//     }
    
//     public int get(int key) {
//         if (!m.containsKey(key)) {
//             return -1;
//         }
//         CacheNode node = m.get(key);
//         list.remove(node);
//         list.addFirst(node);
//         return node.val;
//     }
    
//     public void put(int key, int value) {
//         CacheNode node = m.getOrDefault(key, null);
//         if (node != null) {
//             node.val = value;
//             list.remove(node);
//             list.addFirst(node);
//         } else {
//             node = new CacheNode(key, value);
//             list.addFirst(node);
//             m.put(key, node);
//             size++;
//             if (size > capacity) {
//                 node = list.removeLast();
//                 m.remove(node.key);
//             }
//         }
//     }
// }
