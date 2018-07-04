package com.hommin.study.sellcloud.apigateway.exception;

/**
 * 限流excepiton
 *
 * @author Hommin
 * 2018年07月04日 下午3:25
 */
public class RateLimiterExcpetion extends RuntimeException {

    public RateLimiterExcpetion(String message) {
        super(message);
    }

    public RateLimiterExcpetion() {
        super();
    }
}
