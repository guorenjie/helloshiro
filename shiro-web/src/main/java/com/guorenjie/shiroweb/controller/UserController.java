package com.guorenjie.shiroweb.controller;

import com.guorenjie.shiroweb.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author guorenjie
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {


    /**
     * 登录
     * @return
     */
    @GetMapping("/login")
    public String login(){
        log.info("goto login page!");
        return "/login";
    }

    /**
     * 登录逻辑
     * @return
     */
    @PostMapping("/login")
    public String loginLogic(User user){
        log.info("login logic");
        Subject currentUser = SecurityUtils.getSubject();
        // 用户身份认证
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            token.setRememberMe(false);
                // 登录
                currentUser.login(token);
            return "/index";
        }else {
            return "/login";

        }


    }

    @GetMapping("/getAll")
    public String getAllUser(){
        log.info("获得所有用户");
        return "/userlist";
    }

    @GetMapping("/logout")
    public String logout(){
        return "/login";
    }

    @GetMapping("/error")
    public String error(){
        log.error("权限不足！");
        return "/error";
    }
}
