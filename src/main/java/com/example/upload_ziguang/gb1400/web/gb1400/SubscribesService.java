package com.example.upload_ziguang.gb1400.web.gb1400;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SubscribesService {
    @Value("${YI_SA_IP}")
    private String yiSaIp;
    @Value("${YI_SA_PORT}")
    private String yiSaPort;

    public void subscribes(){
        String url="http://"+yiSaIp+":"+yiSaPort+"/VIID/Subscribes";

    }
}
