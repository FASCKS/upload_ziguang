package com.example.upload_ziguang.gb1400.web.gb1400;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
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

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * URI /VIID/Faces
 * 功能 支持对人脸的查询、增加、修改、删除。支持批量操作。
 * 方法 查询字符串 消息体 返回结果
 * GET Face 属性键/值对  无  <FaceList>
 * POST 无  <FaceList>  <ResponseStatusList>
 * PUT 无  <FaceList>  <ResponseStatusList>
 * DELETE 键为 IDList，值为用英文半角分
 * 号”,”分隔的字符串
 * 无  <ResponseStatusList>
 * 注释 Face、FaceList 的定义应符合 GA/T 1400.3 中的规定。
 */
@Slf4j
@Component
public class FaceService {

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
     * 发送人脸
     * 以萨只需要大图。
     */
    public void sendFace() {
        List<TEvidence> allFace = tEvidenceService.findAllFace();


        log.info("当前人脸数量--->{}", allFace.size());
        if (allFace.isEmpty()) {
            log.error("未检测到人脸--->{}", DateUtil.date());
            return;
        }
        CountDownLatch sendFace = new CountDownLatch(allFace.size());

        for (TEvidence carFace : allFace) {
            TimeController.threadPoolExecutor.execute(() -> {
                //上传
                sendFaceInfo(carFace);
                sendFace.countDown();
            });

        }

        try {
            //默认线程两分钟没有处理完成，则抛出异常
            boolean await = sendFace.await(120, TimeUnit.SECONDS);
            if (!await){
                log.error("------>上传人脸时等待超时<------");
            }
        } catch (InterruptedException e) {
            log.error("上传人脸时-------->等待异常<------");
            e.printStackTrace();
        }
        boolean updateBatch = tEvidenceService.updateBatchById(allFace);
        log.info("上传完成批量更新了----》{} 条数据，总共 {} 条",updateBatch,allFace.size());
    }

    public void sendFaceInfo(TEvidence carFace) {
     List<TEvidenceList> tEvidenceLists=   tEvidenceListService.getByFrameId(carFace.getFrameId());
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

        File bigPictureFile = new File(bigPicture.getPath());
        File thumbnailFile = new File(thumbnail.getPath());

        //判断文件是否存在
        if (!bigPictureFile.exists()) {
            log.error("人脸图片 {} 不存在，路径 {} ", bigPictureFile.getName(), bigPictureFile.getAbsolutePath());
            log.info("当前人脸实体-----》{}", carFace);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                log.error("线程----------->中断异常");
                e.printStackTrace();
            }
            if (!bigPictureFile.exists()){
                log.error("等待三秒后----》人脸图片 {} 不存在，路径 {} ", bigPictureFile.getName(), bigPictureFile.getAbsolutePath());
                carFace.setUploadNum(3);
                return;
            }

        }

        List<Face> faceList = new ArrayList<>();
        Face face = new Face();
        face.setSourceID(Global.getSourceID(Global.DEVICE_IDSMAP.get(carFace.getSbbh().trim())));
        face.setFaceID(Global.getFaceID(face.getSourceID(), "06"));
        face.setDeviceID(Global.DEVICE_IDSMAP.get(carFace.getSbbh().trim()));
        face.setLeftTopX(carFace.getSimgTop());
        face.setLeftTopY(carFace.getSimgLeft());
        face.setRightBtmX(carFace.getSimgRight());
        face.setRightBtmY(carFace.getSimgBottom());
        faceList.add(face);

