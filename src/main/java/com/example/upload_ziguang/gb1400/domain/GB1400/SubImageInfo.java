package com.example.upload_ziguang.gb1400.domain.GB1400;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubImageInfo {
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
     * 存储路径
     * 图像文件的存储路径，采用 URI 命名规则
     */
    private String StoragePath;
    /**
     * 序号  消息中取值  说明
     * 1  01  车辆大图
     * 2  02  车牌彩色小图
     * 3  03  车牌二值化图
     * 4  04  驾驶员面部特征图
     * 5  05  副驾驶面部特征图
     * 6  06  车标
     * 7  07  违章合成图
     * 8  08  过车合成图
     * 9  09  车辆特写图
     * 10  10  人员图
     * 11  11  人脸图
     * 12  12  非机动车图
     * 13  13  物品图
     * 14  14  场景图
     * 15  100  一般图片
     */
    private String Type;
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
    private String ShotTime;
    /**
     * 水平像素值
     */
    private int Width;
    /**
     * 垂直像素值
     */
    private int Height;
    /**
     * base64
     */
    private String Data;
}
