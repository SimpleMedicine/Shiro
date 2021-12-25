package com.mdcn.shirospringbootjsp.shiro.realms;

import com.mdcn.shirospringbootjsp.entity.Perms;
import com.mdcn.shirospringbootjsp.entity.User;
import com.mdcn.shirospringbootjsp.service.UserService;
import com.mdcn.shirospringbootjsp.shiro.util.MyByteSource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @description: 自定义Realm
 * @author: Medicine
 * @createTime: 2021-12-21 16:15
 */
public class CustomerRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取用户名
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        // 查询用户角色
        User user = userService.findRolesByUserName(primaryPrincipal);
        if(!CollectionUtils.isEmpty(user.getRoles())){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            // 遍历添加角色信息
            user.getRoles().forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());
                // 查询用户权限
                List<Perms> permsList = userService.findPermsByRoleId(role.getId());
                if(!CollectionUtils.isEmpty(permsList)){
                    // 遍历添加权限信息
                    permsList.forEach(perm -> {
                        simpleAuthorizationInfo.addStringPermission(perm.getName());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取身份信息
        String principal = (String) token.getPrincipal();
        // 在数据库中查询用户验证信息是否正确
        User user = userService.findByUserName(principal);
        // user != null
        if(!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), new MyByteSource(user.getSalt()),this.getName());
        }
        return null;
    }
}

