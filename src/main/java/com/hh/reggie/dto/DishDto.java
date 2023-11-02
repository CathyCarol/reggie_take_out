package com.hh.reggie.dto;

import com.hh.reggie.entity.Dish;
import com.hh.reggie.entity.DishFlavor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish implements Serializable{

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;

}
