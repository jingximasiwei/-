package com.giao.test;

import com.giao.pojo.User;
import com.giao.service.UserService;
import com.giao.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceTest {

    UserService userService=new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null,"giao_bro","666","dada@qq.com"));
        userService.registUser(new User(null,"aagiao","777","XXXX@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"giao","123456",null)));

    }

    @Test
    public void exitsUserName() {
        if(userService.existsUsername("giao00")){
            System.out.println("存在");
        } else {
            System.out.println("用户名可用");
        }

    }
}