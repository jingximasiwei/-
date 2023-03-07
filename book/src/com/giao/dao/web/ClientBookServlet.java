package com.giao.dao.web;

import com.giao.pojo.Book;
import com.giao.pojo.Page;
import com.giao.service.BookService;
import com.giao.service.impl.BookServiceImpl;
import com.giao.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {


    private BookService bookService=new BookServiceImpl();
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1 获取请求的参数pageNo和pageSize
        int pageNo= WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        //2 调用BookService.page()
        Page<Book> page=bookService.page(pageNo,pageSize);
        page.setUrl("client/bookServlet?action=page");
        //3 保存Page对象到Request域中
        request.setAttribute("page",page);
        //4 请求转发到pages/client/index.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }

    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1 获取请求的参数pageNo和pageSize
        int pageNo= WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min =WebUtils.parseInt(request.getParameter("min"),0);
        int max=WebUtils.parseInt(request.getParameter("max"),Integer.MAX_VALUE);
        //2 调用BookService.page()
        Page<Book> page=bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder sb=new StringBuilder("client/bookServlet?action=pageByPrice");
        if(request.getParameter("min")!=null){
            sb.append("&min=").append(request.getParameter("min"));
        }
        if(request.getParameter("max")!=null){
            sb.append("&max=").append(request.getParameter("max"));
        }
        page.setUrl(sb.toString());
        //3 保存Page对象到Request域中
        request.setAttribute("page",page);
        //4 请求转发到pages/client/index.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }
}
