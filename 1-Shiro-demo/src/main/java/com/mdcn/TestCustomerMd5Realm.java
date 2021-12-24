package com.mdcn;

import com.mdcn.realm.CustomerMd5Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

/**
 * @description: 测试自定义realm
 * @author: Medicine
 * @createTime: 2021-12-21 11:38
 */
public class TestCustomerMd5Realm {
    public static void main(String[] args) {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        // 设置hash凭证匹配器
        // hash凭证匹配器会对用户的输入先进行md5加密,
        // 数据库中储存的也是加密后的数据, 所以可以匹配成功
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");   // 设置加密算法
        hashedCredentialsMatcher.setHashIterations(1024);   // 设置散列次数
        // 注入realm
        CustomerMd5Realm realm = new CustomerMd5Realm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        // 注入安全管理器
        defaultSecurityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        // 认证Subject
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("xiaochen", "123");
        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (UnknownAccountException e) {
            System.out.println("用户名错误");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        }

        // authenticate -> authorize -> access
        // 授权已认证用户
        if(subject.isAuthenticated()){
            // 基于角色权限控制
            System.out.println(subject.hasRole("user"));

            // 基于多角色权限控制
            System.out.println(subject.hasAllRoles(Arrays.asList("user", "admin")));

            // 是否具有其中一个角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("user", "admin", "unknownRole"));
            for (boolean bool : booleans){
                System.out.println(bool);
            }

            // 基于权限字符串(资源标识符:操作:资源类型实例)的访问控制
            System.out.println("权限:" + subject.isPermitted("user:*:*"));
            System.out.println("权限:" + subject.isPermitted("user:*:01"));

            // 同时具有哪些权限
            System.out.println(subject.isPermittedAll("user:*:01", "product:delete:*"));
        }
    }
}
