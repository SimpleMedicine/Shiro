package com.mdcn.shirospringbootjsp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 角色表
 * @author: Medicine
 * @createTime: 2021-12-22 13:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Role implements Serializable {
    private String id;
    private String name;

    // 权限的集合
    private List<Perms> permsList;
}
