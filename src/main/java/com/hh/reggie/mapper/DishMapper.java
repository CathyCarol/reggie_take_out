package com.hh.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hh.reggie.dto.DishDto;
import com.hh.reggie.entity.Dish;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface DishMapper {

    //根据id查询菜品信息
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    //根据id更新菜品信息
    @Update("update dish set name = #{name},category_id = #{categoryId},price = #{price} where id = #{id}")
    void updateById(String name, Long categoryId, BigDecimal price, Long id);

    @Select("select * from dish where name = #{name} limit #{page},#{pageSize}")
    List<Dish> page(@Param("page") int page, @Param("pageSize") int pageSize,String name);

    //查询菜品的所有信息
    @Select("select * from dish")
    List<Dish> getRecords();

    //根据id查询菜品数量
    @Select("select count(*) from dish where category_id = #{id}")
    int countDish(Long id);

    //新增菜品口味信息
    @Insert("insert into dish values(#{id},#{name},#{name},#{categoryId},#{price},#{code},#{image},#{description})")
    void save(DishDto dishDto);

    //根据条件查询对应的菜品信息
    @Select("select * form dish where category_id = #{categoryId} and status = #{status}")
    List<Dish> list(Long categoryId,Integer status);
}
