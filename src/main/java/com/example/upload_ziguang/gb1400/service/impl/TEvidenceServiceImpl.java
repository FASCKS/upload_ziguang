package com.example.upload_ziguang.gb1400.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.upload_ziguang.gb1400.mapper.TEvidenceMapper;
import com.example.upload_ziguang.gb1400.domain.TEvidence;
import com.example.upload_ziguang.gb1400.service.TEvidenceService;
/**
 *  @author Gpxx
 *  @Date 2022/3/9 11:00
 */
@Service
public class TEvidenceServiceImpl extends ServiceImpl<TEvidenceMapper, TEvidence> implements TEvidenceService{

    /**
     * 查找人脸
     */
    @Override
    public List<TEvidence> findAllFace() {
        return baseMapper.findAllFace();
    }

    /**
     * 查找车牌
     */
    @Override
    public List<TEvidence> findAllCar() {
        return baseMapper.findAllCar();
    }


}
