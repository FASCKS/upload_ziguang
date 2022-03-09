package com.example.upload_ziguang.gb1400.domain.GB1400;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 机动车对象
 */
@Getter
@Setter@ToString
public class MotorVehicle {
    /**
     * GA/T 1400.1 图像信息内容要素 ID，人、
     * 人脸、机动车、非机动车、物品、场景
     * 等
     * string(48)
     */
    private String MotorVehicleID;
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
     * 近景照片
     *卡口相机所拍照片，自动采集必选，图像访问路径，采用URI命名规则
     */
    private String StorageUrl1;
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
     * 车牌号
     * 各类机动车号牌编号车牌全部无法识别的以“无车牌”标识，部分未识别的每个字符以半角‘-’代替
     */
    private String PlateNo;

    /**
     * 图像列表
     * 可以包含 0 个或者多个子图像对象
     */
    private SubImageInfoList SubImageList;
    /**
     * 有无车牌
     */
    private boolean HasPlate;
    /**
     * 号牌种类
     * 序号  消息中取值  说明
     * 1  01  大型汽车号牌
     * 2  02  小型汽车号牌
     * 3  03  使馆汽车号牌
     * 4  04  领馆汽车号牌
     * 5  05  境外汽车号牌
     * 6  06  外籍汽车号牌
     * 7  07  普通摩托车号牌
     * 8  08  轻便摩托车号牌
     * 9  09  使馆摩托车号牌
     * 10  10  领馆摩托车号牌
     * 11  11  境外摩托车号牌
     * 12  12  外籍摩托车号牌
     * GA/T 1400.3—2017
     * 67
     * 序号  消息中取值  说明
     * 13  13  低速车号牌
     * 14  14  拖拉机号牌
     * 15  15  挂车号牌
     * 16  16  教练汽车号牌
     * 17  17  教练摩托车号牌
     * 20  20  临时入境汽车号牌
     * 21  21  临时入境摩托车号牌
     * 22  22  临时行驶车号牌
     * 23  23  警用汽车号牌
     * 24  24  警用摩托车号牌
     * 25  25  原农机号牌
     * 26  26  香港入出境号牌
     * 27  27  澳门入出境号牌
     * 31  31  武警号牌
     * 32  32  军队号牌
     * 33  99  其他号牌
     */
    private String PlateClass;
    /**
     * 车牌颜色
     * 指号牌底色，取ColorType中部分值： 黑色，白色，黄色，蓝色，绿色
     * 颜色详细取值见表B.6。
     * 表 B.6 颜色(值类型 string)
     * 序号  消息中取值  说明
     * 1  1  黑
     * 2  2  白
     * 3  3  灰
     * 4  4  红
     * 5  5  蓝
     * 6  6  黄
     * 7  7  橙
     * 8  8  棕
     * 9  9  绿
     * 10  10  紫
     * 11  11  青
     * 12  12  粉
     * 13  13  透明
     * 14  99  其他
     */
    private String PlateColor;
    /**
     * 序号	消息中取值	说明
     * 1	1	黑
     * 2	2	白
     * 3	3	灰
     * 4	4	红
     * 5	5	蓝
     * 6	6	黄
     * 7	7	橙
     * 8	8	棕
     * 9	9	绿
     * 10	10	紫
     * 11	11	青
     * 12	12	粉
     * 13	13	透明
     * 14	99	其他
     */
    private  String VehicleColor;

}
