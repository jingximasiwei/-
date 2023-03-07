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

public class RegistServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        //2.判断验证码是否正确 ===== 写死，要求验证码为：abcde
        if("abcde".equalsIgnoreCase(code)){
            if(userService.existsUsername(username)){
                //不可用
                System.out.println("用户名["+username+"]已存在");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }else {
                //可用
                userService.registUser(new User(null,username,password,email));

                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
            }
        }else {
            //不正确
            System.out.println("验证码["+ code+"]错误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }


    }
}
