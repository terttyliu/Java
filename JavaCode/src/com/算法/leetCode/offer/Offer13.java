package com.算法.leetCode.offer;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class Offer13 {
    @Test
    public void test() {
        System.out.println(movingCount(1,2,1));
    }

    public int movingCount(int m, int n, int k) {
        int res = 0;
        LinkedList<int[]> queue = new LinkedList<>();
        boolean[][] flags = new boolean[m][n];
        queue.offer(new int[]{0, 0});
        flags[0][0] = true;
        int[] dx = {0, 1};
        int[] dy = {1, 0};
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            for (int i = 0; i < 2; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                if (tx >= m || ty >= n || get(tx) + get(ty) > k || flags[tx][ty]) continue;
                queue.add(new int[]{tx, ty});
                res++;
                flags[tx][ty] = true;
            }
        }
        return res;
    }

    private int get(int i) {
        int res = 0;
        while (i != 0) {
            res += i % 10;
            i = i / 10;
        }
        return res;
    }
}
