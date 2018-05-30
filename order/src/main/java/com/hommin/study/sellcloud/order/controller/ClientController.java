package com.hommin.study.sellcloud.order.controller;

import com.hommin.study.sellcloud.order.client.ProductClient;
import com.hommin.study.sellcloud.order.dto.DecreaseStock;
import com.hommin.study.sellcloud.order.dto.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 廖师兄
 * 2017-12-10 20:39
 */
@RestController
@Slf4j
public class ClientController {

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        //1.第一种方式(直接使用restTemplate, url写死)
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8080/msg", String.class);

        //2. 第二种方式(利用loadBalancerClient通过应用名获取url, 然后再使用restTemplate)
//        RestTemplate restTemplate = new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/msg";
//        String response = restTemplate.getForObject(url, String.class);

        //3. 第三种方式(利用@LoadBalanced, 可在restTemplate里使用应用名字)
//        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//
//        log.info("response={}", response);

        String response = productClient.getMsg();
        return response;
    }

    @GetMapping("/decrease")
    public String decrease(){
        List<DecreaseStock> decreaseStocks = Arrays.asList(new DecreaseStock("157875196366160022", 1), new DecreaseStock("157875227953464068", 1));
        productClient.decreaseStock(decreaseStocks);
        return "OK";
    }

    @GetMapping("/product")
    public List<ProductInfo> product(){
        List<String > decreaseStocks = Arrays.asList("157875196366160022", "157875227953464068", "164103465734242707");
        List<ProductInfo> productInfos = productClient.listForOrder(decreaseStocks);
        return productInfos;
    }
}
