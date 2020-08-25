package com.xavier.dong.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author XavierDong
 **/
@Configuration
public class GateWayConfig {

    /**
     *
     * 当 访问 http://localhost:9527/guonei 会 转发到 http://news.baidu.com/guonei
     *
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        // https://news/baidu.com/guonei
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        RouteLocator routeLocator = routes.route("path_route_xavierdong",
                r -> r.path("/guonei")
                        .uri("http://news.baidu.com/guonei")).build();
        return routeLocator;
    }

    @Bean
    public RouteLocator routeLocator2(RouteLocatorBuilder routeLocatorBuilder) {
        // https://news/baidu.com/guonei
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        RouteLocator routeLocator = routes.route("path_route_xavierdong",
                r -> r.path("/guoji")
                        .uri("http://news.baidu.com/guoji")).build();
        return routeLocator;
    }
}
