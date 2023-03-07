package com.giao.test;

import com.giao.dao.UserDao;
import com.giao.dao.impl.UserDaoImpl;
import com.giao.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao=new UserDaoImpl();
    @Test
    public void queryUserByUsername() {

        if(userDao.queryUserByUsername("admin123")==null){
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名不可用");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admin","admin1")==null) {
            System.out.println("用户名或密码错误，登录失败");
        }else{
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"giao","123456","giao@qq.com")));
    }
}