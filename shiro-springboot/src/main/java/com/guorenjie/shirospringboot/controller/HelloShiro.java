package com.guorenjie.shirospringboot.controller;

import com.guorenjie.shirospringboot.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author guorenjie
 * @date 2020/6/5
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class HelloShiro {

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("/register")
    public String register(){
        return "login";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(User user, Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        subject.login(token);
        return "index";
    }

    @RequestMapping("/toUserinfo")
    public String toUserinfo(User user){
        return "userinfo";
    }

    @RequestMapping("/update")
    public String update(User user){
        return "userinfo";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "index";
    }

    @RequestMapping("/toError")
    public String toError(Model model){
        model.addAttribute("msg", "没有权限");
        return "error";
    }

}
