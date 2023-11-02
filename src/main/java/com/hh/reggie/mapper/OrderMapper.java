package com.hh.reggie.mapper;

import com.hh.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    //插入一条数据
    void save(Orders orders);
}
