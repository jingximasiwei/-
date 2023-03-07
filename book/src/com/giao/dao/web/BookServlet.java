package com.giao.dao.web;

import com.giao.pojo.Book;
import com.giao.pojo.Page;
import com.giao.service.BookService;
import com.giao.service.impl.BookServiceImpl;
import com.giao.utils.WebUtils;
import org.apache.catalina.startup.Bootstrap;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {


    private BookService bookService=new BookServiceImpl();
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo=WebUtils.parseInt(request.getParameter("pageNo"),0);
        pageNo++;
        //1.获取请求的参数封装成bean对象
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        //2.调用BookService.addBook()保存图书
        bookService.addBook(book);
        //3.跳转到图书列表
//        request.getRequestDispatcher("/manager/bookServlet?action=list");
//        request.getRequestDispatcher("") 如果请求转发会导致按f5后一直添加图书所以重定向
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id=WebUtils.parseInt(request.getParameter("id"),0);

        bookService.deleteBookById(id);

        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        bookService.updateBook(book);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        Book book=bookService.queryBookById(id);
        request.setAttribute("book",book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把全部图书保存到Request域中
        request.setAttribute("books",books);
        //3.请求转发到/pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1 获取请求的参数pageNo和pageSize
        int pageNo=WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        //2 调用BookService.page()
        Page<Book> page=bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3 保存Page对象到Request域中
        request.setAttribute("page",page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

}
