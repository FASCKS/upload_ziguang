package com.example.upload_ziguang.gb1400.web.gb1400;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.example.upload_ziguang.gb1400.domain.GB1400.SubscribeNotification;
import com.example.upload_ziguang.gb1400.domain.GB1400.SubscribeNotificationListObject;
import com.example.upload_ziguang.gb1400.domain.GB1400.SubscribeNotificationObject;
import com.example.upload_ziguang.gb1400.util.Global;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NoticeService {
    @Value("${this.ip}")
    private String ip;
    @Value("${this.port}")
    private String prot;
    @Value("${this.sum}")
    private String sum;
    @Value("${this.upload_car}")
    private boolean upload_car;
    @Value("${YI_SA_IP}")
    private String yiSaIp;
    @Value("${YI_SA_PORT}")
    private String yiSaPort;
    public int sendNotice(SubscribeNotificationObject subscribeNotificationObject){
        SubscribeNotificationListObject subscribeNotificationListObject=new SubscribeNotificationListObject();
        subscribeNotificationListObject.setSubscribeNotificationListObject(subscribeNotificationObject);
        for (SubscribeNotification subscribeNotification : subscribeNotificationObject.getSubscribeNotificationObject()) {
            subscribeNotification.setNotificationID(Global.NoticeId(""));
            subscribeNotification.setTriggerTime(DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss"));
            subscribeNotification.setInfoIDs(Global.getSourceID("11"));
        }
        log.info("准备发送通知");
        String uri = "http://"+yiSaIp+":"+yiSaPort+"/VIID/SubscribeNotifications";
        HttpRequest httpRequest=HttpRequest
                .post(uri)
                .body(JSONUtil.toJsonStr(subscribeNotificationListObject))
                .contentType("application/vvid+json")
                .header("User-Identify", "35040000001320000413");
        log.info("我方发送的数据 ------》{} ",httpRequest);
        HttpResponse execute = httpRequest.execute();
        log.info("对方返回的数据 ----->{}",execute);
        return execute.getStatus();
    }
}
