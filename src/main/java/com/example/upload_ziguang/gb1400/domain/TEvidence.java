package com.example.upload_ziguang.gb1400.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 *  @author Gpxx
 *  @Date 2022/3/9 11:00
 */
@Data
@TableName(value = "t_evidence")
public class TEvidence {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "sbbh")
    private String sbbh;

    @TableField(value = "frame_id")
    private Long frameId;

    /**
     * 存的是大图中传的objectType（0人脸大图   1人形大图  2机动车(车型)大图  3非机动车大图  4车牌大图）
     */
    @TableField(value = "pic_type")
    private Integer picType;

    @TableField(value = "hphm")
    private String hphm;

    /**
     * 车牌颜色
     */
    @TableField(value = "color")
    private Integer color;

    /**
     * 2大图，6 车小图，1人脸小图，7 车型图片（摄像头传的图片类型）
     */
    @TableField(value = "`type`")
    private Integer type;

    /**
     * 经度（后续改成double）
     */
    @TableField(value = "lat")
    private String lat;

    /**
     * 纬度
     */
    @TableField(value = "lng")
    private String lng;

    @TableField(value = "address")
    private String address;

    /**
     * 抓拍时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 0 未上传 1已上传
     */
    @TableField(value = "is_upload")
    private Byte isUpload;

    /**
     * 上传时间
     */
    @TableField(value = "upload_time")
    private Date uploadTime;

    /**
     * 上传次数 5次为上限
     */
    @TableField(value = "upload_num")
    private Integer uploadNum;

    /**
     * 汽车品牌
     */
    @TableField(value = "brand")
    private String brand;

    /**
     * localType 1人脸小图  2人脸大图  3车牌小图  4车牌大图 5车型小图  6车型大图
     */
    @TableField(value = "local_type")
    private Integer localType;

    @TableField(value = "track_id")
    private Integer trackId;

    /**
     * 抓拍时间戳
     */
    @TableField(value = "appear_time")
    private String appearTime;

    /**
     * 实际上传的车牌id
     */
    @TableField(value = "upload_real_id")
    private Long uploadRealId;

    @TableField(value = "bind_id")
    private Integer bindId;

    /**
     * 启动标记，每次树莓派启动都会记录一个标记，主要用于bind_id寻找track_id
     */
    @TableField(value = "start_tag")
    private String startTag;

    /**
     * 小图坐标
     */
    @TableField(value = "simg_top")
    private Integer simgTop;

    /**
     * 小图坐标
     */
    @TableField(value = "simg_left")
    private Integer simgLeft;

    /**
     * 小图坐标
     */
    @TableField(value = "simg_bottom")
    private Integer simgBottom;

    /**
     * 小图坐标
     */
    @TableField(value = "simg_right")
    private Integer simgRight;

    /**
     * 0未分类，人脸，1黑车牌（仅用作黑车和普通车的上传区分用），2普通车牌，3黑人脸
     */
    @TableField(value = "is_black_car")
    private Integer isBlackCar;

    /**
     * 雄迈 AI 人脸id
     */
    @TableField(value = "n_face_id")
    private Long nFaceId;

    /**
     * 相似度
     */
    @TableField(value = "similarity")
    private String similarity;

    /**
     * 车辆类型
     */
    @TableField(value = "car_type")
    private Integer carType;
    @TableField(exist = false)
    private List<TEvidenceList> tEvidenceLists;

    public static final String COL_ID = "id";

    public static final String COL_SBBH = "sbbh";

    public static final String COL_FRAME_ID = "frame_id";

    public static final String COL_PIC_TYPE = "pic_type";

    public static final String COL_HPHM = "hphm";

    public static final String COL_COLOR = "color";

    public static final String COL_TYPE = "type";

    public static final String COL_LAT = "lat";

    public static final String COL_LNG = "lng";

    public static final String COL_ADDRESS = "address";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_IS_UPLOAD = "is_upload";

    public static final String COL_UPLOAD_TIME = "upload_time";

    public static final String COL_UPLOAD_NUM = "upload_num";

    public static final String COL_BRAND = "brand";

    public static final String COL_LOCAL_TYPE = "local_type";

    public static final String COL_TRACK_ID = "track_id";

    public static final String COL_APPEAR_TIME = "appear_time";

    public static final String COL_UPLOAD_REAL_ID = "upload_real_id";

    public static final String COL_BIND_ID = "bind_id";

    public static final String COL_START_TAG = "start_tag";

    public static final String COL_SIMG_TOP = "simg_top";

    public static final String COL_SIMG_LEFT = "simg_left";

    public static final String COL_SIMG_BOTTOM = "simg_bottom";

    public static final String COL_SIMG_RIGHT = "simg_right";

    public static final String COL_IS_BLACK_CAR = "is_black_car";

    public static final String COL_N_FACE_ID = "n_face_id";

    public static final String COL_SIMILARITY = "similarity";

    public static final String COL_CAR_TYPE = "car_type";
}