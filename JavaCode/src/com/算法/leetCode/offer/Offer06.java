package com.算法.leetCode.offer;

import java.util.Stack;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 *
 * @author pjliu
 */
public class Offer06 {
        public int[] reversePrint(ListNode head) {
            Stack<Integer> stack = new Stack<Integer>();
            while (head != null) {
                stack.push(head.val);
                head = head.next;
            }
            int size = stack.size();
            int[] print = new int[size];
            for (int i = 0; i < size; i++) {
                print[i] = stack.pop();
            }
            return print;
        }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}


