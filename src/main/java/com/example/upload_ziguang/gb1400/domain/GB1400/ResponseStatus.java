package com.example.upload_ziguang.gb1400.domain.GB1400;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 应答状态对象
 * ResponseStatus 的 Id 为请求注册的 DeviceID，
 * StatusCode 为本次注册的操作响应码，
 * StatusString 为本次注册的操作响应说明，
 * LocalTime 为被注册方的系统时间，可用于注册方的校时
 */
@Getter
@Setter
@ToString
public class ResponseStatus {
    @JsonProperty("RequestURL")
    private String RequestURL;
    @JsonProperty("StatusCode")
    private int StatusCode;
    @JsonProperty("StatusString")
    private String StatusString;
    @JsonProperty("Id")
    private String Id;
    @JsonProperty("LocalTime")
    private String LocalTime;
}
