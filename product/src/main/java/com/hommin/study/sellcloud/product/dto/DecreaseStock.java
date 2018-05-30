package com.hommin.study.sellcloud.product.dto;

import lombok.Data;

/**
 * @author Hommin
 * 2018年05月30日 下午1:51
 */
@Data
public class DecreaseStock {

    String productId;

    Integer productQuantity;
}
