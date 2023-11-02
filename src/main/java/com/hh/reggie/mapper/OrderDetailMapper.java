package com.hh.reggie.mapper;


import com.hh.reggie.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    //插入多条数据
    void saveBatch(List<OrderDetail> orderDetails);
}
