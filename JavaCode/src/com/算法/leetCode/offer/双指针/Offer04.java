package com.算法.leetCode.offer.双指针;

/**
 *  二维数组中的查找
 *  在一个 n * m 的二维数组中，
 *  每一行都按照从左到右递增的顺序排序，
 *  每一列都按照从上到下递增的顺序排序。
 *  请完成一个高效的函数，输入这样的一个二维数组和一个整数，
 *  判断数组中是否含有该整数。
 */
public class Offer04 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        boolean rs=false;
        int n=matrix.length;
        int m=matrix[0].length;
        int lie=-1;
        int hang=-1;
        for (int i = 0; i < m; i++) {
            if (matrix[0][i]==target)return true;
            if (matrix[0][i]>target){
                lie=i;
                break;
            }
        }
        return rs;
    }
}
