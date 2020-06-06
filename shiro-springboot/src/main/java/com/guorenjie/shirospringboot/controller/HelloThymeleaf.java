package com.guorenjie.shirospringboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author guorenjie
 * @date 2020/6/4
 */
@Controller
@Slf4j
public class HelloThymeleaf {

    /**
     * helloThymeleaf
     * @return
     */
    @RequestMapping("/")
    public String helloThymeleaf(Model model){
        model.addAttribute("hello","你好 Thymeleaf!");
        return "index";
    }
}
