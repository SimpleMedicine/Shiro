package com.mdcn;

import com.mdcn.realm.CustomerRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @description: 使用自定义realm
 * @author: Medicine
 * @createTime: 2021-12-20 13:05
 */
public class TestCustomerRealmAuthenticator {
    public static void main(String[] args) {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(new CustomerRealm());
        // 使用自定义的realm
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("xiaochen", "123");
        try{
            subject.login(token);
            System.out.println(subject.isAuthenticated());
        }catch (UnknownAccountException e){
            System.out.println("error: 用户名错误");
        }catch (IncorrectCredentialsException e){
            System.out.println("error: 密码错误");
        }
    }
}
