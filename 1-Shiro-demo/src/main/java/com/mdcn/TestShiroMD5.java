package com.mdcn;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @description: Shiro中的MD5认证
 * @author: Medicine
 * @createTime: 2021-12-21 11:29
 */
public class TestShiroMD5 {
    public static void main(String[] args) {
        // 使用md5
        String source = "123";
        Md5Hash md5Hash = new Md5Hash(source);
        System.out.println(md5Hash.toHex());    // 16进制输出

        // 使用md5+salt
        String salt = "!@#$%"; // 随机数, 可以通过随机数软件生成
        Md5Hash md5Hash1 = new Md5Hash(source, salt);
        System.out.println(md5Hash1.toHex());

        // 使用md5+salt+hash散列次数
        Md5Hash md5Hash2 = new Md5Hash(source, salt, 1024);
        System.out.println(md5Hash2.toHex());
    }
}

/**
 * 输出结果:
 * 202cb962ac59075b964b07152d234b70 md5
 * 8d8b8a69420e5f74b5cf50e599822e5a md5+salt
 * fad0db1ad9f6fef7b3665052cd8210f5 md5+salt+hash
 */
