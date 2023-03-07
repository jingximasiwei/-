package com.giao.dao.web;
/*
* 已被UserServlet.java优化
* */
import com.giao.pojo.User;
import com.giao.service.UserService;
import com.giao.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String passeord = request.getParameter("password");
        User LoginUser = userService.login(new User(null, username, passeord, null));
        if(LoginUser!=null){
            //登录成功

            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
        else {
            //登录失败
            System.out.println("登录失败");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }

    }
}
