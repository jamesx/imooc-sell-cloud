package com.hommin.study.sellcloud.order.controller;

import com.hommin.study.sellcloud.order.VO.ResultVO;
import com.hommin.study.sellcloud.order.mqstream.StreamClient;
import com.hommin.study.sellcloud.order.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hommin
 * 2018年06月10日 下午8:12
 */
@RestController
public class StreamMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/streamMesssage")
    public ResultVO<String > streamMesssage(){
        ResultVO heheda = ResultVOUtil.success("heheda");
        streamClient.input().send(MessageBuilder.withPayload(heheda).build());
        return heheda;
    }

}
