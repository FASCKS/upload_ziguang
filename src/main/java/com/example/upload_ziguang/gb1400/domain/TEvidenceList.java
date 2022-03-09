package com.example.upload_ziguang.gb1400.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 *  @author Gpxx
 *  @Date 2022/3/9 11:00
 */
@Data
@TableName(value = "t_evidence_list")
public class TEvidenceList {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "`path`")
    private String path;

    @TableField(value = "frame_id")
    private Long frameId;

    @TableField(value = "ip")
    private String ip;

    @TableField(value = "evidence_id")
    private Long evidenceId;

    @TableField(value = "pic_size")
    private Long picSize;

    @TableField(value = "s_size")
    private Long sSize;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "un_cleanup")
    private Byte unCleanup;

    @TableField(value = "cleanup_time")
    private Date cleanupTime;

    public static final String COL_ID = "id";

    public static final String COL_PATH = "path";

    public static final String COL_FRAME_ID = "frame_id";

    public static final String COL_IP = "ip";

    public static final String COL_EVIDENCE_ID = "evidence_id";

    public static final String COL_PIC_SIZE = "pic_size";

    public static final String COL_S_SIZE = "s_size";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UN_CLEANUP = "un_cleanup";

    public static final String COL_CLEANUP_TIME = "cleanup_time";
}