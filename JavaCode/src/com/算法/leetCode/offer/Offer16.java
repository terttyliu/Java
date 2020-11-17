package com.算法.leetCode.offer;

import org.junit.jupiter.api.Test;

public class Offer16 {
    @Test
    public void test(){
        System.out.println(myPow(2.0, 10));
    }
    public double myPow(double x, int n) {
        //快速幂
        if(n<0){
            x=1/x;
            n=-n;
        }
        double res=1;
        while(n!=0){
            res*=((n%2)==1)?x:1;
            n>>>=1;
            x*=x;
        }
        return res;
    }
}
