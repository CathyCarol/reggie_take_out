package com.hh.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hh.reggie.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper{
    void saveBatch(List<SetmealDish> setmealDishes);
    //根据id批量删除套餐菜品数据
    void removeByIds(List<Long> ids);
}
