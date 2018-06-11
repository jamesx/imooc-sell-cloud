package com.hommin.study.sellcloud.order.mqstream;

import com.hommin.study.sellcloud.order.VO.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.Arrays;
import java.util.List;

/**
 * @author Hommin
 * 2018年06月10日 下午8:08
 */
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(StreamClient.TOPIC)
    @SendTo(StreamClient.BACK)
    public List<String> processMessage(ResultVO message){
        log.info("StreamReceiver, message={}", message);
        return Arrays.asList("Hommin", "Lee");
    }


    @StreamListener(StreamClient.BACK)
    public void backMessage(List<String> names){
        log.info("backMessage, message={}", names);
    }
}
