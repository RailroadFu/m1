package com.jxau.m1.controller;

import com.jxau.m1.model.User;
import com.jxau.m1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(String username,String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean flag = userService.check2(user);
        if (flag) {
            userService.login(user);
        } else {
            return "redirect:login?flag=0";
        }

        return "login";
    }
}
