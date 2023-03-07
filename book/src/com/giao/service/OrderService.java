package com.giao.service;

import com.giao.pojo.Cart;
import com.giao.pojo.Order;
import com.giao.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
    public List<Order> queryOrdersByUserId(Integer userId);

    public List<OrderItem> queryOrderItemsByOrderId(String OrderId);
}
