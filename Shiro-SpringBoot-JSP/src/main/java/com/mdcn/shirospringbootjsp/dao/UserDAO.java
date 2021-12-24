package com.mdcn.shirospringbootjsp.dao;

import com.mdcn.shirospringbootjsp.entity.Perms;
import com.mdcn.shirospringbootjsp.entity.Role;
import com.mdcn.shirospringbootjsp.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: User操作接口
 * @author: Medicine
 * @createTime: 2021-12-21 20:44
 */
@Mapper
public interface UserDAO {

    void save(User user);

    User findByUserName(String username);

    // 根据用户名查询所有角色
    User findRolesByUserName(String username);

    // 根据角色id查询权限集合
    List<Perms> findPermsByRoleId(String roleId);
}
