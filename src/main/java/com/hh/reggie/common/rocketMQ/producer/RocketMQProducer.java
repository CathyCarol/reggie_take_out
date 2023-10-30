package com.hh.reggie.common.rocketMQ.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

@Component
public class RocketMQProducer {
    private DefaultMQProducer producer;

    public RocketMQProducer() {
        // 初始化生产者
        producer = new DefaultMQProducer("producerGroup");
        producer.setNamesrvAddr("127.0.0.1:9876");
        try {
            producer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String topic, String message) {
        try {
            Message msg = new Message(topic, "Tag", message.getBytes());
            SendResult result = producer.send(msg);
            if (result.getSendStatus() == SendStatus.SEND_OK) {
                System.out.println("Message sent successfully. Message ID: " + result.getMsgId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
