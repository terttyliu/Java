package com.算法.leetCode.offer;

/**
 * @category 动态规划
 * @author `pjliu`
 * @date 2020/10/01
 */
public class Offer42 {

    public int maxSubArray(int[] nums) {
        //dp[i]表示以第i个元素为结尾的连续数组的最大值。
        //dp[i]=max{dp[i-1],0}+nums[i]
        int res=nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i]+=Math.max(nums[i-1],0);
            res=Math.max(res,nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        Offer42 myClass = new Offer42();
        System.out.println(myClass.maxSubArray(
                new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
