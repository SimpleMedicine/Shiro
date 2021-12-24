package com.mdcn.shirospringbootjsp.utils;

import java.util.Random;

/**
 * @description: 随机盐工具类
 * @author: Medicine
 * @createTime: 2021-12-21 20:52
 */
public class SaltUtils {
    /**
     * 生成salt的静态方法
     * @param n 随机盐位数
     * @return 随机盐
     */
    public static String getSalt(int n){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<n; i++){
            char aChar = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }
}
