package com.giao.dao.web;

import com.giao.pojo.Cart;
import com.giao.pojo.Order;
import com.giao.pojo.OrderItem;
import com.giao.pojo.User;
import com.giao.service.OrderService;
import com.giao.service.impl.OrderServiceImpl;
import com.giao.utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class OrderServlet extends BaseServlet {
    private OrderService orderService=new OrderServiceImpl();
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        User loginUser= (User) req.getSession().getAttribute("user");

        if(loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId=loginUser.getId();
        String orderId= null;

        try {
            orderId = orderService.createOrder(cart,userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
    protected void queryOrdersByUserId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user= (User) req.getSession().getAttribute("user");
        List<Order> orders = orderService.queryOrdersByUserId(user.getId());
        req.getSession().setAttribute("orders",orders);
        resp.sendRedirect(req.getContextPath()+"/pages/order/order.jsp");
    }


    protected void queryOrderItemsByOrderId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String orderId = req.getParameter("orderId");
        BigDecimal totalPrice= new BigDecimal(0);
        Integer totalCount=Integer.valueOf(0);
        List<OrderItem> orderItems = orderService.queryOrderItemsByOrderId(orderId);
        for (OrderItem orderItem:orderItems){
            totalCount+=orderItem.getCount();
            totalPrice=totalPrice.add(orderItem.getTotalPrice());
        }
        req.getSession().setAttribute("totalCount",totalCount);
        req.getSession().setAttribute("totalPrice",totalPrice);

        req.getSession().setAttribute("orderItems",orderItems);
        resp.sendRedirect(req.getContextPath()+"/pages/order/orderItem.jsp");
    }
}
