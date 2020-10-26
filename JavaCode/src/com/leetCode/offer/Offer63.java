package com.leetCode.offer;

/**
 * @author `pjliu`
 * @category 动态规划
 * @date 2020/10/03
 */
public class Offer63 {
    class Solution {
        public int maxProfit(int[] prices) {
            int res=0;
            int[]dp=new int[prices.length];
            dp[0]=0;
            for (int i = 1; i < prices.length; i++) {
                dp[i]=Math.max(0,dp[i-1]+prices[i]-prices[i-1]);
                res=Math.max(res,dp[i]);
            }
            return res;
        }
    }
    public static void main(String[] args) {
        Solution s =new Offer63().new Solution();
        System.out.println(s.maxProfit(new int[]{7,6,4,3,1}));
    }
}
