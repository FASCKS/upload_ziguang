package com.example.upload_ziguang.gb1400.web.gb1400;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;

import com.example.upload_ziguang.gb1400.domain.GB1400.*;
import com.example.upload_ziguang.gb1400.domain.TEvidence;
import com.example.upload_ziguang.gb1400.domain.TEvidenceList;
import com.example.upload_ziguang.gb1400.service.TEvidenceListService;
import com.example.upload_ziguang.gb1400.service.TEvidenceService;
import com.example.upload_ziguang.gb1400.util.Global;
import com.example.upload_ziguang.gb1400.web.TimeController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CarService {
    @Autowired
    private TEvidenceService tEvidenceService;
    @Autowired
    private TEvidenceListService tEvidenceListService;
    @Value("${this.ip}")
    private String ip;
    @Value("${this.port}")
    private String prot;
    @Value("${this.sum}")
    private String sum;

    @Value("${YI_SA_IP}")
    private String yiSaIp;
    @Value("${YI_SA_PORT}")
    private String yiSaPort;


    /**
     * 上传车牌
     * 以萨只需要车牌。
     */
    public void sendCar() {
        List<TEvidence> allFace = tEvidenceService.findAllFace();


        log.info("当前人脸数量--->{}", allFace.size());
        if (allFace.isEmpty()) {
            log.error("未检测到车牌--->{}", DateUtil.date());
            return;
        }
        CountDownLatch sendFace = new CountDownLatch(allFace.size());

        for (TEvidence carFace : allFace) {
            TimeController.threadPoolExecutor.execute(() -> {
                //上传
                sendCarInfo(carFace);
                sendFace.countDown();
            });

        }

        try {
            //默认线程两分钟没有处理完成，则抛出异常
            boolean await = sendFace.await(120, TimeUnit.SECONDS);
            if (!await){
                log.error("------>上传车牌时等待超时<------");
            }
        } catch (InterruptedException e) {
            log.error("上传车牌时-------->等待异常<------");
            e.printStackTrace();
        }
        boolean updateBatch = tEvidenceService.updateBatchById(allFace);
        log.info("上传完成批量更新了----》{} 条数据，总共 {} 条",updateBatch,allFace.size());
    }


    public void sendCarInfo(TEvidence carRealReceiveBayonet) {
        List<TEvidenceList> tEvidenceLists=   tEvidenceListService.getByFrameId(carRealReceiveBayonet.getFrameId());
        //根据图片大小判断哪个是大图
        TEvidenceList bigPicture=null;
        TEvidenceList thumbnail=null;
        {
            TEvidenceList img1 = tEvidenceLists.get(0);
            TEvidenceList img2 = tEvidenceLists.get(1);
            if (img1.getSSize() > img2.getPicSize()) {
                bigPicture=img1;
                thumbnail=img2;
            }else {
                bigPicture=img2;
                thumbnail=img1;
            }
        }
        File file = new File(bigPicture.getPath());
        {
            //判断文件是否存在
            if (!file.exists()) {
                log.error("车牌图片 {} 不存在", file.getAbsolutePath());
                log.info("当前车牌实体----》{}", carRealReceiveBayonet);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    log.error("线程----------->中断异常");
                    e.printStackTrace();
                }
                if (!file.exists()) {
                    log.error("等待三秒后--》车牌图片 {} 不存在", file.getAbsolutePath());
                    carRealReceiveBayonet.setUploadNum(3);
                    return;
                }
            }
        }
        //车辆实体
        MotorVehicleList motorVehicleListObject = new MotorVehicleList();

        {
            List<MotorVehicle> motorVehicleList = new ArrayList<>();
            MotorVehicle motorVehicle = new MotorVehicle();
            motorVehicle.setSourceID(Global.getSourceID(Global.DEVICE_IDSMAP.get(carRealReceiveBayonet.getSbbh().trim())));
            motorVehicle.setMotorVehicleID(Global.getFaceID(motorVehicle.getSourceID(), "02"));
            motorVehicle.setDeviceID(Global.DEVICE_IDSMAP.get(carRealReceiveBayonet.getSbbh().trim()));
            motorVehicle.setStorageUrl1("http://" + ip + ":" + prot + "/" + bigPicture.getPath());
            motorVehicle.setPlateNo(carRealReceiveBayonet.getHphm().isEmpty() ? "无车牌" : carRealReceiveBayonet.getHphm());
            motorVehicleList.add(motorVehicle);
            motorVehicleListObject.setMotorVehicleObject(motorVehicleList);
            //有无车牌
            motorVehicle.setHasPlate(true);
            //号牌种类
            String hpzl= colorToHpzl(carRealReceiveBayonet.getColor(), carRealReceiveBayonet.getHphm());
            motorVehicle.setPlateClass(hpzl);
            //号牌颜色
            String hpys = isColor(hpzl);
            if (hpys == null) {
                hpys = "99";
            }
            motorVehicle.setPlateColor(isColor(hpys));
            motorVehicle.setLeftTopX(0);
            motorVehicle.setLeftTopY(0);
            motorVehicle.setRightBtmX(100);
            motorVehicle.setRightBtmY(100);
            //车身颜色
            motorVehicle.setVehicleColor("99");//其它
            //获取图像
            SubImageInfoList subImageInfoListObject = new SubImageInfoList();
            {
                List<SubImageInfo> subImageInfoList = new ArrayList<>();
                SubImageInfo subImageInfo = new SubImageInfo();
                subImageInfo.setStoragePath("http://" + ip + ":" + prot + "/" + bigPicture.getPath());
                subImageInfo.setImageID(Global.getSourceID(Global.DEVICE_IDSMAP.get(carRealReceiveBayonet.getSbbh().trim())));
                subImageInfo.setEventSort("13");
                subImageInfo.setDeviceID(Global.DEVICE_IDSMAP.get(carRealReceiveBayonet.getSbbh()));
                subImageInfo.setType("14");// 14  场景图
                subImageInfo.setFileFormat("Jpeg");
                String format = DateUtil.format(carRealReceiveBayonet.getCreateTime(), "yyyyMMddHHmmss");
                subImageInfo.setShotTime(format);

                try {
                    subImageInfo.setWidth(Global.getWidth(file));
                    subImageInfo.setHeight(Global.getHeight(file));
                } catch (IOException e) {
                    log.error("获取图片宽高失败");
                    e.printStackTrace();
                }
                //获取base64
                {
                    FileReader fileReader = new FileReader(file);
                    byte[] bytes = fileReader.readBytes();
                    String encode = Base64.encode(bytes);
                    subImageInfo.setData(encode);
                }
                subImageInfoList.add(subImageInfo);
                subImageInfoListObject.setSubImageInfoObject(subImageInfoList);
                motorVehicle.setSubImageList(subImageInfoListObject);
            }
        }

        MotorVehicleListObject motorVehicleList = new MotorVehicleListObject();
        motorVehicleList.setMotorVehicleListObject(motorVehicleListObject);
        HttpRequest httpRequest = HttpRequest
                .post("http://" + yiSaIp + ":" + yiSaPort + "/VIID/MotorVehicles")
                .body(JSONUtil.toJsonStr(motorVehicleList))
                .header("User-Identify", Global.DEVICE_IDSMAP.get(carRealReceiveBayonet.getSbbh().trim()))
                .contentType("application/VIID+JSON;charset=UTF-8")
                .header("Accept", "application/json;charset=utf-8");
        log.info("当前车辆id-----》{}", carRealReceiveBayonet.getId());
        HttpResponse execute = httpRequest.execute();
        try {
            ResponseStatus responseStatus = JSONUtil.toBean(execute.body(), ResponseStatusListObject.class).getResponseStatusListObject().getResponseStatusObject().get(0);
            log.info("对方返回的 对象 ---》{}", responseStatus);
            int statusCode = responseStatus.getStatusCode();
            if (statusCode == 0) {
                carRealReceiveBayonet.setUploadNum(2);
            } else {
                log.info("车辆数据上传失败");
                carRealReceiveBayonet.setUploadNum(3);
            }
        } catch (Exception e) {
            log.error("json解析错误----->{}", e.getMessage());
            carRealReceiveBayonet.setUploadNum(3);
        }
    }
    public String colorToHpzl(Integer color, String hphm) {

        String hpzl = "99";

        if (color == 1) {

            if (hphm.contains("挂")) {
                hpzl = "15";
            } else if (hphm.contains("学")) {
                hpzl = "16";
            } else {
                hpzl = "01";
            }

        } else if (color == 0) {
            hpzl = "02";
        } else if (color == 2) {
            hpzl = "03";
        } else if (color == 3) {

            if (hphm.contains("警")) {
                hpzl = "23";
            } else if ("WJ".equals(hphm.substring(0, 2))) {
                hpzl = "31";
            } else {
                hpzl = "32";
            }

        } else if (color == 6) {
            hpzl = "51";
        } else if (color == 5) {
            hpzl = "52";
        } else if (color == 4 || color == 7) {
            hpzl = "99";
        }

        return hpzl;
    }
    private String isColor(String type) {
        switch (type) {
            case "02":
            case "2":
                return "5";
            case "03":
            case "3":
                return "1";
            case "15":
            case "01":
            case "1":
                return "6";
            case "23":
            case "31":
                return "2";
            case "51":
            case "52":
                return "9";
            default:
                return "99";
        }
    }
}
