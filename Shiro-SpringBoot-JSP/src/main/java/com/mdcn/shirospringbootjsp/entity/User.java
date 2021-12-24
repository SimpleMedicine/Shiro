package com.mdcn.shirospringbootjsp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 用户类
 * @author: Medicine
 * @createTime: 2021-12-21 20:39
 */
@Data   // getter and setter
@Accessors(chain = true)
@AllArgsConstructor // 有参构造
@NoArgsConstructor  // 无参构造
public class User implements Serializable {
    private String id;
    private String username;
    private String password;
    private String salt;

    // 定义角色集合
    private List<Role> roles;
}
