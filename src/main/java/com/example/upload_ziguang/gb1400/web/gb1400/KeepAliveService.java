package com.example.upload_ziguang.gb1400.web.gb1400;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;

import com.example.upload_ziguang.gb1400.domain.GB1400.Keepalive;
import com.example.upload_ziguang.gb1400.domain.GB1400.KeepaliveObject;
import com.example.upload_ziguang.gb1400.domain.GB1400.ResponseStatus;
import com.example.upload_ziguang.gb1400.domain.GB1400.ResponseStatusObject;
import com.example.upload_ziguang.gb1400.util.Digests;
import com.example.upload_ziguang.gb1400.util.Global;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class KeepAliveService {
    @Value("${YI_SA_IP}")
    private String yiSaIp;
    @Value("${YI_SA_PORT}")
    private String yiSaPort;


    public void keepAlive(String deviceID) {

        JSONObject jsonObject = new JSONObject();
        {
            jsonObject.putOpt("DeviceID", deviceID);
        }
        JSONObject sendJson = new JSONObject();
        sendJson.putOpt("KeepaliveObject", jsonObject);
        HttpResponse execute=null;
        try {
            execute  = HttpUtil
                    .createPost("http://" + yiSaIp + ":" + yiSaPort + "/VIID/System/Keepalive")
                    .body(sendJson.toString())
                    .header("Content-Type","application/VIID+JSON;charset=UTF-8")
                    .header("User-Identify",deviceID)
                    .header("Accept", "application/json")
                    .execute();
        }catch (Exception e){
            log.error("请求失败-----》{}",e.getMessage());
        }


        String body = execute.body();
        ResponseStatusObject responseStatusObject = JSONUtil.toBean(body, ResponseStatusObject.class);
        log.info("保活对方返回的数据--->{}",responseStatusObject);

    }

}
