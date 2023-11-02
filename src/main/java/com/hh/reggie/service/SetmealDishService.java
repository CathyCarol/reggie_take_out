package com.hh.reggie.service;

import com.hh.reggie.entity.SetmealDish;

import java.util.List;

public interface SetmealDishService{
    void saveBatch(List<SetmealDish> setmealDishes);

    void remove(List<Long> ids);
}
