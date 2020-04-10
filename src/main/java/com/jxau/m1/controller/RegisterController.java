package com.jxau.m1.controller;

import com.jxau.m1.model.User;
import com.jxau.m1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/doReg")
    public String doReg(String user_name,String password,String conf_pass) {
        User user = new User();
        user.setUsername(user_name);
        user.setPassword(password);

        boolean isRegular = userService.check1(user, conf_pass);
        if(isRegular){
        userService.addUser(user);
        }else{
            return "redirect:register?flag=1";
        }
        return "redirect:login";
    }
}