        SubImageInfoList subImageInfoList = new SubImageInfoList();
        List<SubImageInfo> imageInfos = new ArrayList<>();
        //场景图
        {
            SubImageInfo subImageInfo = new SubImageInfo();
            subImageInfo.setImageID(Global.DEVICE_IDSMAP.get(carFace.getSbbh().trim()));
            subImageInfo.setEventSort("11");
            subImageInfo.setDeviceID(Global.DEVICE_IDSMAP.get(carFace.getSbbh()));
            subImageInfo.setStoragePath("http://" + ip + ":" + prot + "/" + bigPicture.getPath().substring(15));
            subImageInfo.setFileFormat("Jpeg");
            String format = DateUtil.format(carFace.getCreateTime(), "YYYYMMddhhmmss");
            subImageInfo.setShotTime(format);
            subImageInfo.setType("14");
            //添加base64
            String toBase64 = null;
            try {
                toBase64 = Global.toBase64(bigPictureFile);
                subImageInfo.setData(toBase64);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("base64异常");
            }
            {
                //获取图片宽高
                try {
                    Image imageIO = ImageIO.read(bigPictureFile);
                    int width = imageIO.getWidth(null);
                    int height = imageIO.getHeight(null);
                    subImageInfo.setWidth(width);
                    subImageInfo.setHeight(height);
                } catch (IOException e) {
                    log.error("获取图片宽或高失败");
                    e.printStackTrace();
                }
            }
            imageInfos.add(subImageInfo);

        }
        //人脸图
        {
            SubImageInfo subImageInfo = new SubImageInfo();
            subImageInfo.setImageID(Global.DEVICE_IDSMAP.get(carFace.getSbbh().trim()));
            subImageInfo.setEventSort("11");
            subImageInfo.setDeviceID(Global.DEVICE_IDSMAP.get(carFace.getSbbh()));
            subImageInfo.setStoragePath("http://" + ip + ":" + prot + "/" + thumbnail.getPath().substring(15));
            subImageInfo.setFileFormat("Jpeg");
            String format = DateUtil.format(carFace.getCreateTime(), "YYYYMMddhhmmss");
            subImageInfo.setShotTime(format);
            subImageInfo.setType("11");
            //添加base64
            String toBase64 = null;
            try {
                toBase64 = Global.toBase64(thumbnailFile);
                subImageInfo.setData(toBase64);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("base64异常");
            }
            {
                //获取图片宽高
                try {
                    subImageInfo.setWidth(Global.getWidth(thumbnailFile));
                    subImageInfo.setHeight(Global.getHeight(thumbnailFile));
                } catch (IOException e) {
                    log.error("获取图片宽高失败");
                    e.printStackTrace();
                }
            }
            imageInfos.add(subImageInfo);
        }
        subImageInfoList.setSubImageInfoObject(imageInfos);

        face.setSubImageList(subImageInfoList);
        FaceList faceListObject = new FaceList();
        faceListObject.setFaceObject(faceList);
        FaceListObject faceListObjectEnd = new FaceListObject();
        faceListObjectEnd.setFaceListObject(faceListObject);

        //准备发送人脸
        HttpRequest httpRequest = HttpRequest
                .post("http://" + yiSaIp + ":" + yiSaPort + "/VIID/Faces")
                .body(JSONUtil.toJsonStr(faceListObjectEnd))
                .header("User-Identify", Global.DEVICE_IDSMAP.get(carFace.getSbbh().trim()))
                .contentType("application/VIID+JSON;charset=UTF-8")
                .header("Accept", "application/json;charset=utf-8");
           log.info("当前人脸id------》{}",carFace.getId());
        HttpResponse execute = httpRequest.execute();
        try {
            ResponseStatus responseStatus = JSONUtil.toBean(execute.body(), ResponseStatusListObject.class).getResponseStatusListObject().getResponseStatusObject().get(0);
            log.info("对方返回的对象 ----》{} ", responseStatus);
            int statusCode = responseStatus.getStatusCode();
            if (statusCode == 0) {
                carFace.setIsUpload(Convert.toByte(2));
            } else {
                log.info("人脸上传失败");
                carFace.setIsUpload(Convert.toByte(3));
            }

        } catch (Exception e) {
            carFace.setIsUpload(Convert.toByte(3));
            log.error("人脸返回对象接送解析错误");
            log.error(e.getMessage(), e);
        }
    }
}
