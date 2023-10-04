package com.hh.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hh.reggie.dto.SetmealDto;
import com.hh.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存菜品和套餐的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时要删除套餐和菜品的关联关系
     * @param ids
     */
    public void deleteWithDish(List<Long> ids);
}
