package com.mdcn.shirospringbootjsp.service;

import com.mdcn.shirospringbootjsp.entity.Perms;
import com.mdcn.shirospringbootjsp.entity.User;

import java.util.List;

/**
 * @description: 用户接口
 * @author: Medicine
 * @createTime: 2021-12-21 20:48
 */
public interface UserService {
    // 注册用户
    void register(User user);

    // 根据用户名查询用户
    User findByUserName(String username);

    // 根据用户名查询用户所有角色
    User findRolesByUserName(String username);

    // 根据角色id查询权限集合
    List<Perms> findPermsByRoleId(String roleId);
}
