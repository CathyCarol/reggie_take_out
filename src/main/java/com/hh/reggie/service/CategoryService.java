package com.hh.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hh.reggie.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface CategoryService {

    //增加菜品分类
    void save(Category category);

    //分页查询
    List<Category> page(@Param("page") int page, @Param("pageSize")int pageSize);

    //删除菜品
    void remove(Long ids);

    //更新菜品
    void updateById(Long id);

    //根据条件查询分类数据
    List<Category> list(Integer type);

    //根据id查询分类
    Category getById(Long categoryId);
}
