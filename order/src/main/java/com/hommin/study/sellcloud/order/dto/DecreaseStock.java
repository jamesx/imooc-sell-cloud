package com.hommin.study.sellcloud.order.dto;

import lombok.Data;

/**
 * @author Hommin
 * 2018年05月30日 下午1:51
 */
@Data
public class DecreaseStock {

    String productId;

    Integer productQuantity;

    public DecreaseStock() {
    }

    public DecreaseStock(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
