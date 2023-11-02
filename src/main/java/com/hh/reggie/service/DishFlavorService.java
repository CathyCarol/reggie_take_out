package com.hh.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hh.reggie.entity.DishFlavor;
import com.hh.reggie.mapper.DishFlavorMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface DishFlavorService{


    //查询所有的菜品口味信息

    List<DishFlavor> list(Long dishId);

    //根据id清理菜品口味数据

    void remove(Long id);

    void saveBatch(List<DishFlavor> flavors);
}
