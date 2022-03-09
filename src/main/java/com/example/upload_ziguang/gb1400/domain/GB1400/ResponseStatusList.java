package com.example.upload_ziguang.gb1400.domain.GB1400;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 应答状态对象列表
 */
@Getter
@Setter
@ToString
public class ResponseStatusList {
    private List<ResponseStatus> ResponseStatusObject;
}
