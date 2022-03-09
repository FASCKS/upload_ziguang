package com.example.upload_ziguang.gb1400.domain.GB1400;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ResponseStatusObject {
    @JsonProperty("ResponseStatusObject")
    private List<ResponseStatus> ResponseStatusObject;
}
