package com.hh.reggie.service.impl;


import com.hh.reggie.entity.OrderDetail;
import com.hh.reggie.mapper.OrderDetailMapper;
import com.hh.reggie.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public void saveBatch(List<OrderDetail> orderDetails) {
        orderDetailMapper.saveBatch(orderDetails);
    }
}
