package com.hommin.study.sellcloud.order.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Hommin
 * 2018年06月10日 下午6:11
 */
@Component
@Slf4j
public class MqRecevier {

    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    public void process(String message){
        log.info("MqRecevier, message={}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("Exchange"),
            key = "key",
            value = @Queue("myQueue2")
    ))
    public void process2(String message){
        log.info("MqRecevier, message={}", message);
    }

}
