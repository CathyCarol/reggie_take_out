package com.hh.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hh.reggie.dto.DishDto;
import com.hh.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和对应的口味信息
    public DishDto getByIdWithFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);
}
