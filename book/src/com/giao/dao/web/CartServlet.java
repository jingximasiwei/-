package com.giao.dao.web;

import com.giao.pojo.Book;
import com.giao.pojo.Cart;
import com.giao.pojo.CartItem;
import com.giao.service.BookService;
import com.giao.service.impl.BookServiceImpl;
import com.giao.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        Book book=bookService.queryBookById(id);
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice());
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        request.getSession().setAttribute("lastName",book.getName());
//        System.out.println(cart);
        //重定向到原来商品所在的地址页面
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        Book book=bookService.queryBookById(id);
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice());
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("lastName",cartItem.getName());
        resultMap.put("totalCount",cart.getTotalCount());

        Gson gson=new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.clear();
            response.sendRedirect(request.getHeader("Referer"));
        }

    }
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.deleteItem(id);
            response.sendRedirect(request.getHeader("Referer"));
        }

    }
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("修改成功！");
        int id= WebUtils.parseInt(request.getParameter("id"),0);
        int count =WebUtils.parseInt(request.getParameter("count"),1);
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.updateCount(id,count);
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}
