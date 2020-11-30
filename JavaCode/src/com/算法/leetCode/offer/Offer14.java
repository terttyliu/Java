package com.算法.leetCode.offer;

import org.junit.jupiter.api.Test;

/**
 * 剪绳子 1
 * <p>
 * <p>
 * dp[i]=max_{0<j<i}{j*(i-j),j*dp[i-j]}
 *
 * @author `pjliu`
 * @category 动态规划
 * @date 2020/10/03
 */
public class Offer14 {
    @Test
    public void test() {
        System.out.println(cuttingRope(120));
        System.out.println(cuttingRope3(120));

    }

    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    public int cuttingRope2(int n) {
        if (n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if (b == 0) return (int) Math.pow(3, a);
        if (b == 1) return (int) Math.pow(3, a - 1) * 4;
        return (int) Math.pow(3, a) * 2;
    }

    public int cuttingRope3(int n) {
        if (n <= 3) return n - 1;
        int mod = 1000000007;
        long res = 1;
        while (n > 4) {
            //细节 此处不能使用res*=... 不然会 mod后才*res
            res = res * 3 % mod;
            n -= 3;
        }
        return (int) (res * n % mod);

    }
}
