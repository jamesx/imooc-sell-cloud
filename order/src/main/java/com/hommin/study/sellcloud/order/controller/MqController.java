package com.hommin.study.sellcloud.order.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hommin
 * 2018年06月10日 下午6:28
 */
@RestController
public class MqController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/sendMessage")
    public void sendMessage(){
        amqpTemplate.convertAndSend("myQueue", "Hello bosh");
    }
        @GetMapping("/sendMessage2")
        public void sendMessage2(){
            amqpTemplate.convertAndSend("Exchange", "key", "shshshshsh");
        }

}
