package com.example.upload_ziguang.gb1400.domain.GB1400;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubscribeNotification {
    /**
     * 通知标识
     * 该订阅通知标识符
     * GA/T 1400.1，布控、订阅及相应通知
     * 管理对象 ID
     */
    private String NotificationID;
    /**
     * 订阅标识
     */
    private String SubscribeID;
    /**
     * 订阅标题
     */
    private String Title;
    /**
     * TriggerTime
     */
    private String TriggerTime;
    /**
     * 信息标识
     */
    private String InfoIDs;
    /**
     * FaceObjectList
     * 人脸信息数据集
     */
    private FaceListObject FaceObjectList;
    /**
     * 机动车信息
     * MotorVehicleObject
     * List
     */
    private MotorVehicleListObject MotorVehicleObjectLis;
}
