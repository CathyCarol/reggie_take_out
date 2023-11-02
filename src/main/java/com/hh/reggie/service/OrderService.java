package com.hh.reggie.service;


import com.hh.reggie.entity.Orders;


public interface OrderService {

    //用户下单
    public void submit(Orders orders);

    //插入一条数据
    void save(Orders orders);
}
