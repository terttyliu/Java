package com.算法.leetCode.offer;

/**
 * 字符串替换
 *
 * @author pjliu
 */
public class Offer05 {
    public String replaceSpace(String s) {
        StringBuffer sb = new StringBuffer();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append("%20");
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
