package com.hh.reggie.service;


import com.hh.reggie.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    //插入多条数据
    void saveBatch(List<OrderDetail> orderDetails);
}
