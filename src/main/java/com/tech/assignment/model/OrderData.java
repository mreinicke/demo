package com.tech.assignment.model;

import java.util.List;

/**
 * Created by a543086 on 10/06/2015.
 */
public class OrderData {
    List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public OrderData() {
    }

    public OrderData(List<Order> orderList) {
        this.orderList = orderList;
    }
}
