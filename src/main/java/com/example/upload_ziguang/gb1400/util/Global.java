package com.example.upload_ziguang.gb1400.util;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.IdUtil;
import com.example.upload_ziguang.gb1400.domain.GB1400.Subscribe;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Global {
    public static long COUNT = 0;
    public static String SUBSCRIBE_ID="";
    public static String TITLE="";
    public static Subscribe Car_Subscribe=null;
    public static Subscribe Face_Subscribe=null;

    public static String DEVICE_ID = CommenUtil.getConfig().getString("DEVICE_ID");

    public static boolean isRegister = false;

    public static Map<String, String> KKBHMAP;
    public static Map<String, String> KKMYMAP;
    public static Map<String, String> PUIDSMAP;
    public static Map<String, String> DEVICE_IDSMAP;
    public static Map<String, String> AREACODE;
    public static Map<String, String> USERNAMEMAP;
    public static Map<String, String> PASSWORDMAP;

//    public static final String[] kkbhs = CommenUtil.getConfig().getStringArray("KKBH");
//    public static final String[] kkmys = CommenUtil.getConfig().getStringArray("KKMY");
    public static final String[] puids = CommenUtil.getConfig().getStringArray("PUID");
    public static final String[] device_ids = CommenUtil.getConfig().getStringArray("DEVICE_ID");

    public static final String[] areacode = CommenUtil.getConfig().getStringArray("AREA_CODE");
    public static final String[] username = CommenUtil.getConfig().getStringArray("USERNAMES");
    public static final String[] password = CommenUtil.getConfig().getStringArray("PASSWORDS");

    static {
        Map<String, String> device_idsMap = new HashMap<>();
        Map<String, String> areacodeMap = new HashMap<>();
        Map<String, String> usernameMap = new HashMap<>();
        Map<String, String> passwordMap = new HashMap<>();
        Map<String, String> kkbhMap = new HashMap<>();
        Map<String, String> kkmyMap = new HashMap<>();
        for (int i = 0; i < device_ids.length; i++) {
            device_idsMap.put(puids[i], device_ids[i]);
            areacodeMap.put(puids[i], areacode[i]);
            usernameMap.put(device_ids[i], username[i]);
            passwordMap.put(device_ids[i], password[i]);
//            kkbhMap.put(puids[i],kkbhs[i] );
//            kkmyMap.put(puids[i],kkmys[i]);
        }
        DEVICE_IDSMAP = device_idsMap;
        AREACODE = areacodeMap;
        USERNAMEMAP = usernameMap;
        PASSWORDMAP = passwordMap;
//        KKBHMAP=kkbhMap;
//        KKMYMAP=kkmyMap;

    }

    /**
     * 表3 视频图像信息基本对象统一标识编码规则
     * 码段	码位	含义	取值说明
     * 设备编码
     * /应用平台编码	1-20	1.对自动采集对象,应使用在线视频图像信息采集设备/系统、分析设备/系统统一标识编码；
     * 2.对人工采集对象,应使用对应的公安视频图像信息应用平台或其他公安信息系统统一标识编码，包括所有通过数据服务接口接入视图库的系统。	GB/T28181-2016附录D中D.1规定的编码规则
     * 子类型编码	21-22 	表示视频图像信息基本对象的类型	01-视频片段
     * 02-图像
     * 03-文件
     * 99-其他
     * 时间编码	23-36	表示视频图像信息基本对象生成时间，精确到秒级	YYYYMMDDhhmmss，年月日时分秒
     * 序号	37-41	视频图像信息基本对象序号
     * 表4 视频图像信息语义属性对象统一标识编码规则
     * 码段	码位	含义	取值说明
     * 视频图像信息基本对象统一标识	1-41	视频图像信息语义属性对象所属的视频图像信息基本对象	视频图像信息基本对象统一标识
     * 子类型编码	42-43 	表示视频图像信息语义属性对象的类型	01-人员
     * 02-机动车
     * 03-非机动车
     * 04-物品
     * 05-场景
     * 06-人脸
     * 07-视频图像标签
     * 99-其他
     * 序号	44-48	视频图像信息语义属性对象序号
     *
     * @return
     */
    public static String getFaceID(String sourceId, String type) {
        String substring = IdUtil.getSnowflake().nextIdStr().substring(14, 19);
        return sourceId + type + substring;
    }


    public static String getSourceID(String deviid) {
        //设备编码
        StringBuilder sb = new StringBuilder();
        //设备编码
        sb.append(deviid);
        //子类型编码
        sb.append("02");
        /*
         *时间编码	23-36	表示视频图像信息基本对象生成时间，精确到秒级	YYYYMMDDhhmmss，年月日时分秒
         */
        sb.append(DateUtil.format(DateUtil.date(), "YYYYMMddhhmmss"));
//        序号	37-41	视频图像信息基本对象序号
        //14431311133585      69472
        String substring = IdUtil.getSnowflake().nextIdStr().substring(14, 19);
        sb.append(substring);
        return sb.toString();
    }

    public static int getWidth(File file) throws IOException {
        Image image = ImageIO.read(file);
        int width = image.getWidth(null);
        return width;
    }

    public static int getHeight(File file) throws IOException {
        Image image = ImageIO.read(file);
        int width = image.getWidth(null);
        return width;
    }

    /**
     * E.3 布控与订阅统一标识编码规则
     * 布控与订阅统一标识编码应符合表5的规定。
     * <p>
     * 表D.5布控与订阅统一标识编码规则
     * 码段 	码位 	含义 	取值说明
     * 机构编码 	1-12 	公安机关机构代码 	公安机关机构代码，采用GA/T 543.1-2011中DE00060
     * 子类型编码 	13-14 	表示类型 	01-布/撤控
     * 02-告警
     * 03-订阅
     * 04-通知
     * 99-其它
     * 时间编码 	15-28 	表示布控与订阅生成时间，精确到秒级 	YYYYMMDDhhmmss，年月日时分秒
     * 序号 	29-33 	表示流水序号
     */
    public static String SubscriptionId(String puid) {
        String area_code = "350404740000" + "03";
        String format = DateUtil.format(DateUtil.date(), "YYYYMMddhhmmss");
        String substring = IdUtil.getSnowflake().nextIdStr().substring(15, 19);
        return area_code + format + substring;
    }
    public static String NoticeId(String puid) {
        String area_code = "350404740000" + "04";
        String format = DateUtil.format(DateUtil.date(), "YYYYMMddhhmmss");
        String substring = IdUtil.getSnowflake().nextIdStr().substring(15, 19);
        return area_code + format + substring;
    }
    public static String toBase64(File file) throws IOException {
        Image imageIO = ImageIO.read(file);
        String toBase64 = ImgUtil.toBase64(imageIO, "jpeg");
        return toBase64;
    }
}
