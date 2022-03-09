package com.example.upload_ziguang.gb1400.domain.GB1400;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * //图像子对象，人 、车、物等对象可以包含一个或者多个图像子对象。
 */
@Getter
@Setter
@ToString
public class ImageInfo {
    /**
     * GA/T 1400.1，图像信息基本要素 ID，
     * 视频、图像、文件
     * string(41)
     */
    private String ImageID;
    /**
     *   10  目标检测与特征提取  人脸检测
     *   11  目标检测与特征提取  人脸比对
     *   12  目标检测与特征提取  车辆检测
     *   13  目标检测与特征提取  车辆比对
     */
    private String EventSort;
    /**
     * GA/T 1400.1，采集设备、卡口点位、
     * 采集系统、分析系统、视图库、应用平
     * 台等设备编码规则
     * ----------
     * string(20)
     * ----------
     * 公安数据元内
     * 部标识符 (未填
     * 表示无)
     * DE01195
     */
    private String DeviceID;
    /**
     * 图像来源
     * DataSourceType  string(2)  视频图像数据来源
     * 序号  消息中取值  说明
     * 1  1  政府机关监控
     * 2  2  社会面治安监控
     * 3  3  交通监控（含轨道交通监控）
     * 4  4  出入境监控
     * 5  5  港口监控
     * 6  6  金融系统监控
     * 7  7  旅馆监控
     * 8  8  互联网营业场所监控
     * 9  9  娱乐服务场所监控
     * 10  10  其他企业/事业单位监控
     * 11  11  居民自建监控
     * 12  12  公安内部
     * 13  13  监所
     * 14  14  讯问室
     * 15  15  车（船、直升机等）载终端拍摄
     * 16  16  移动执法
     * 17  17  手机、平板电脑拍摄
     * 18  18  DV 拍摄
     * 19  19  相机拍摄
     * 20  20  网络获取
     * 21  21  声像资料片
     * 22  99  其他
     */
    private String ImageSource;
    /**
     * 0  其他
     * 1  自动采集  采集设备及视频分析系统采集
     * 2  人工采集  人工采集通过应用平台输入
     * R
     */
    private int InfoKind=1;
    /**
     * 存储路径
     * 图像文件的存储路径，采用 URI 命名规则
     */
    private String StoragePath;
    /**
     * 图像文件格式
     * 序号  消息中取值  说明
     * 1  Bmp  BMP
     * 2  Gif  GIF
     * 3  Jpeg  JPEG
     * 4  Jfif  JFIF
     * 5  Kdc  KDC
     * 6  Pcd  PCD
     * 7  Pcx  PCX
     * 8  Pic  PIC
     * 9  Pix  PIX
     * 10  Png  PNG
     * 11  Psd  PSD
     * 12  Tapga  TAPGA
     * 13  Tiff  TIFF
     * 14  Wmf  WMF
     * 15  Jp2  JPEG 2000
     * 16  Other  其他
     */
    private String FileFormat;
    /**
     * 拍摄时间
     */
    private Date ShotTime;
    /**
     * 题名
     * 图像资料名称的汉语描述
     */
    private String Title;
    /**
     * 内容描述
     * 对图像内容的简要描述
     */
    private String ContentDescription;
    /**
     * 拍摄地点区划
     * 内详细地址
     * 具体到街道门牌号，可以由乡镇（街道）名
     * 称、街路巷名称、门（楼）牌号、门（楼详
     * 细地址）组成
     */
    private String ShotPlaceFullAdress;
    /**
     * 密级代码
     * 自动采集时取值为 5
     * 序号  消息中取值  说明
     * 1  1  绝密
     * 2  2  机密
     * 3  3  秘密
     * 4  4  内部
     * 5  5  公开
     * 6  9  其他
     */
    private String SecurityLevel="5";
    /**
     * 水平像素值
     */
    private int Width;
    /**
     * 垂直像素值
     */
    private int Height;
}
