package com.算法.leetCode.offer;

import org.junit.jupiter.api.Test;

public class Offer17 {
    @Test
    public void test() {
        int[] ints = printNumbers(1);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]);
        }
    }

        public int[] printNumbers(int n) {
            int[] res = new int[(int) Math.pow(10, n)-1];
            for (int i = 0; i < res.length; i++) {
                res[i]=i+1;
            }
            return res;
        }
}
