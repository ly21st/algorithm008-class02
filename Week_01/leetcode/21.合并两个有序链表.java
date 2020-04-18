/*
 * @lc app=leetcode.cn id=21 lang=java
 *
 * [21] 合并两个有序链表
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// class ListNode {
//     int val;
//     ListNode next;

//     ListNode(int x) {
//         val = x;
//     }
// }

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
// @lc code=end




// class Solution {
//     public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//         ListNode head = new ListNode(0);
//         ListNode cur = head;
//         while (null != l1 && null != l2) {
//             if (l1.val <= l2.val) {
//                 cur.next = l1;
//                 cur = l1;
//                 l1 = l1.next;
//             } else {
//                 cur.next = l2;
//                 cur = l2;
//                 l2 = l2.next;
//             }
//         }
//         cur.next = l1 == null? l2 : l1;
//         return head.next;
//     }
// }