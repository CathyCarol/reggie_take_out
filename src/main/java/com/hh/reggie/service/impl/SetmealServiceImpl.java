package com.hh.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.hh.reggie.common.CustomException;
import com.hh.reggie.dto.SetmealDto;
import com.hh.reggie.entity.Setmeal;
import com.hh.reggie.entity.SetmealDish;
import com.hh.reggie.mapper.SetmealMapper;
import com.hh.reggie.service.SetmealDishService;
import com.hh.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetmealDishService setmealDishService;

    private void save(SetmealDto setmealDto) {
        setmealMapper.save(setmealDto);
    }


    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐的基本信息，操作setmeal，执行insert操作
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item->{
            item.setSetmealId(setmealDto.getId());
            return item;
        })).collect(Collectors.toList());

        //保存套餐和菜品的关联信息，操作seteal_dish,执行insert操作
        setmealDishService.saveBatch(setmealDishes);
    }


    /**
     * 删除套餐，同时要删除套餐和菜品的关联关系
     * @param ids
     */
    @Transactional
    public void deleteWithDish(List<Long> ids) {
        //查询套餐的状态，确定套餐是否可以删除
        //LambdaQueryWrapper<Setmeal> queryWrapper1 = new LambdaQueryWrapper<>();
        //queryWrapper1.in(Setmeal::getId,ids);
        //queryWrapper1.eq(Setmeal::getStatus,1);
        int seatmealcount = this.seatmealCount(ids);
        if(seatmealcount > 0){
            //如果不能删除则抛出一个异常
            throw new CustomException("该套餐正在售卖中，不能删除！");
        }

        //如果可以删除，先删除套餐表中的数据
        this.removeByIds(ids);

        //删除关系表中的数据
        //LambdaQueryWrapper<SetmealDish> queryWrapper2 = new LambdaQueryWrapper<>();
        //queryWrapper2.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(ids);
    }

    public void removeByIds(List<Long> ids) {
        setmealMapper.removeByIds(ids);
    }

    @Override
    public List<Setmeal> list(Setmeal setmeal) {
        return setmealMapper.list(setmeal);
    }

    @Override
    public void page(int page, int pageSize, String name) {
        setmealMapper.page(page,pageSize,name);
    }

    public int seatmealCount(List<Long> ids) {
        return setmealMapper.seatmealCount(ids);
    }

    @Override
    public int count(Long id) {
        return setmealMapper.count(id);
    }


}
