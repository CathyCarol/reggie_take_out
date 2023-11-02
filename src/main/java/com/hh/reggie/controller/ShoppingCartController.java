package com.hh.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hh.reggie.common.BaseContext;
import com.hh.reggie.common.R;
import com.hh.reggie.entity.ShoppingCart;
import com.hh.reggie.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("shoppingCart")
@Slf4j
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 菜品添加到购物车
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        log.info("购物车数据:{}",shoppingCart);
        //设置用户id，指定当前是哪个用户的购物车数据
        Long currentId = BaseContext.getCurrentId();
        shoppingCart.setUserId(currentId);


        //查询当前菜品或套餐是否在购物车中
        Long dishId = shoppingCart.getDishId();
        Long setmealId = shoppingCart.getSetmealId();

        //LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        //queryWrapper.eq(ShoppingCart::getDishId,currentId);

        ShoppingCart cartServiceOne = null;

        if(dishId != null){
            //添加到购物车的是菜品
            //queryWrapper.eq(ShoppingCart::getDishId,dishId);
            cartServiceOne = shoppingCartService.getDish(dishId);
        }else{
            //添加到购物车的是套餐
            //queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
            cartServiceOne = shoppingCartService.getSetmeal(setmealId);
        }


        if(cartServiceOne != null){
            //如果已经存在。数量则在原来数据的基础上+1
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number + 1);
            shoppingCartService.updateById(cartServiceOne);
        }else{
            //如果不存在，则添加到购物车，数量默认就是1
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            cartServiceOne = shoppingCart;
        }

        return R.success(cartServiceOne);

    }

    /**
     * 查看购物车
     * @return
     */
    @GetMapping("/list")
    public R<List<ShoppingCart>> list(){
        log.info("查看购物车..." );

        //LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        Long currentId = BaseContext.getCurrentId();
        //queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());
        //queryWrapper.orderByDesc(ShoppingCart::getCreateTime);

        List<ShoppingCart> list = shoppingCartService.list(currentId);
        return R.success(list);
    }

    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    public R<String> clean(){
        //LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        //queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());
        Long currentId = BaseContext.getCurrentId();
        shoppingCartService.remove(currentId);
        return R.success("清空购物车成功！");
    }
}
