package com.mdcn.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @description: 自定义realm类
 * @author: Medicine
 * @createTime: 2021-12-20 13:05
 */

/**
 * 在源框架中:
 * SimpleAccountRealm ----> AuthorizingRealm ----> AuthenticatingRealm
 * 实际上的认证工作是在SimpleAccountRealm类中完成的, 这里自定义realm类目的就是
 * 编写一个自定义的类来代替SimpleAccountRealm类完成认证工作
 */
public class CustomerRealm extends AuthorizingRealm {
    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 在token中获取用户名
        String principal = (String) authenticationToken.getPrincipal();
//        System.out.println("principal = " + principal); // 输出用户名
        // 根据身份信息使用mybatis查询数据库中正确的信息
        String principalFromDb = "xiaochen";
        String passwordFromDb = "123";
        if(principalFromDb.equals(principal)){
            // 创建验证对象, 代表通过验证
            // 参数1: 数据库中正确的用户名
            // 参数2: 数据库中正确的密码
            // 参数3: 提供当前realm的名字
            return new SimpleAuthenticationInfo(principalFromDb, passwordFromDb, this.getName());
        }
        return null;
    }
}
