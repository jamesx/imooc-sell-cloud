package com.hommin.study.sellcloud.order.service.impl;

import com.hommin.study.sellcloud.order.client.ProductClient;
import com.hommin.study.sellcloud.order.dataobject.OrderDetail;
import com.hommin.study.sellcloud.order.dataobject.OrderMaster;
import com.hommin.study.sellcloud.order.dto.DecreaseStock;
import com.hommin.study.sellcloud.order.dto.OrderDTO;
import com.hommin.study.sellcloud.order.dto.ProductInfo;
import com.hommin.study.sellcloud.order.enums.OrderStatusEnum;
import com.hommin.study.sellcloud.order.enums.PayStatusEnum;
import com.hommin.study.sellcloud.order.repository.OrderDetailRepository;
import com.hommin.study.sellcloud.order.repository.OrderMasterRepository;
import com.hommin.study.sellcloud.order.service.OrderService;
import com.hommin.study.sellcloud.order.utils.KeyUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 廖师兄
 * 2017-12-10 16:44
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();

       // 查询商品信息(调用商品服务)
       List<String> productIdList = orderDTO.getOrderDetailList().stream()
               .map(OrderDetail :: getProductId)
               .collect(Collectors.toList());
        List<ProductInfo> productInfos = productClient.listForOrder(productIdList);
        //计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            for (ProductInfo productInfo: productInfos) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //单价*数量
                    orderAmout = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //扣库存(调用商品服务)
        List<DecreaseStock> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStock(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
