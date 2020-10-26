package com.常用;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author `pjliu`
 * @date 2020/10/12
 */
public class 常用类SystemMathBig {
    public static void main(String[] args) throws IOException {
        //SystemTest
//        Properties properties = System.getProperties();
//        properties.list(System.out);
//        properties.load(System.in);
        //Math
        System.out.println(Math.max(10, 17));
        //Big**
        BigInteger integer = new BigInteger("9999999999999999999999999999999999");
        System.out.println(integer);
        BigDecimal bigDecimal = new BigDecimal(1.0 / 2);
        System.out.println(bigDecimal.setScale(2));
        bigDecimal = new BigDecimal(1.0 / 3);
        System.out.println(bigDecimal.setScale(2));
    }
}
