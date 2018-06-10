package com.hommin.study.sellcloud.order.controller;

import com.hommin.study.sellcloud.order.config.OrderProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hommin
 * 2018年06月09日 下午9:11
 */
@RestController
@RefreshScope
public class ConfigDemoController {

    @Value("${order.name}")
    private String name;

    @Autowired
    private OrderProperties orderProperties;

    @GetMapping("/name")
    public String getName(){
        return name;
    }

    @GetMapping("/name2")
    public String getName2(){
        return orderProperties.getName();
    }

}
