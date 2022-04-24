package com.example.upload_ziguang.gb1400.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Gpxx
 * @Date 2022/4/24 14:41
 */
@Data
@TableName(value = "t_temp_data")
public class TempData {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "data")
    private String data;
    @TableField(value = "upload")
    private Integer upload;
}
