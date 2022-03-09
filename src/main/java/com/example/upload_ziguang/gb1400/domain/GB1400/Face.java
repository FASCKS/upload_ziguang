package com.example.upload_ziguang.gb1400.domain.GB1400;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 人脸对象
 * 必选 R 可选 O
 */
@Getter
@Setter
@ToString
public class Face {
    /**
     * GA/T 1400.1 图像信息内容要素 ID，人、
     * 人脸、机动车、非机动车、物品、场景
     * 等
     * string(48)
     * R
     */
    private String FaceID;
    /**
     * 0  其他
     * 1  自动采集  采集设备及视频分析系统采集
     * 2  人工采集  人工采集通过应用平台输入
     * R
     */
    private int InfoKind=1;
    /**
     * GA/T 1400.1，图像信息基本要素 ID，
     * 视频、图像、文件
     * string(41)
     * R
     */
    private String SourceID;
    /**
     * 设备编码，自动采集必选
     * R/O
     */
    private String DeviceID;
    /**
     * 左上角 X 坐标
     * 人脸区域，自动采集记录时为必选
     */
    private int LeftTopX;
    /**
     * 左上角 Y 坐标
     * 人脸区域，自动采集记录时为必选
     */
    private int LeftTopY;
    /**
     * 右下角 X 坐标
     * 人脸区域，自动采集记录时为必选
     */
    private int RightBtmX;
    /**
     * 右下角 Y 坐标
     * 人脸区域，自动采集记录时为必选
     */
    private int RightBtmY;
    /**
     * 是否驾驶员 0：否；1：是；2：不确定
     */
    private int IsDriver=2;
    /**
     * 是否涉外人员
     * 0：否；1：是；2：不确定
     */
    private int IsForeigner=2;
    /**
     * 是否涉恐人员
     * 0：否；1：是；2：不确定
     */
    private int IsSuspectedTerrorist=2;
    /**
     * 是否涉案人员
     * 0：否；1：是；2：不确定
     */
    private int IsCriminalInvolved=2;
    /**
     * 是否在押人员
     * 0：否；1：是；2：不确定，人工采集必填
     */
    private int IsDetainees=2;
    /**
     * 是否被害人
     * 0：否；1：是；2：不确定，人工采集必填
     */
    private int IsVictim=2;
    /**
     * 是否可疑人
     * 0：否；1：是；2：不确定
     */
    private int IsSuspiciousPerson=2;
    /**
     * 图像列表
     * 可以包含 0 个或者多个子图像对象
     */
    private SubImageInfoList SubImageList;
}
