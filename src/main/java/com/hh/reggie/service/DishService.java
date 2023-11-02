package com.hh.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hh.reggie.dto.DishDto;
import com.hh.reggie.entity.Dish;

import java.util.List;

public interface DishService {
    public void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和对应的口味信息
    public DishDto getByIdWithFlavor(Long id);

    //更新菜品口味信息
    public void updateWithFlavor(DishDto dishDto);

    //根据id查询菜品信息
    int countDish(Long id);

    List<Dish> page(int page, int pageSize, String name);

    List<Dish> getRecords();
    //根据条件查询对应的菜品信息
    List<Dish> list(Long categoryId,Integer status);
}
