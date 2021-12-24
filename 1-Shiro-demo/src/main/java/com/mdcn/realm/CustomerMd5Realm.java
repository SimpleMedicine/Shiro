package com.mdcn.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @description: 自定义realm+md5+salt+hash散列
 * @author: Medicine
 * @createTime: 2021-12-21 11:37
 */
public class CustomerMd5Realm extends AuthorizingRealm {
    /**
     * 授权
     * @param principals 身份集合
     * @return 返回授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // Subject可以有多个身份, 但是只能有一个主身份, 默认是用户名principle
        String principle = (String) principals.getPrimaryPrincipal();
        System.out.println("身份信息:" + principle);
        // 根据用户名获取当前用户的角色信息, 以及权限信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 将数据库中查询到的权限信息赋值给权限对象
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addRole("user");
        // 将数据库中查询到的权限字符串赋值给权限对象
        simpleAuthorizationInfo.addStringPermission("user:*:01");   // 只对01用户具有所有权限
        simpleAuthorizationInfo.addStringPermission("product:delete:*");   // 对所有product资源具有delete权限
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param token 待认证用户信息封装类
     * @return 认证信息
     * @throws AuthenticationException 认证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();

        String principle_from_db = "xiaochen";
        String password_from_db = "fad0db1ad9f6fef7b3665052cd8210f5";   // 123经过加密的密文
        // 认证通过
        if(principle_from_db.equals(principal)){
            /* md5加密验证 */
//            return new SimpleAuthenticationInfo(principle_from_db,password_from_db,this.getName());

            /* md5+salt && md5+salt+hash加密验证 */
            return new SimpleAuthenticationInfo(
                    principle_from_db,
                    password_from_db,   // md5+salt密文
                    ByteSource.Util.bytes("!@#$%"), // 随机盐
                    this.getName());
        }
        return null;
    }
}






