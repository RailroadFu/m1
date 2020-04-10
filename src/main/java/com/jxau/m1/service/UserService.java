package com.jxau.m1.service;

import com.jxau.m1.dao.UserDao;
import com.jxau.m1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public void addUser(User user) {
        userDao.insert(user);
    }

    public User login(User user) {
        return userDao.select(user);
    }

    public boolean confPass(String password, String confPass) {
        return  password.equals(confPass);
    }

    public boolean check1(User user, String confPass) {
        if(this.confPass(user.getPassword(),confPass)){
             if(user.getUsername().length()>2 && user.getUsername().length()<10){
                 if (user.getPassword().length() > 5 && user.getPassword().length() < 20) {
                      return true;
                 }else{
                     return false;
                 }
             }else{
                 return false;
             }
        }else{
             return false;
        }
    }

    public boolean check2(User user) {
        if(user.getUsername().length()>2 && user.getUsername().length()<10){
            if (user.getPassword().length() > 5 && user.getPassword().length() < 20) {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

}
