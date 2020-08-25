package com.xavier.dong.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author XavierDong
 **/
@Configuration
public class FeignConfig {


    /**
     *  None: 默认的，不显示任何日志
     *  Basic：仅仅记录请求方法、URL、响应的状态码和执行时间
     *  Headers：除了 Basic 中 定义的信息之外，还有请求和响应的头信息
     *  Full：除了 Headers 中 定义的信息之外，还有请求和响应的正文及元数据
     *
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
