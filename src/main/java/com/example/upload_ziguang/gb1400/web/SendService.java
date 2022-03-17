package com.example.upload_ziguang.gb1400.web;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.XmlUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.upload_ziguang.gb1400.util.Global;
import com.example.upload_ziguang.gb1400.webservice.ServicePortTypeProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import javax.xml.xpath.XPathConstants;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class SendService {
    @Value("${upload_file}")
    private String UPLOAD_FILE_PATH;

    @Value("${this.sum}")
    private String sum;

    /**
     * 获得时间
     *
     * @param snowflakeId
     * @return
     */
    private String dateTime(long snowflakeId) {
        long idTime = snowflakeId >> 22;
        long initTime = 1288834974657L;//这个值会有差异
        Date now = new Date(initTime + idTime);
        return DateUtil.format(now, "yyyyMMddHHmmssSSS");
    }

/*
    */
/**
     * 海康转发车牌
     *//*

    public synchronized void sendHaiKang() {
        QueryWrapper<CarRealIllegal> carRealIllegalQueryWrapper = new QueryWrapper<>();
        carRealIllegalQueryWrapper.eq(CarRealIllegal.COL_STATUS, 1).orderByDesc("id").last("limit " + sum);
        final List<CarRealIllegal> carRealIllegals = carRealIllegalService.list(carRealIllegalQueryWrapper);
        if (carRealIllegals.isEmpty()) {
            log.info("上传的海康数据为空");
            return;
        }
        CountDownLatch sendHaiKang = new CountDownLatch(carRealIllegals.size());
        for (CarRealIllegal carRealIllegal : carRealIllegals) {
            TimeController.threadPoolExecutor.execute(() -> {
                final String sbbh = carRealIllegal.getSbbh();
                if (sbbh.isEmpty()) {
                    log.info("设备编号为空");
                    carRealIllegal.setStatus(Convert.toByte(0));
                    return;
                }
                final String finish1 = carRealIllegal.getFinish1();
                final String finish2 = carRealIllegal.getFinish2();
                final String finish3 = carRealIllegal.getFinish3();
                File[] files = new File[3];
                files[0] = FileUtil.file(finish1);
                files[1] = FileUtil.file(finish2);
                files[2] = FileUtil.file(finish3);

                //判断图片文件是否存在
                if (!files[0].exists() || !files[1].exists() || !files[2].exists()) {
                    log.error("海康需要文件 {} , {} , {} 不存在", files[0].getAbsolutePath(), files[1].getAbsolutePath(), files[2].getAbsolutePath());
                    //让线程阻塞三秒钟，防止图片还未解压到目录
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        log.error("线程----------->中断异常");
                        e.printStackTrace();
                    }
                    if (!files[0].exists() || !files[1].exists() || !files[2].exists()) {
                        log.error("等待三秒后--》海康需要文件 {} , {} , {} 不存在", files[0].getAbsolutePath(), files[1].getAbsolutePath(), files[2].getAbsolutePath());
                        carRealIllegal.setStatus(Convert.toByte(2));
                        return;
                    }
                }

                final boolean haikang = haikang(files, DateUtil.date().toDateStr(), carRealIllegal.getSbbh());
                if (haikang) {
                    carRealIllegal.setStatus(Convert.toByte(0));
                } else {
                    //调用海康失败
                    carRealIllegal.setStatus(Convert.toByte(2));
                }
                sendHaiKang.countDown();
            });
        }
        try {
            //默认线程两分钟没有处理完成，则抛出异常
            boolean await = sendHaiKang.await(120, TimeUnit.SECONDS);
            if (!await) {
                log.error("------>上传海康违法数据时等待超时<------");
            }
        } catch (InterruptedException e) {
            log.error("上传海康违法数据时-------->等待异常<------");
            e.printStackTrace();
        }
        boolean updateBatch = carRealIllegalService.updateBatchById(carRealIllegals);
        log.info("上传完成批量更新----》{} 条数据，总共 {} 条", updateBatch, carRealIllegals.size());


    }


    public boolean haikang(File[] imgs, String time, String sbbh) {
        String strVehicleInfo =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>" +
                        "<ROOT>" +
                        "<KKMY>" + Global.KKMYMAP.get(sbbh) + "</KKMY>" +
                        "<KKBH>" + Global.KKBHMAP.get(sbbh) + "</KKBH>" +
                        "<JGSK>" + dateTime(Convert.toLong(time)) + "</JGSK>" +
                        "<CDBH>" + "01" + "</CDBH>" +
                        "<HPHM>" + "无车牌" + "</HPHM>" +
                        "<HPYS>" + "01" + "</HPYS>" +
                        "<HPZL>99</HPZL>" +
                        "<CLSD>" + "0" + "</CLSD>" +
                        "<CWKC>0</CWKC>" +
                        "<CSYS>Z</CSYS>" +
                        "<CLLX>" + "X99" + "</CLLX>" +
                        "<WFLX>" + "1228" + "</WFLX>" +
                        "<SSYF>0</SSYF>" +
                        "</ROOT>";

        ServicePortTypeProxy WebService =
                new ServicePortTypeProxy("http://" + "10.133.7.160" + ":" + "8123" + "?wsdl");

        String strRet2 = null;
        byte[] img1 = null;
        byte[] img2 = null;
        byte[] img3 = null;
        log.info("总共需要发送 {} 张图片", imgs.length);
        try {
            img1 = Files.readAllBytes(Paths.get(imgs[0].getAbsolutePath()));
            if (imgs.length > 1) {
                img2 = Files.readAllBytes(Paths.get(imgs[1].getAbsolutePath()));
            }
            if (imgs.length > 2) {
                img3 = Files.readAllBytes(Paths.get(imgs[2].getAbsolutePath()));
            }
        } catch (IOException e) {
            log.error("读取文件时出错。");
            e.printStackTrace();
        }
        try {
            log.info("我方发送数据------>{}", strVehicleInfo);
            strRet2 = WebService.sendAlarmPass(strVehicleInfo, null, img1, img2, img3);
        } catch (Exception e) {
            log.error("海康上传失败");
            log.error("解析失败----->" + e.getMessage());
        }
        final Document document = XmlUtil.readXML(strRet2);
        final double byXPath = (double) XmlUtil.getByXPath("//ROOT/CODE", document, XPathConstants.NUMBER);
        log.info("海康返回的信息:" + strRet2);
        if (byXPath >= 0) {
            log.info("海康调用成功");
            return true;
        }
        return false;
    }
*/

}
