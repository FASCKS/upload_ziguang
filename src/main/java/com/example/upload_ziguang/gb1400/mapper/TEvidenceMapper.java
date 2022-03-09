package com.example.upload_ziguang.gb1400.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.upload_ziguang.gb1400.domain.TEvidence;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *  @author Gpxx
 *  @Date 2022/3/9 11:00
 */
@Mapper
public interface TEvidenceMapper extends BaseMapper<TEvidence> {
    List<TEvidence> findAllFace();

    List<TEvidence> findAllCar();
}