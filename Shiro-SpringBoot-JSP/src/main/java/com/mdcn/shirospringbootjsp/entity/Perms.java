package com.mdcn.shirospringbootjsp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 权限表
 * @author: Medicine
 * @createTime: 2021-12-22 13:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Perms implements Serializable {
    private String id;
    private String name;
    private String url;
}
