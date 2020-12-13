package com.算法.leetCode.offer;

import org.junit.jupiter.api.Test;

/**
 * 调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 */
public class Offer21 {
    @Test
    public void test() {
        int[] ints = exchange(new int[]{1, 2, 3, 4});
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
    }

    //思路一 双端指针
    public int[] exchange(int[] nums) {
        int pre = 0;
        int last = nums.length - 1;
        boolean preSwap = false;
        boolean lastSwap = false;
        while (pre < last) {
            while (nums[pre] % 2 == 1) {
                pre++;
            }
            while (nums[last] % 2 == 0) {
                last--;
            }
            if (pre < last) {
                int temp = nums[pre];
                nums[pre] = nums[last];
                nums[last] = temp;
            }
        }
        return nums;
    }
}
