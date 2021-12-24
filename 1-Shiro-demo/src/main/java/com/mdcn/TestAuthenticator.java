package com.mdcn;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class TestAuthenticator {
    public static void main(String[] args) {
        // 1.创建安全管理器对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        // 2.给安全管理器设置realm
        // Realms act as the ‘bridge’ or ‘connector’ between Shiro and your application’s security data
        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));

        // 3.SecurityUtils 全局安全工具类, 提供了认证和退出等方法
        // 给全局安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        // 4.关键对象Subject主题
        Subject subject = SecurityUtils.getSubject();

        // 5.创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("xiaochen", "123");

        try{
            System.out.println("认证信息:" + subject.isAuthenticated());
            subject.login(token);
            System.out.println("认证信息:" + subject.isAuthenticated());
        }catch(UnknownAccountException e){
            System.out.println("认证失败: 用户名不存在");
        }catch(IncorrectCredentialsException e){
            System.out.println("认证失败: 密码错误");
        }
    }
}
