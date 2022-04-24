package com.example.upload_ziguang.gb1400.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.upload_ziguang.gb1400.domain.TempData;
import com.example.upload_ziguang.gb1400.mapper.TempMapper;
import com.example.upload_ziguang.gb1400.service.impl.TempServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gpxx
 * @Date 2022/4/24 15:45
 */
@RestController
@Slf4j
@RequestMapping("/temp")
public class TempController {
    @Autowired
    private TempServiceImpl tempService;

    @GetMapping("/list")
    public JSONObject list() {
        JSONObject jsonObject=new JSONObject();
        QueryWrapper<TempData> qw = new QueryWrapper<>();
        qw.eq("upload", 0).last("limit 100");
        List<TempData> tempData = tempService.list(qw);
        if (tempData == null || tempData.isEmpty()) {
            log.info("当前预警为空");
            jsonObject.putOpt("msg","当前预警为空");
            jsonObject.putOpt("code",0);
            jsonObject.putOpt("data",new ArrayList<>());
            return jsonObject;
        }
        for (TempData tempDatum : tempData) {
            tempDatum.setUpload(1);
            tempDatum.setData("0");
        }
        tempService.updateBatchById(tempData);
// {"msg" : "11","code" : 0,"data" : }

        jsonObject.putOpt("msg","下发预警");
        jsonObject.putOpt("code",0);
        jsonObject.putOpt("data",tempData);

        return jsonObject;
    }
}
