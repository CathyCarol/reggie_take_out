package com.hh.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hh.reggie.entity.SetmealDish;
import com.hh.reggie.mapper.SetmealDishMapper;
import com.hh.reggie.mapper.SetmealMapper;
import com.hh.reggie.service.SetmealDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealDishServiceImpl implements SetmealDishService {
    @Autowired
    private SetmealDishMapper setmealDishMapper;
    @Override
    public void saveBatch(List<SetmealDish> setmealDishes) {
        setmealDishMapper.saveBatch(setmealDishes);
    }

    @Override
    public void remove(List<Long> ids) {
        setmealDishMapper.removeByIds(ids);
    }
}
