package com.hh.reggie.mapper;

import com.hh.reggie.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper{
    //增加菜品分类
    @Insert("insert into category(name,sort) values(#{name},#{sort})")
    void save(Category category);

    //分页查询
    @Select("select * from category limit #{page},#{pageSize}")
    List<Category> page(@Param("page") int page, @Param("pageSize")int pageSize);

    //删除菜品
    @Delete("delete category where id = #{ids}")
    void remove(Long ids);

    //更新菜品
    @Update("update category set name = #{name},sort = #{sort} where id = #{id}")
    void updateById(Long id);

    //根据条件查询分类数据
    @Select("select * from category where type = #{type} orderBy update_time asc")
    List<Category> list(Integer type);

    //根据id查询分类
    @Select("select * from category where id = #{categoryId}")
    Category getById(Long categoryId);
}
