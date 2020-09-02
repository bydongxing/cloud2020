package com.xavier.dong.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.dong.springcloud.dao.TAccountMapper;
import com.xavier.dong.springcloud.entity.po.TAccount;
import com.xavier.dong.springcloud.service.TAccountService;
import org.springframework.stereotype.Service;
@Service
public class TAccountServiceImpl extends ServiceImpl<TAccountMapper, TAccount> implements TAccountService{

}
