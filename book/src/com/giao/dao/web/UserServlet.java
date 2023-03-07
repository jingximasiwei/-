package com.giao.dao.web;

import com.giao.pojo.User;
import com.giao.service.UserService;
import com.giao.service.impl.UserServiceImpl;
import com.giao.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String passeord = request.getParameter("password");
        User LoginUser = userService.login(new User(null, username, passeord, null));
        if(LoginUser!=null){
            //登录成功
            //保存登录信息
            request.getSession().setAttribute("user",LoginUser);
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
        else {
            //登录失败
//            System.out.println("登录失败");
            request.setAttribute("msg","用户名或密码错误");
            request.setAttribute("username",username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }
    }
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁Session中的用户信息
        request.getSession().invalidate();
        //2.重定向道首页
        response.sendRedirect(request.getContextPath());

    }
    protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        boolean existsUsername=userService.existsUsername(username);

        Map<String, Object> resultMap =new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        Gson gson=new Gson();
        String json=gson.toJson(resultMap);
        response.getWriter().write(json);

    }
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //1.获取参数
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
        String code = request.getParameter("code");
        String token=(String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());

        //2.判断验证码是否正确 ===== 写死，要求验证码为：abcde
        if(token!=null&&token.equalsIgnoreCase(code)){
            if(userService.existsUsername(user.getUsername())){
                //不可用
                request.setAttribute("msg","用户名已存在!");
                request.setAttribute("username",user.getUsername());
                request.setAttribute("email",user.getEmail());
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }else {
                //可用
                userService.registUser(user);
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
            }
        }else {
            //不正确
//            System.out.println("验证码["+ code+"]错误");
            request.setAttribute("msg","验证码错误!");
            request.setAttribute("username",user.getUsername());
            request.setAttribute("email",user.getEmail());
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }

    }

}
