package com.算法.leetCode.offer;

import com.mysql.jdbc.BlobFromLocator;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.metal.MetalIconFactory;
import java.util.Stack;
import java.util.concurrent.RecursiveTask;

public class Offer17 {
    @Test
    public void test() {
        Print1ToMaxOFN(0);
    }

    public int[] printNumbers(int n) {
        int[] res = new int[(int) Math.pow(10, n) - 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = i + 1;
        }
        return res;
    }

    public void Print1ToMaxOFN(int n) {
        if (n <= 0) return;
        char[] number = new char[n];
        for (int i = 0; i < 10; i++) {
            number[0] = (char) ('0' + i);
            printRecursively(number, 0);
        }
    }

    void printRecursively(char[] number, int index) {
        if (index == number.length - 1) {
            myPrint(number);
            return;
        }
        for (int i = 0; i < 10; i++) {
            number[index + 1] = (char) ('0' + i);
            printRecursively(number, index + 1);
        }
        System.out.println();
    }

    public void myPrint(char[] number) {
        System.out.print(String.valueOf(number).replaceAll("^0+", "") + "\t");
    }

    @Test
    public void testBigNumPlus(){
        System.out.println(bigNumPlus("12346789", "1111"));
    }
    public String bigNumPlus(String strA, String strB) {
        if (strA.startsWith("-") || strB.startsWith("-")) {
            return bigNumSub(strA, strB);
        }
        StringBuffer bA = new StringBuffer(strA).reverse();
        StringBuffer bB = new StringBuffer(strB).reverse();
        if (bA.length() > bB.length()) {
            for (int i = 0; i < bA.length() - bB.length(); i++) {
                bB.append("0");
            }
        } else {
            for (int i = 0; i < bB.length() - bA.length(); i++) {
                bA.append("0");
            }
        }
        StringBuffer buffer = new StringBuffer();
        int flag = 0;
        for (int i = 0; i < bA.length(); i++) {
            int numA = bA.charAt(i) - '0';
            int numB = bB.charAt(i) - '0';
            int sum = numA + numA + flag;
            if (sum >= 10) {
                flag = 1;
                sum -= 10;
            }
            buffer.append(sum);
        }
        if (flag==1){
            buffer.append(flag);
        }
        return buffer.reverse().toString();
    }

    public String bigNumSub(String strA, String strB) {
        return "";
    }
}
