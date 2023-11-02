package com.hh.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hh.reggie.entity.Dish;
import com.hh.reggie.entity.DishFlavor;
import com.hh.reggie.mapper.DishFlavorMapper;
import com.hh.reggie.mapper.DishMapper;
import com.hh.reggie.service.DishFlavorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishFlavorServiceImpl implements DishFlavorService {
    private DishFlavorMapper dishFlavorMapper;


    @Override
    public List<DishFlavor> list(Long dishId) {
        return dishFlavorMapper.list(dishId);
    }

    @Override
    public void remove(Long id) {
        dishFlavorMapper.remove(id);
    }

    @Override
    public void saveBatch(List<DishFlavor> flavors) {
        dishFlavorMapper.saveBatch(flavors);
    }


}
