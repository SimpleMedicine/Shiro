package com.mdcn.shirospringbootjsp.controller;

import com.mdcn.shirospringbootjsp.entity.User;
import com.mdcn.shirospringbootjsp.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: TODO
 * @author: Medicine
 * @createTime: 2021-12-21 16:44
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 用户认证
     * @param user 用户
     */
    @RequestMapping("register")
    public String register(User user){
        try{
            userService.register(user);
            return "redirect:/login.jsp";
        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/register.jsp";
        }
    }

    /**
     * 处理身份认证
     * @param username 用户名
     * @param password 密码
     */
    @RequestMapping("login")
    public String login(String username, String password){
        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(new UsernamePasswordToken(username,password));
            return "redirect:/index.jsp";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("用户名错误!");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误!");
        }
        return "redirect:/login.jsp";
    }

    /**
     * 退出登录
     */
    @RequestMapping("logout")
    public String logOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }
}
