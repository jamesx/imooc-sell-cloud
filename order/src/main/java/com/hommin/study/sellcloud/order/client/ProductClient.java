package com.hommin.study.sellcloud.order.client;

import com.hommin.study.sellcloud.order.dto.DecreaseStock;
import com.hommin.study.sellcloud.order.dto.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 商品服务对外接口
 *
 * @author Hommin
 * 2018年05月30日 下午1:54
 */
@FeignClient(name = "product")
public interface ProductClient {

    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStock> decreaseStockInputList);

    @GetMapping("msg")
    String getMsg();
}
