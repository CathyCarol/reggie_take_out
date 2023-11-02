package com.hh.reggie.service;

import com.hh.reggie.entity.User;

public interface UserService {
    //根据手机号码获取用户
    User getOne(String phone);

    //注册新用户
    void save(User user);

    //根据id查询用户数据
    User getById(Long userId);
}
