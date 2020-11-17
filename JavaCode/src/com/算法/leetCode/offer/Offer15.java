package com.算法.leetCode.offer;

import org.junit.jupiter.api.Test;

public class Offer15 {
    @Test
    public void test(){
        System.out.println(hammingWeight(31));
    }
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        if (n < 0) {
            n = -n;
        }
        while (n != 0) {
            res += n & 1;
            n=n>>>1;
        }
        return res;
    }
}
