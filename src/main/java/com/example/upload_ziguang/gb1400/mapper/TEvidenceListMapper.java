package com.example.upload_ziguang.gb1400.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.upload_ziguang.gb1400.domain.TEvidenceList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Gpxx
 * @Date 2022/3/9 11:00
 */
@Mapper
public interface TEvidenceListMapper extends BaseMapper<TEvidenceList> {
    List<TEvidenceList> getByFrameId(@Param("frameId") Long frameId);
}