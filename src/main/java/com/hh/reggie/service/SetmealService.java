package com.hh.reggie.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hh.reggie.dto.SetmealDto;
import com.hh.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService{

    //新增套餐，同时需要保存菜品和套餐的关联关系
    public void saveWithDish(SetmealDto setmealDto);

    //删除套餐，同时要删除套餐和菜品的关联关系
    public void deleteWithDish(List<Long> ids);

    //根据id查询套餐数量
    int count(Long id);

    //查询启用的套餐数量
    public int seatmealCount(List<Long> ids);

    //批量删除套餐
    void removeByIds(List<Long> ids);

    //条件查询套餐数据
    List<Setmeal> list(Setmeal setmeal);

    //分页查询
    void page(int startIndex, int pageSize, String name);
}
