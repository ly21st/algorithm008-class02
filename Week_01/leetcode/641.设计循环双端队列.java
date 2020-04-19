/*
 * @lc app=leetcode.cn id=641 lang=java
 *
 * [641] 设计循环双端队列
 */

// @lc code=start
class MyCircularDeque {
    class QueueNode {
        int val;
        QueueNode pre;
        QueueNode next;
        QueueNode(int k) {
            val = k;
        }
     }

    QueueNode head;
    int count;
    int capacity;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        count = 0;
        capacity = k;
        head = new QueueNode(k);
        head.next = head;
        head.pre = head;
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (count >= capacity) return false;
        QueueNode node = new QueueNode(value);
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        node.pre = head;
        count++;
        return true;
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (count >= capacity) return false;
        QueueNode node = new QueueNode(value);
        head.pre.next = node;
        node.pre = head.pre;
        node.next = head;
        head.pre = node;
        count++;
        return true;
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (count < 1) return false;
        QueueNode newNext = head.next.next;
        head.next = newNext;
        newNext.pre = head;
        count--;
        return true;
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (count < 1) return false;
        QueueNode newPre = head.pre.pre;
        newPre.next = head;
        head.pre = newPre;
        count--;
        return true;
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        if (count < 1) return -1;
        return head.next.val;
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        if (count < 1) return -1;
        return head.pre.val;
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        if (count < 1) return true;
        return false;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        if (count >= capacity) return true;
        return false;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
// @lc code=end

