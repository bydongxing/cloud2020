package com.xavier.dong.springcloud.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xavier.dong.springcloud.dao.TStorageMapper;
import com.xavier.dong.springcloud.entity.po.TStorage;
import com.xavier.dong.springcloud.service.TStorageService;
@Service
public class TStorageServiceImpl extends ServiceImpl<TStorageMapper, TStorage> implements TStorageService{

}
