package com.hh.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hh.reggie.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    //查询所有的菜品口味信息
    @Select("select * form dish_flavor where dish_id = dishId")
    List<DishFlavor> list(Long dishId);

    //根据id清理菜品口味数据
    @Delete("delete from dish_flavor where dish_id = #{id}")
    void remove(Long id);

    //批量插入口味信息
    void saveBatch(List<DishFlavor> flavors);
}
