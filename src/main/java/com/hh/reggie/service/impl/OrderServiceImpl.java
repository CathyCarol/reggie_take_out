package com.hh.reggie.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.hh.reggie.common.BaseContext;
import com.hh.reggie.common.CustomException;
import com.hh.reggie.common.enums.OrderStatusEnum;
import com.hh.reggie.entity.*;
import com.hh.reggie.mapper.OrderMapper;
import com.hh.reggie.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private OrderDetailService orderDetailService;


    /**
     * 用户下单
     * @param orders
     */
    @Transactional
    public void submit(Orders orders) {
        // 查询地址数据
        Long addressBookId = orders.getAddressBookId();
        if (addressBookId == null) {
            throw new CustomException("地址信息参数错误");
        }
        AddressBook addressBook = addressBookService.getById(addressBookId);
        if (addressBook == null) {
            throw new CustomException("用户地址信息有误，不能下单！");
        }
        // 获取当前用户的id
        Long userId = BaseContext.getCurrentId();
        if (userId == null) {
            throw new RuntimeException("获取用户信息错误");
        }

        // 查询当前用户的购物车数据
        List<ShoppingCart> shoppingCarts = shoppingCartService.list(userId);
        // 订单号
        long orderId = IdWorker.getId();

        List<OrderDetail> orderDetails = shoppingCarts.stream().map((item) -> {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(item, orderDetail);
            orderDetail.setOrderId(orderId);
            return orderDetail;
        }).collect(Collectors.toList());
        // 总金额
        int amount = orderDetails.stream().mapToInt(obj -> {
            return obj.getAmount().multiply(BigDecimal.valueOf(obj.getNumber())).intValue();
        }).sum();


        // 查询用户数据
        User user = userService.getById(userId);

        LocalDateTime now = LocalDateTime.now();
        StringBuffer sb = new StringBuffer();
        sb.append(addressBook.getProvinceName()).append(addressBook.getCityName()).append(addressBook.getDistrictName()).append(addressBook.getDetail());
        orders = Orders.builder()
                .id(orderId)
                .orderTime(now)
                .checkoutTime(now)
                .status(OrderStatusEnum.WAIT_SEND.getCode())
                .amount(new BigDecimal(amount))
                .userId(userId)
                .number(String.valueOf(orderId))
                .userName(user.getName())
                .consignee(addressBook.getConsignee())
                .phone(addressBook.getPhone())
                .address(sb.toString()).build();
        //向订单表插入数据，一条数据
        this.save(orders);

        //向订单明细表插入数据，多条数据
        orderDetailService.saveBatch(orderDetails);

        //清空购物车数据
        shoppingCartService.remove(userId);
    }

    public void save(Orders orders) {
        orderMapper.save(orders);
    }
}
