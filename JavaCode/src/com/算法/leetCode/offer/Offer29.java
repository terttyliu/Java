package com.算法.leetCode.offer;

import org.junit.jupiter.api.Test;

/**
 * 剑指 Offer 29. 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 */
public class Offer29 {
    @Test
    public void test() {
        int[] ints = spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }

    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return new int[0];

        int left = 0;
        int right = matrix[0].length - 1;
        int up = 0;
        int down = matrix.length - 1;

        int[] rs = new int[(right + 1) * (down + 1)];
        int num = 0;
        while (left <= right && up <= down) {
            for (int i = left; i <= right; i++) {
                rs[num++] = matrix[up][i];
            }
            for (int i = up + 1; i <= down; i++) {
                rs[num++] = matrix[i][right];
            }
            if (left < right && up < down) {
                for (int i = right - 1; i > left; i--) {
                    rs[num++] = matrix[down][i];
                }
                for (int i = down; i > up; i--) {
                    rs[num++] = matrix[i][left];
                }
            }
            left++;
            right--;
            up++;
            down--;
        }
        return rs;
    }
}

class Solution {
    @Test
    public void test(){
        int[] ints = spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[] rs = new int[rows * columns];
        int num = 0;
        int left = 0, right = columns - 1, up = 0, down = rows - 1;
        while (left <= right && up <= down) {
            for (int i = left; i <= right; i++) {
                rs[num++] = matrix[up][i];
            }
            for (int row = up + 1; row <= down; row++) {
                rs[num++] = matrix[row][right];
            }
            if (left < right && up < down) {
                for (int column = right - 1; column > left; column--) {
                    rs[num++] = matrix[down][column];
                }
                for (int row = down; row > up; row--) {
                    rs[num++] = matrix[row][left];
                }
            }
            left++;
            right--;
            up++;
            down--;
        }
        return rs;
    }
}
