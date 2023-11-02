package com.hh.reggie.mapper;


import com.hh.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper{
    //根据手机号码获取用户
    User getOne(String phone);

    //注册新用户
    void save(User user);

    //根据id查询用户数据
    User getById(Long userId);
}
