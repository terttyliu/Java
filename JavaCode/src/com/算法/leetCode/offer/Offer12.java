package com.算法.leetCode.offer;

import org.junit.jupiter.api.Test;

/*
剑指 Offer 12. 矩阵中的路径
第一思路：类似迷宫==>DFS
 */
public class Offer12 {
    @Test
    public void testExit() {
        char[][] board = {{'A', 'B', 'C', 'E' }, {'S', 'F', 'C', 'S' }, {'A', 'D', 'E', 'E' }};
        String word = "ABCCED";
        boolean f = exist(board, word);
        System.out.println(f);
    }

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean flag = check(i, j, board, visited, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(int i, int j, char[][] board, boolean[][] visited, String s, int k) {
        if (board[i][j] != s.charAt(k)) {
            return false;
        } else if (k == s.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        boolean res = false;
        for (int l = 0; l < 4; l++) {
            int newi = i + directions[l][0];
            int newj = j + directions[l][1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    res = check(newi, newj, board, visited, s, k + 1);
                    if (res == true) break;
                }
            }
        }
        visited[i][j] = false;
        return res;
    }
}
