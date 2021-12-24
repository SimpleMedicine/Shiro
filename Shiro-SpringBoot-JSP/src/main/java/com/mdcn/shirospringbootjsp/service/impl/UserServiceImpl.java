package com.mdcn.shirospringbootjsp.service.impl;

import com.mdcn.shirospringbootjsp.dao.UserDAO;
import com.mdcn.shirospringbootjsp.entity.Perms;
import com.mdcn.shirospringbootjsp.service.UserService;
import com.mdcn.shirospringbootjsp.entity.User;
import com.mdcn.shirospringbootjsp.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 用户接口实现类
 * @author: Medicine
 * @createTime: 2021-12-21 20:49
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    /**
     * 注册用户
     * 对明文密码进行md5+salt+hash散列
     * @param user 用户
     */
    @Override
    public void register(User user) {
        // 1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        // 2.将随机盐保存到数据
        user.setSalt(salt);
        // 3.将明文密码进行md5+salt+hash散列
        Md5Hash password_Md5Hashed = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(password_Md5Hashed.toHex());
        userDAO.save(user);
    }

    @Override
    public User findByUserName(String username) {
        return userDAO.findByUserName(username);
    }

    @Override
    public User findRolesByUserName(String username) {
        return userDAO.findRolesByUserName(username);
    }

    @Override
    public List<Perms> findPermsByRoleId(String roleId) {
        return userDAO.findPermsByRoleId(roleId);
    }
}
