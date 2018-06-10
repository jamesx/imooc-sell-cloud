package com.hommin.study.sellcloud.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author Hommin
 * 2018年06月09日 下午9:34
 */
@Component
@ConfigurationProperties(prefix = "order")
@RefreshScope
@Data
public class OrderProperties {

    private String name;

}
