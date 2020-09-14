package com.xavier.dong.springcloud.interfaces;

import com.xavier.dong.springcloud.common.Result;
import com.xavier.dong.springcloud.entity.dto.Payment;

/**
 * @author XavierDong
 **/
public interface UserService {

    Result getPayment(Payment payment);
}
