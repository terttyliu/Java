package com.算法.leetCode.offer.动态规划;

import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * 正则表达式匹配
 * 动态规划、状态机
 *
 * @author pjliu
 */
public class Offer19 {
    @Test
    public void test(){
        System.out.println(isMatch("aa", "a*"));
    }
    public boolean isMatch(String s, String p) {
        boolean rs = false;
        char[] cChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        if (s.equals(p)) return true;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (pChars[j - 1] == '*') {
                    dp[i][j] = matchs(cChars, pChars, i, j - 1) ? dp[i - 1][j] || dp[i][j - 2] : dp[i][j - 2];
                } else {
                    dp[i][j] = matchs(cChars, pChars, i, j) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public boolean matchs(char[] s, char[] p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p[j - 1] == '.') {
            return true;
        } else {
            return Objects.equals(s[i - 1], p[j - 1]);
        }
    }


}
