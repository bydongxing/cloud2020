package com.xavier.dong.springcloud.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus的配置文件
 *
 * @author xavierdong
 **/
@EnableTransactionManagement
@Configuration
@MapperScan("com.xavier.dong.springcloud.dao")
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 此配置 是为 监控而配置的，与服务容错本身无关，springcloud 升级的坑
     * ServletRegistrationBean：因为 springboot 的默认路径 不是 "/hystrix.stream"
     * <p>
     * 只要 在自己的项目里 配置上下面的 servlet 就可以了
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

}
