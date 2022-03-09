package com.example.upload_ziguang.gb1400.domain.GB1400;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 订阅对象
 */
@ToString@Getter@Setter
public class Subscribe {
    /**
     * string(41)
     * GA/T 1400.1，图像信息基本要素 ID，
     * 视频、图像、文件
     */
    private String SubscribeID;
    /**
     * 订阅标识符
     * 描述订阅的主题和目标，订阅时必选
     */
    private String Title;
    /**
     * 订阅类别
     * 订阅时必选，可同时带多个类别（此时是一个string），用英文半角逗号分隔
     * 序号  消息中取值  说明
     * 1  1  案（事）件目录
     * 2  2  单个案（事）件内容
     * 3  3  采集设备目录
     * 4  4  采集设备状态
     * 5  5  采集系统目录
     * 6  6  采集系统状态
     * 7  7  视频卡口目录
     * 8  8  单个卡口记录
     * 9  9  车道目录
     * 10  10  单个车道记录
     * 11  11  自动采集的人员信息
     * 12  12  自动采集的人脸信息
     * 13  13  自动采集的车辆信息
     * 14  14  自动采集的非机动车辆信息
     * 15  15  自动采集的物品信息
     * 16  16  自动采集的文件信息
     */
    private String SubscribeDetail;
    /**
     * 订阅资源路径
     * 资源路径URI(卡口ID、设备ID、采集内容ID、案件ID、目标视图库ID、行政区编号2/4/6位等)
     * 支持批量和单个订阅，订阅时必选
     */
    private String ResourceURI;
    /**
     * 订阅时必选
     */
    private String ApplicantName;
    /**
     * 申请单位名称，订阅时必选
     */
    private String ApplicantOrg;
    /**
     * 订阅时必选
     */
    private String BeginTime;
    /**
     * 订阅时必选
     */
    private String EndTime;
    /**
     * 订阅信息接收地址URL如：
     * http://x.x.x.x/receive1，订阅时必选
     */
    private String ReceiveAddr;
    /**
     * 信息接收地址
     * 单位为秒（s），<=0表示不限制
     */
    private String OperateType;
    private String SubscribeStatus;
    private String Reason;
    private String SubscribeCancelOrg;
    private String SubscribeCancelPerson;
    private String CancelTime;
    private String CancelReason;



}
