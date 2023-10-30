package com.hh.reggie.controller;

import com.hh.reggie.common.R;
import com.hh.reggie.common.rocketMQ.producer.RocketMQProducer;
import com.hh.reggie.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/MessageQueue")
public class MQController {

    @Autowired
    private RocketMQProducer producer;

    @GetMapping("/mqTest")
    public R<?> mqTest(){
        User user = new User();
        producer.send("Topic", "Hello, RocketMQ!");
        return R.success(user);
    }
}
