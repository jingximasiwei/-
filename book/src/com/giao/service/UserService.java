package com.giao.service;

import com.giao.pojo.User;

public interface UserService {

    /**
     * 注册
     */
    public void registUser(User user);

    /**
     * 登录
     */
    public User login(User user);

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    public boolean existsUsername(String username);

}
