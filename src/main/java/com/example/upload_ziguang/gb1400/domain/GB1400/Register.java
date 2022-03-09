package com.example.upload_ziguang.gb1400.domain.GB1400;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  注册对象
 */
@Getter
@Setter
@ToString
public class Register {
    /**
     * 设备编码，自动采集必选
     * R/O
     */
    private String DeviceID;
}
