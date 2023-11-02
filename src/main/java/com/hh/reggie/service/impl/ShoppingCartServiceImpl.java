package com.hh.reggie.service.impl;

import com.hh.reggie.entity.ShoppingCart;
import com.hh.reggie.mapper.ShoppingCartMapper;
import com.hh.reggie.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Override
    public ShoppingCart getDish(Long dishId) {
        return shoppingCartMapper.getDish(dishId);
    }

    @Override
    public ShoppingCart getSetmeal(Long setmealId) {
        return shoppingCartMapper.getSetmeal(setmealId);
    }

    @Override
    public void updateById(ShoppingCart cartServiceOne) {
        shoppingCartMapper.updateById(cartServiceOne);
    }

    @Override
    public void save(ShoppingCart shoppingCart) {
        shoppingCartMapper.save(shoppingCart);
    }

    @Override
    public List<ShoppingCart> list(Long currentId) {
        return shoppingCartMapper.list(currentId);
    }

    @Override
    public void remove(Long currentId) {
        shoppingCartMapper.remove(currentId);
    }
}
