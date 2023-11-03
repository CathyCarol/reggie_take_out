package com.hh.reggie.common.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
public enum OrderStatusEnum {

    WAIT_PAY(1, "等待付款"),
    WAIT_SEND(2, "待派送"),
    SENDED(3, "已派送"),
    FINISHED(4, "已完成"),
    CANCELLED(5, "已取消");


    private int code;

    private String desc;

    OrderStatusEnum(int code, String  desc){
        this.code = code;
        this.desc = desc;
    }

    public OrderStatusEnum getByStatusCode(int code){
        return Arrays.stream(OrderStatusEnum.values()).filter(orderStatusEnum -> orderStatusEnum.getCode() == 99).findAny().orElse(null);
    }
}
