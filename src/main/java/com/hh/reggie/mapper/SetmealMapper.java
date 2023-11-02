package com.hh.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hh.reggie.dto.SetmealDto;
import com.hh.reggie.entity.Setmeal;
import com.hh.reggie.entity.SetmealDish;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {
    //根据id查询套餐数量
    @Select("select count(*) from setmeal where category_id = #{id}")
    int count(Long id);

    //新增套餐操作
    @Insert("insert into setmeal(category_id,name,price,description,image) values(#{categoryId},#{name},#{price},#{description},#{image})")
    void save(SetmealDto setmealDto);

    //多行插入套餐菜品
    void saveBatch(List<SetmealDish> setmealDishes);

    //查询启用的套餐数量
    int seatmealCount(List<Long> ids);

    //批量删除套餐
    void removeByIds(List<Long> ids);

    //条件查询套餐数据
    List<Setmeal> list(Setmeal setmeal);

    //分页查询
    void page(@Param("page") int page,@Param("pageSize") int pageSize,@Param("name") String name);
}
