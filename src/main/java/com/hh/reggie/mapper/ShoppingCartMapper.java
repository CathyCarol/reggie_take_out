package com.hh.reggie.mapper;


import com.hh.reggie.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    //当添加到购物车的是菜品，根据id查询购物车
    ShoppingCart getDish(Long dishId);

    //当添加到购物车的是套餐，根据id查询购物车
    ShoppingCart getSetmeal(Long setmealId);

    //根据id更新购物车
    void updateById(ShoppingCart cartServiceOne);

    //新增购物车
    void save(ShoppingCart shoppingCart);

    //根据用户id查询用户购物车信息
    List<ShoppingCart> list(Long currentId);

    //根据用户id清空购物车
    void remove(Long currentId);
}
