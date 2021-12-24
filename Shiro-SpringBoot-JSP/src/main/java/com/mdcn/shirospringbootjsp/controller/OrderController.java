package com.mdcn.shirospringbootjsp.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 订单控制类
 * @author: Medicine
 * @createTime: 2021-12-22 10:40
 */
@Controller
@RequestMapping("order")
public class OrderController {

    /**
     * 订单保存操作
     */
    @RequestMapping("save")
//    @RequiresRoles({"admin", "user"}) // 同时具有admin, user角色才能进入当前方法
    @RequiresPermissions("user:update:01")  // 用权限字符串进行判断
    public String save(){
        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            System.out.println("保存订单执行");
        }else{
            System.out.println("无权限访问保存订单");
        }
        // 基于权限字符串(省略)
        return "redirect:/index.jsp";
    }
}
