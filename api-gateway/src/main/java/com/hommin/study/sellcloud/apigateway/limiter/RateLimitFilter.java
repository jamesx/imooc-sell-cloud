package com.hommin.study.sellcloud.apigateway.limiter;

import com.google.common.util.concurrent.RateLimiter;
import com.hommin.study.sellcloud.apigateway.exception.RateLimiterExcpetion;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 限流filter
 *
 * 因为是实现限流功能, 所以此filter会在所有filter之前执行, 否则如果在某些filter执行之后执行,而请求又被限制, 那之前的filter就白执行了.
 * 因此此的filter类型为"pre", 且order 小于任何filter.
 *
 * 限流的关键在于通过某种算法将被规则筛选出来的请求阻挡, 从而降低系统处理单位时间内请求的数量.
 *
 * 在此通过令牌桶的方式进行限流: 每过单位时间, 令牌桶中产生一个令牌.请求过来时尝试从令牌桶中获得令牌, 当有且成功获取时, 请求被处理; 当没有令牌获取失败时, 请求被阻挡.
 *
 * 关于令牌桶的算法, google有一个成熟的工具{@link RateLimiter}, 使用方式: 1. 定义静态变量; 2. 每个经过此filter的请求, 尝试去获取令牌.
 *
 * @author Hommin
 * 2018年07月04日 下午3:17
 */
@Component
public class RateLimitFilter extends ZuulFilter {

    private static RateLimiter rateLimiter = RateLimiter.create(100);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        if(!rateLimiter.tryAcquire()){
            throw new RateLimiterExcpetion("服务器限流, 请稍后再试!");
        }
        return null;
    }
}
