package com.hh.reggie.common.rocketMQ.comsumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RocketMQConsumer {
    private DefaultMQPushConsumer consumer;

    public RocketMQConsumer() {
        // 初始化消费者
        consumer = new DefaultMQPushConsumer("consumerGroup");
        consumer.setNamesrvAddr("127.0.0.1:9876");

        try {
            consumer.subscribe("Topic", "*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    for (MessageExt msg : msgs) {
                        String message = new String(msg.getBody());
                        System.out.println("Received message: " + message);
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
