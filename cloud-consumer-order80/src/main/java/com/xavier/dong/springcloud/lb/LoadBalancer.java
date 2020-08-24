package com.xavier.dong.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author XavierDong
 **/
public interface LoadBalancer {

    ServiceInstance INSTANCE(List<ServiceInstance> serviceInstances);
}
