package com.hh.reggie.service.impl;


import com.hh.reggie.entity.User;
import com.hh.reggie.mapper.UserMapper;
import com.hh.reggie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getOne(String phone) {
        return userMapper.getOne(phone);
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public User getById(Long userId) {
        return userMapper.getById(userId);
    }
}
