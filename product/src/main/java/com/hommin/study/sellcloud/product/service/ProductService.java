package com.hommin.study.sellcloud.product.service;


import com.hommin.study.sellcloud.product.dataobject.ProductInfo;
import com.hommin.study.sellcloud.product.dto.DecreaseStock;

import java.util.List;

/**
 * Created by 廖师兄
 * 2017-12-09 21:57
 */
public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);

    /**
     * 扣库存
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStock> decreaseStockInputList);
}
