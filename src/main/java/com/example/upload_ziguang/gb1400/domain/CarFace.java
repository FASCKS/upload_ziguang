package com.example.upload_ziguang.gb1400.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@TableName(value = "car_face")
public class CarFace implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 设备编号
     */
    @TableField(value = "sbbh")
    private String sbbh;

    /**
     * 执勤民警
     */
    @TableField(value = "zqmj")
    private String zqmj;

    /**
     * 车辆分类
     */
    @TableField(value = "clfl")
    private String clfl;

    /**
     * 号牌种类
     */
    @TableField(value = "hpzl")
    private String hpzl;

    /**
     * 号牌号码
     */
    @TableField(value = "hphm")
    private String hphm;

    /**
     * 违法地行政区划
     */
    @TableField(value = "xzqh")
    private String xzqh;

    /**
     * 违法地点
     */
    @TableField(value = "wfdd")
    private String wfdd;

    /**
     * 路段代码公里数
     */
    @TableField(value = "lddm")
    private String lddm;

    /**
     * 地点米数
     */
    @TableField(value = "ddms")
    private String ddms;

    /**
     * 违法地址
     */
    @TableField(value = "wfdz")
    private String wfdz;

    /**
     * 违法时间
     */
    @TableField(value = "wfsj")
    private Date wfsj;

    /**
     * 违法时间1
     */
    @TableField(value = "wfsj1")
    private Date wfsj1;

    /**
     * 违法行为
     */
    @TableField(value = "wfxw")
    private String wfxw;

    /**
     * 实测值
     */
    @TableField(value = "scz")
    private String scz;

    /**
     * 标准值
     */
    @TableField(value = "bzz")
    private String bzz;

    /**
     * 照片数量
     */
    @TableField(value = "zpsl")
    private Integer zpsl;

    /**
     * 图片文件名
     */
    @TableField(value = "zpwjm")
    private String zpwjm;

    /**
     * 照片1
     */
    @TableField(value = "zpstr1")
    private String zpstr1;

    /**
     * 照片2
     */
    @TableField(value = "zpstr2")
    private String zpstr2;

    /**
     * 照片3
     */
    @TableField(value = "zpstr3")
    private String zpstr3;

    /**
     * 违法地址视频
     */
    @TableField(value = "wfspdz")
    private String wfspdz;

    @TableField(value = "zpstr4")
    private String zpstr4;

    @TableField(value = "lng")
    private String lng;

    @TableField(value = "lat")
    private String lat;

    /**
     * 判断是否上传成功1为成功-1为失败默认为0
     */
    @TableField(value = "`status`")
    private volatile Byte status;

    @TableField(value = "cjjg")
    private String cjjg;

    @TableField(value = "upload_time")
    private Date uploadTime;

    @TableField(value = "spdir")
    private String spdir;

    @TableField(value = "memo")
    private String memo;

    @TableField(value = "is_leave")
    private Integer isLeave;

    @TableField(value = "is_ok")
    private Integer isOk;

    @TableField(value = "have_pic")
    private Integer havePic;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_SBBH = "sbbh";

    public static final String COL_ZQMJ = "zqmj";

    public static final String COL_CLFL = "clfl";

    public static final String COL_HPZL = "hpzl";

    public static final String COL_HPHM = "hphm";

    public static final String COL_XZQH = "xzqh";

    public static final String COL_WFDD = "wfdd";

    public static final String COL_LDDM = "lddm";

    public static final String COL_DDMS = "ddms";

    public static final String COL_WFDZ = "wfdz";

    public static final String COL_WFSJ = "wfsj";

    public static final String COL_WFSJ1 = "wfsj1";

    public static final String COL_WFXW = "wfxw";

    public static final String COL_SCZ = "scz";

    public static final String COL_BZZ = "bzz";

    public static final String COL_ZPSL = "zpsl";

    public static final String COL_ZPWJM = "zpwjm";

    public static final String COL_ZPSTR1 = "zpstr1";

    public static final String COL_ZPSTR2 = "zpstr2";

    public static final String COL_ZPSTR3 = "zpstr3";

    public static final String COL_WFSPDZ = "wfspdz";

    public static final String COL_ZPSTR4 = "zpstr4";

    public static final String COL_LNG = "lng";

    public static final String COL_LAT = "lat";

    public static final String COL_STATUS = "status";

    public static final String COL_CJJG = "cjjg";

    public static final String COL_UPLOAD_TIME = "upload_time";

    public static final String COL_SPDIR = "spdir";

    public static final String COL_MEMO = "memo";

    public static final String COL_IS_LEAVE = "is_leave";

    public static final String COL_IS_OK = "is_ok";

    public static final String COL_HAVE_PIC = "have_pic";
}