package com.giao.test;



import com.giao.pojo.Cart;
import com.giao.pojo.CartItem;
import com.giao.service.OrderService;
import com.giao.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {

        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(100)));
        cart.addItem(new CartItem(2,"数据结构",1,new BigDecimal(100)));
        OrderService orderService=new OrderServiceImpl();
        System.out.println(cart);
        System.out.println("订单号是："+orderService.createOrder(cart,1));
    }
}