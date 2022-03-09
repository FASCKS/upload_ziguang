package com.example.upload_ziguang.gb1400.controller;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;

import com.example.upload_ziguang.gb1400.domain.GB1400.*;
import com.example.upload_ziguang.gb1400.util.Global;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 任格
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/VIID")
public class SubscribeController {

/*

    */
/**
     * 订阅流程
     *//*

    @PostMapping("/Subscribes")
    public ResponseStatusListObject responseStatus(@RequestBody String json) {
        log.info("对方发来的数据---》{}",json);
        SubscribeListObject subscribeListObject = JSONUtil.toBean(json, SubscribeListObject.class);
        System.out.println(subscribeListObject);
          log.info("对方订阅发送过来的数据---》{}", subscribeListObject);
        log.info("---------------------------------------------");
        Subscribe subscribe = subscribeListObject.getSubscribeListObject().getSubscribeObject().get(0);

        log.info("获取的订阅对象--->{}", subscribe);
        String subscribeID = subscribe.getSubscribeID();
        log.info("获取订阅ID：" + subscribeID);
        Subscribe subscribe1 = subscribeListObject.getSubscribeListObject().getSubscribeObject().get(0);
        Global.SUBSCRIBE_ID = subscribe1.getSubscribeID();
        Global.TITLE=subscribe1.getTitle();
        //自动采集车牌类型
        if ("13".equals(subscribe1.getSubscribeDetail())){
            Global.Car_Subscribe=subscribe1;
        }
        //自动采集人脸类型
        if ("12".equals(subscribe1.getSubscribeDetail())){
            Global.Face_Subscribe=subscribe1;
        }


        ResponseStatusListObject responseStatusListObject = new ResponseStatusListObject();
        ResponseStatusObject responseStatusObject=new ResponseStatusObject();

        List<ResponseStatus> responseStatuses = new ArrayList<>();
        responseStatusObject.setResponseStatusObject(responseStatuses);
        ResponseStatus responseStatus = new ResponseStatus();

        responseStatuses.add(responseStatus);

        responseStatusListObject.setResponseStatusListObject(responseStatusObject);
        responseStatus.setRequestURL("/VIID/Subscribes");
        responseStatus.setStatusCode(0);
        responseStatus.setStatusString("订阅成功");
        responseStatus.setId(IdUtil.getSnowflake().nextIdStr());
        responseStatus.setLocalTime(DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_PATTERN));


        return responseStatusListObject;


    }

    */
/**
     * 订阅取消
     *//*

    @PutMapping("/Subscribes/{ID}")
    public ResponseStatus delSubscribes(@PathVariable("ID") String id) {
        log.info("对方删除订阅，接收到的id-------》{}", id);
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setId(id);
        responseStatus.setRequestURL("/VIID/Subscribes/" + id);
        responseStatus.setStatusString("正常");
        responseStatus.setLocalTime(DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_PATTERN));
        responseStatus.setStatusCode(0);
        Global.SUBSCRIBE_ID="";
        return responseStatus;
    }

    */
/**
     * 订阅删除
     *//*

    @DeleteMapping("/VIID/Subscribes")
    public ResponseStatusList responseStatusList(String IDList) {
        String[] split = IDList.split(",");

        ResponseStatusList responseStatusList = new ResponseStatusList();
        List<ResponseStatus> responseStatuses = new ArrayList<>();
        log.info("订阅删除");
        Global.SUBSCRIBE_ID="";
        for (String s : split) {
            ResponseStatus responseStatus = new ResponseStatus();
            responseStatus.setId(s);
            responseStatus.setRequestURL("/VIID/Subscribes/" + s);
            responseStatus.setStatusString("正常");
            responseStatus.setLocalTime(DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_PATTERN));
            responseStatus.setStatusCode(0);
            responseStatuses.add(responseStatus);
        }
        responseStatusList.setResponseStatusObject(responseStatuses);
        return responseStatusList;
    }
*/

}
