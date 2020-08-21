package com.xavier.dong.springcloud.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    /**
     * ID
     */
    private Long id;

    private String serial;
}