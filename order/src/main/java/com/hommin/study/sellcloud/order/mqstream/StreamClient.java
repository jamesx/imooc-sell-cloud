package com.hommin.study.sellcloud.order.mqstream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Hommin
 * 2018年06月10日 下午8:15
 */
public interface StreamClient {

    String TOPIC = "streamDemoQueue";

    String BACK = "streamReturnQueue";

//    @Output(StreamClient.TOPIC)
//    MessageChannel output();

    @Input(StreamClient.TOPIC)
    SubscribableChannel input();

    @Input(StreamClient.BACK)
    SubscribableChannel input2();
}
