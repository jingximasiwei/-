package com.giao.test;

import com.giao.pojo.Cart;
import com.giao.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构",1,new BigDecimal(1000)));

        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构",1,new BigDecimal(1000)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构",1,new BigDecimal(1000)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构",1,new BigDecimal(1000)));
        cart.updateCount(1,1);
        System.out.println(cart);
    }
}