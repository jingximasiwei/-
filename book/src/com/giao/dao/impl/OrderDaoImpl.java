package com.giao.dao.impl;

import com.giao.dao.OrderDao;
import com.giao.pojo.Order;
import com.giao.pojo.OrderItem;

import java.sql.Timestamp;
import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql ="insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());

    }

    @Override
    public List<Order> queryOrdersByUserId(Integer id) {
        String sql="select `order_id` orderId ,`create_time` createTime,`price`,`status`,`user_id` userId from t_order where user_id = ?";
        return queryForList(Order.class,sql,id);
    }

    @Override
    public List<Order> queryOrders() {

        String sql="select `order_id` `orderId` ,`create_time` `createTime`,`price`,`status`,`user_id` `userId` from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql="select `id`,`name`,`count`,`price`,`total_price` `totalPrice`,`order_id` `orderId` from t_order_item where order_id = ?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
