package com.giao.test;

import com.giao.dao.OrderDao;
import com.giao.dao.impl.OrderDaoImpl;
import com.giao.pojo.Order;
import com.giao.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {
    private OrderDao orderDao=new OrderDaoImpl();
    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("1234567890",new Timestamp(new Date().getTime()),new BigDecimal(100),0,1));
    }
    @Test
    public void queryOrderByUserId(){
        for (Order order : orderDao.queryOrdersByUserId( Integer.valueOf(1))){
            System.out.println(order);
        }
    }


    @Test
    public void queryOrders(){
        for (Order order : orderDao.queryOrders()){
            System.out.println(order);
        }
    }
    @Test
    public void queryOrderItemsByOrderId(){
        for (OrderItem orderItem:orderDao.queryOrderItemsByOrderId("16781022653051")){
            System.out.println(orderItem);
        }
    }
}