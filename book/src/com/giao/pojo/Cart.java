package com.giao.pojo;

import java.math.BigDecimal;
import java.util.*;

public class Cart {

    private Map<Integer,CartItem> items=new HashMap<Integer,CartItem>();

    public void addItem(CartItem cartItem){
        CartItem item=items.get(cartItem.getId());
        if(item==null){
            items.put(cartItem.getId(),cartItem);
        }
        else{
            item.setCount(item.getCount()+1);
        }
    }
    public void deleteItem(Integer id){
        items.remove(id);
    }
    public void clear(){
        items.clear();
    }
    public void updateCount(Integer id,Integer count){
        CartItem item=items.get(id);
        if(item!=null){
            item.setCount(count);
        }
    }



    public Integer getTotalCount() {
        Integer totalCount = 0;
        for(Map.Entry<Integer,CartItem>entry:items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for(CartItem item: items.values()){
            totalPrice=totalPrice.add(item.getTotalPrice());
        }
        return totalPrice;

    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
