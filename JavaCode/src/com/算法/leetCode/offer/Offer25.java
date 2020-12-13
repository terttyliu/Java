package com.算法.leetCode.offer;

public class Offer25 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode pre = head;
        while (true) {
            if (l1 == null) {
                pre.next = l2;
                break;
            }
            if (l2 == null) {
                pre.next = l1;
                break;
            }
            if (l1.val < l2.val) {
                pre.next = l1;
                pre=pre.next;
                l1=l1.next;
            } else {
                pre.next = l2;
                pre=pre.next;
                l2=l2.next;
            }
        }
        return head.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
