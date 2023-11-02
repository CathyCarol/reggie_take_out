package com.hh.reggie.service.impl;

import com.hh.reggie.common.CustomException;
import com.hh.reggie.entity.Category;
import com.hh.reggie.mapper.CategoryMapper;
import com.hh.reggie.service.CategoryService;
import com.hh.reggie.service.DishService;
import com.hh.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @Override
    public void save(Category category) {
        categoryMapper.save(category);
    }

    @Override
    public List<Category> page(int page, int pageSize) {
        return categoryMapper.page(page,pageSize);
    }

    @Override
    public void updateById(Long id) {
        categoryMapper.updateById(id);
    }

    @Override
    public List<Category> list(Integer type) {
        return categoryMapper.list(type);
    }

    @Override
    public Category getById(Long categoryId) {
        return categoryMapper.getById(categoryId);
    }

    /**
     * 根据id删除分类，删除之前需要进行判断
     * @param id
     */

    public void remove(Long id) {
        //LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行查询
        //dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = dishService.countDish(id);

        //查询当前分类是否关联了菜品，如果已经关联，抛出一个业务异常
        if(count1 > 0){
            //已经关联菜品，抛出一个业务异常
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }

        //查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常
        //LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据分类id进行查询
        //setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count(id);
        if(count2 > 0){
            //已经关联套餐，抛出一个业务异常
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }

        //正常删除分类
    }
}
