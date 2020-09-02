package com.xavier.dong.springcloud.service;

import com.xavier.dong.springcloud.entity.po.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;
public interface TOrderService extends IService<TOrder>{


    void create(TOrder tOrder);


}
