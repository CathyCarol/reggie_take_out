package com.hh.reggie.service.impl;


import com.hh.reggie.dto.DishDto;
import com.hh.reggie.entity.Dish;
import com.hh.reggie.entity.DishFlavor;
import com.hh.reggie.mapper.DishMapper;
import com.hh.reggie.service.DishFlavorService;
import com.hh.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private DishMapper dishMapper;


    /**
     * 保存菜品口味信息
     * @param dishDto
     */
    public void save(DishDto dishDto) {
        dishMapper.save(dishDto);
    }

    /**
     * 新增菜品，同时保存口味数据
     * @param dishDto
     */
    @Transactional
    @Override
    public void saveWithFlavor(DishDto dishDto) {
        this.save(dishDto);
        Long dishId = dishDto.getId();
        //菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();

        flavors = flavors.stream().map((item)->{
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);
    }



    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //查询菜品信息
        Dish dish = dishMapper.getById(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);
        //查询口味信息
        //LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        //queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        Long dishId = dish.getId();
        List<DishFlavor> flavorList = dishFlavorService.list(dishId);
        dishDto.setFlavors(flavorList);
        return dishDto;
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //更新dish表的基本信息
        Long id = dishDto.getId();
        Long categoryId = dishDto.getCategoryId();
        BigDecimal price = dishDto.getPrice();
        String name = dishDto.getName();
        dishMapper.updateById(name,categoryId,price,id);

        //清理当前的口味数据-dish_flavor表的delete操作
        //LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        //queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());

        dishFlavorService.remove(id);
        //添加当前提交过来的口味数据-dish_flavor的insert操作
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item->{
            item.setDishId(id);
            return item;
        })).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 根据id查询菜品数量
     * @param id
     * @return
     */
    @Override
    public int countDish(Long id) {
        return dishMapper.countDish(id);
    }

    @Override
    public List<Dish> page(int page, int pageSize, String name) {
        return dishMapper.page(page,pageSize,name);
    }

    @Override
    public List<Dish> getRecords() {
        return dishMapper.getRecords();
    }

    @Override
    public List<Dish> list(Long categoryId,Integer status) {
        return dishMapper.list(categoryId,status);
    }


}
