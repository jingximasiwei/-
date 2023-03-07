package com.giao.service.impl;

import com.giao.dao.BookDao;
import com.giao.dao.OrderDao;
import com.giao.dao.OrderItemDao;
import com.giao.dao.impl.BookDaoImpl;
import com.giao.dao.impl.OrderDaoImpl;
import com.giao.dao.impl.OrderItemDaoImpl;
import com.giao.pojo.*;
import com.giao.service.OrderService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        String orderId=System.currentTimeMillis()+""+userId;
        Order order=new Order(orderId,new Timestamp(new Date().getTime()),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);


        for(CartItem cartItem:cart.getItems().values()){
            OrderItem orderItem = new
                    OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),
                    orderId);
            orderItemDao.saveOrderItem(orderItem);
            Book book=bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        return orderDao.queryOrderItemsByOrderId(orderId);
    }
}
