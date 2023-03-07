package com.giao.dao;

import com.giao.pojo.Order;
import com.giao.pojo.OrderItem;

import java.util.List;

public interface OrderDao {
    public int saveOrder(Order order);
    public List<Order> queryOrdersByUserId(Integer id);
    public List<Order> queryOrders();
    public List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
