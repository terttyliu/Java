package com.算法.leetCode.offer;

public class Offer22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode low = head;
        for (int i = 0; i < 6; i++) {
            if (fast.next == null) return head;
        }
        while (fast.next != null) {
            low = low.next;
            fast = fast.next;
        }
        return low;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
