package com.guorenjie.shirospringboot.controller;

import com.guorenjie.shirospringboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guorenjie
 * @date 2020/6/5
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/getUsername/{username}")
    public String getUser( @PathVariable String username){
        return userService.findUserByName(username).toString();
    }
}
