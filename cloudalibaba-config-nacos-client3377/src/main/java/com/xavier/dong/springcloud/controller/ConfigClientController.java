package com.xavier.dong.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XavierDong
 **/
@RestController
// 支持 nacos 的动态刷新功能
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("config/info")
    public String getConfigInfo(){
        return this.configInfo;
    }
}
